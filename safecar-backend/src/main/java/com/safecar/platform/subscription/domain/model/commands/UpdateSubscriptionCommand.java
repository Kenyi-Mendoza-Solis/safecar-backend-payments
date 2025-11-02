package com.safecar.platform.subscription.domain.model.commands;

import java.util.Date;

public record UpdateSubscriptionCommand(
        Long subscriptionId,
        Date endDate,
        Long planId
) {
}
