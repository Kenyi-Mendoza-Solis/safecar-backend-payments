package com.safecar.platform.subscription.interfaces.rest.resources.plan;

import java.math.BigDecimal;

public record PlanResource(
        Long id,
        String name,
        String description,
        BigDecimal price
) {
}
