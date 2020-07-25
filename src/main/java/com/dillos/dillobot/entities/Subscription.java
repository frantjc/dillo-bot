package com.dillos.dillobot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public enum Subscription {

    BIRTHDAY("birthday");

    @Id
    @GeneratedValue
    Long id;

    String subscription;

    Subscription(String subscription) {
        this.subscription = subscription.toLowerCase();
    }
}