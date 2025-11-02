package com.safecar.platform.subscription.domain.services;

import com.safecar.platform.subscription.domain.model.aggregates.Subscription;
import com.safecar.platform.subscription.domain.model.queries.GetSubscriptionByUserIdQuery;

public interface SubscriptionQueryService {
    Subscription handle(GetSubscriptionByUserIdQuery query);
}
