package com.aetherwars.events;

import com.aetherwars.interfaces.Event;
import com.aetherwars.models.cards.Card;

public class OnDrawCard extends Event {
    public Card getSelectedCard() {
        return selectedCard;
    }

    private final Card selectedCard;
    public OnDrawCard(Object sender, Card c) {
        super(sender);
        this.selectedCard = c;
    }
}
