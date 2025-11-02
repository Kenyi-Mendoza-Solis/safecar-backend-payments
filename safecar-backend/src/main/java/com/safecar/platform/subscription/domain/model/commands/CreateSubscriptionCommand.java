package com.safecar.platform.subscription.domain.model.commands;

import java.util.Date;

public record CreateSubscriptionCommand(
        Date startDate,
        Date endDate,
        Long planId,
        Long userId
) {
}
