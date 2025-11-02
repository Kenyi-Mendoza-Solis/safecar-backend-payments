package com.safecar.platform.subscription.domain.services;

import com.safecar.platform.subscription.domain.model.commands.CreatePlanCommand;
import com.safecar.platform.subscription.domain.model.entities.Plan;

public interface PlanCommandService {
    Plan handle(CreatePlanCommand command);
}
