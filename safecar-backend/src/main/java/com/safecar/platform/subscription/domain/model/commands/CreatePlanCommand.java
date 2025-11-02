package com.safecar.platform.subscription.domain.model.commands;

import java.math.BigDecimal;

public record CreatePlanCommand(
        String name,
        String description,
        BigDecimal price
) {
}
