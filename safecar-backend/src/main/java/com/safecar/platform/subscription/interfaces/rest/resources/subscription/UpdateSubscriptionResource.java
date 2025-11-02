package com.safecar.platform.subscription.interfaces.rest.resources.subscription;

import java.util.Date;

public record UpdateSubscriptionResource(
        Date endDate,
        Long planId
) {
}
