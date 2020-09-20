package com.dillos.dillobot.dto;

import com.dillos.dillobot.entities.Subscription;
import com.dillos.dillobot.entities.Subscription.SubscriptionType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class SubscriptionResponse {

  Long id;

  SubscriptionType subscription;

  public SubscriptionResponse(Subscription subscription) {
    this.id = subscription.getId();
    this.subscription = subscription.getSubscription();
  }
}
