package com.safecar.platform.subscription.domain.services;

import com.safecar.platform.subscription.domain.model.aggregates.Subscription;
import com.safecar.platform.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.safecar.platform.subscription.domain.model.commands.DeleteSubscriptionCommand;
import com.safecar.platform.subscription.domain.model.commands.UpdateSubscriptionCommand;

public interface SubscriptionCommandService {
    Subscription handle(CreateSubscriptionCommand command);
    Subscription handle(UpdateSubscriptionCommand command);
    void handle(DeleteSubscriptionCommand command);
}
