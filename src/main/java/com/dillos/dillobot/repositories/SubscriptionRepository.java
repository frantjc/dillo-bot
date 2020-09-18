package com.dillos.dillobot.repositories;

import com.dillos.dillobot.entities.Subscription;
import com.dillos.dillobot.entities.Subscription.SubscriptionType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

  public Subscription findBySubscription(SubscriptionType subscription);

}
