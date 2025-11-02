package com.safecar.platform.subscription.interfaces.rest.transformers.subscription;

import com.safecar.platform.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.safecar.platform.subscription.interfaces.rest.resources.subscription.CreateSubscriptionResource;

public class CreateSubscriptionCommandFromResourceAssembler {
    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand(resource.startDate(), resource.endDate(), resource.planId(), resource.userId());
    }
}
