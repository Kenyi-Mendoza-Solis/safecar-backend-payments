package com.safecar.platform.subscription.interfaces.rest.transformers.plan;

import com.safecar.platform.subscription.domain.model.commands.CreatePlanCommand;
import com.safecar.platform.subscription.interfaces.rest.resources.plan.CreatePlanResource;

public class CreatePlanCommandFromResourceAssembler {
    public static CreatePlanCommand toCommandFromResource(CreatePlanResource resource) {
        return new CreatePlanCommand(resource.name(), resource.description(), resource.price());
    }
}
