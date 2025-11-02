package com.safecar.platform.subscription.application.internal.queryservices;

import com.safecar.platform.subscription.domain.model.aggregates.Subscription;
import com.safecar.platform.subscription.domain.model.queries.GetSubscriptionByUserIdQuery;
import com.safecar.platform.subscription.domain.services.SubscriptionQueryService;
import com.safecar.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Subscription handle(GetSubscriptionByUserIdQuery query) {
        return subscriptionRepository.findByUserId(query.userId())
                .orElseThrow(() -> new RuntimeException("Subscription not found for userId: " + query.userId()));
    }
}
