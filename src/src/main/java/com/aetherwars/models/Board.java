package com.aetherwars.models;

import com.aetherwars.core.GameManager;
import com.aetherwars.events.*;
import com.aetherwars.interfaces.Event;
import com.aetherwars.interfaces.Observer;
import com.aetherwars.interfaces.Subscriber;
import com.aetherwars.models.cards.*;

// Board implements Observer
public class Board implements Observer<Card>, Subscriber {
    private final Card[] cards = new Card[] {null, null, null, null, null};
    private final BoardType type;
    private final int MAX_CAP = 5;

    public BoardType getType() {
        return type;
    }

    public Board (BoardType type) {
        this.type = type;
    }

    public int getSize() {
        int count = 0;
        for (int i = 0; i < MAX_CAP; i++) {
            if (cards[i] != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void register(Card card, int index) {
        if (index >= 0 && index < MAX_CAP) {
            cards[index] = card;
        } else {
            System.out.println("Invalid index");
        }
    }

    @Override
    public void register(Card obj) {
        for (int i = 0; i < MAX_CAP; i++) {
            if (cards[i] == null) {
                register(obj, i);
                return;
            }
        }
        System.out.println("No space left!");
    }

    @Override
    public void unregister(Card card) {
        for (int i = 0; i < MAX_CAP; i++) {
            if (cards[i] == card) {
                cards[i] = null;
                return;
            }
        }
        System.out.println("This card is not in the board!");
    }

    @Override
    public void unregister(int index) {
        if (cards[index] != null) {
            cards[index] = null;
        } else {
            System.out.println("Invalid index");
        }
    }

    public Card getCard(int index) {
        return cards[index];
    }

    @Override
    public void notifyObjects() {
        for (Card c: cards) {
            if (c != null)
                c.update();
        }
    }

    @Override
    public void receiveEvent(Event evt) {
        GameManager gm = GameManager.getInstance();
        if (type == BoardType.HAND) {
           if (evt instanceof OnDrawCard) {
               register(((OnDrawCard) evt).getSelectedCard());
           }
        } else {
            if (evt instanceof OnCardAction) {
                OnCardAction ec = (OnCardAction) evt;
                Player p = gm.getCurrentPlayer();
                Card card_from = (ec.getFromHand() ? p.getHand():p.getBoard()).getCard(ec.getFromCardIdx());
                card_from.action(ec);
            } else if (evt instanceof OnPhaseChange) {
                switch (((OnPhaseChange) evt).getPhase()) {
                    case DRAW:
                        notifyObjects();
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
