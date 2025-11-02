package com.safecar.platform.subscription.interfaces.rest.transformers.plan;

import com.safecar.platform.subscription.domain.model.entities.Plan;
import com.safecar.platform.subscription.interfaces.rest.resources.plan.PlanResource;

public class PlanResourceFromEntityAssembler {
    public static PlanResource toResourceFromEntity(Plan entity) {
        return new PlanResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice());
    }
}
