package com.aetherwars.interfaces;

import java.util.ArrayList;

public class Publisher {
    private ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();

    public void addSubscriber(Subscriber s) {
        subscribers.add(s);
    }

    public void removeSubscriber(Subscriber s) {
        subscribers.remove(s);
    }

    public void sendEvent(Event evt) {
        for (int i = 0; i < subscribers.size(); i++) {
            subscribers.get(i).receiveEvent(evt);
        }
    }
}
