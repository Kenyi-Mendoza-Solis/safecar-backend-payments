package com.safecar.platform.subscription.interfaces.rest.resources.plan;

import java.math.BigDecimal;

public record CreatePlanResource(
        String name,
        String description,
        BigDecimal price
) {
}
