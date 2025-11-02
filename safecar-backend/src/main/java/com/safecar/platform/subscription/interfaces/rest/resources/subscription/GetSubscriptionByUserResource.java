package com.safecar.platform.subscription.interfaces.rest.resources.subscription;

import com.safecar.platform.subscription.interfaces.rest.resources.plan.PlanResource;

import java.util.Date;

public record GetSubscriptionByUserResource(
        Long id,
        Date startDate,
        Date endDate,
        Long userId,
        PlanResource plan
) {
}
