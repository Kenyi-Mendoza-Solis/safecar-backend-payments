package com.safecar.platform.subscription.interfaces.rest.transformers.subscription;

import com.safecar.platform.subscription.domain.model.aggregates.Subscription;
import com.safecar.platform.subscription.interfaces.rest.resources.subscription.SubscriptionResource;

public class SubscriptionResourceFromEntityAssembler {
    public static SubscriptionResource toResourceFromEntity(Subscription entity) {
        return new SubscriptionResource(entity.getId(), entity.getStartDate(), entity.getEndDate(), entity.getPlan().getId(), entity.getUserId());
    }
}
