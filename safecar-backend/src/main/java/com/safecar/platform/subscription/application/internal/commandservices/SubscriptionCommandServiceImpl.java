package com.safecar.platform.subscription.application.internal.commandservices;

import com.safecar.platform.subscription.domain.model.aggregates.Subscription;
import com.safecar.platform.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.safecar.platform.subscription.domain.model.commands.DeleteSubscriptionCommand;
import com.safecar.platform.subscription.domain.model.commands.UpdateSubscriptionCommand;
import com.safecar.platform.subscription.domain.services.SubscriptionCommandService;
import com.safecar.platform.subscription.infrastructure.persistence.jpa.repositories.PlanRepository;
import com.safecar.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

    private final SubscriptionRepository subscriptionRepository;
    private final PlanRepository planRepository;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository, PlanRepository planRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.planRepository = planRepository;
    }

    @Override
    public Subscription handle(CreateSubscriptionCommand command) {

        var plan = planRepository.findById(command.planId())
                .orElseThrow(() -> new IllegalArgumentException("Plan not found."));

        var subscription = new Subscription(command, plan);

        try {
            subscriptionRepository.save(subscription);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving course: " + e.getMessage());
        }
        return subscription;
    }

    @Override
    public Subscription handle(UpdateSubscriptionCommand command) {
        var plan = planRepository.findById(command.planId())
                .orElseThrow(() -> new IllegalArgumentException("Plan not found."));

        var subscription = subscriptionRepository.findById(command.subscriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found."));

        subscription.updateSubscription(command, plan);

        try {
            subscriptionRepository.save(subscription);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving course: " + e.getMessage());
        }
        return subscription;
    }

    @Override
    public void handle(DeleteSubscriptionCommand command) {
        var subscription = subscriptionRepository.findById(command.subscriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found."));

        try {
            subscriptionRepository.delete(subscription);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving course: " + e.getMessage());
        }

    }

}
