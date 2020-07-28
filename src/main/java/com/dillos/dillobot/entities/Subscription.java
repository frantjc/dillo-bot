package com.dillos.dillobot.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Subscription {

	public enum SubscriptionType {
        BIRTHDAY("BIRTHDAY");

        String subscription;

        SubscriptionType(String subscription) {
            this.subscription = subscription.toUpperCase();
        }
    }

    @Id
    @GeneratedValue
    Long id;

    @Enumerated(EnumType.STRING)
    SubscriptionType subscription;
    
    public Subscription() {}

}
