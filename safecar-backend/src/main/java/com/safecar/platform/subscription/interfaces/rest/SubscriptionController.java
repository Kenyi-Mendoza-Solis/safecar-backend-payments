package com.safecar.platform.subscription.interfaces.rest;

import com.safecar.platform.subscription.domain.model.commands.DeleteSubscriptionCommand;
import com.safecar.platform.subscription.domain.model.commands.UpdateSubscriptionCommand;
import com.safecar.platform.subscription.domain.model.queries.GetPlanByIdQuery;
import com.safecar.platform.subscription.domain.model.queries.GetSubscriptionByUserIdQuery;
import com.safecar.platform.subscription.domain.services.PlanQueryService;
import com.safecar.platform.subscription.domain.services.SubscriptionCommandService;
import com.safecar.platform.subscription.domain.services.SubscriptionQueryService;
import com.safecar.platform.subscription.interfaces.rest.resources.subscription.CreateSubscriptionResource;
import com.safecar.platform.subscription.interfaces.rest.resources.subscription.GetSubscriptionByUserResource;
import com.safecar.platform.subscription.interfaces.rest.resources.subscription.SubscriptionResource;
import com.safecar.platform.subscription.interfaces.rest.resources.subscription.UpdateSubscriptionResource;
import com.safecar.platform.subscription.interfaces.rest.transformers.plan.PlanResourceFromEntityAssembler;
import com.safecar.platform.subscription.interfaces.rest.transformers.subscription.CreateSubscriptionCommandFromResourceAssembler;
import com.safecar.platform.subscription.interfaces.rest.transformers.subscription.SubscriptionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/subscription", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Subscription", description = "Subscription Management Endpoints")
public class SubscriptionController {

    private final SubscriptionCommandService subscriptionCommandService;
    private final SubscriptionQueryService subscriptionQueryService;

    private final PlanQueryService planQueryService;

    public SubscriptionController(SubscriptionCommandService subscriptionCommandService, SubscriptionQueryService subscriptionQueryService, PlanQueryService planQueryService) {
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
        this.planQueryService = planQueryService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionResource> createPlan(@RequestBody CreateSubscriptionResource resource) {

        var createSubscriptionCommand = CreateSubscriptionCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var subscription = subscriptionCommandService.handle(createSubscriptionCommand);
        if (subscription == null)
            return ResponseEntity.badRequest().build();

        var subscriptionResource = SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription);
        return new ResponseEntity<>(subscriptionResource, HttpStatus.CREATED);
    }

    @PutMapping("/{subscriptId}")
    public ResponseEntity<SubscriptionResource> createPlan(@PathVariable Long subscriptId, @RequestBody UpdateSubscriptionResource resource) {

        var updateSubscriptionCommand = new UpdateSubscriptionCommand(subscriptId, resource.endDate(), resource.planId());

        var subscription = subscriptionCommandService.handle(updateSubscriptionCommand);
        if (subscription == null)
            return ResponseEntity.badRequest().build();

        var subscriptionResource = SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription);
        return new ResponseEntity<>(subscriptionResource, HttpStatus.CREATED);
    }

    @DeleteMapping("/{subscriptId}")
    public ResponseEntity<?> deletePlan(@PathVariable Long subscriptId) {

        var deleteSubscriptionCommand = new DeleteSubscriptionCommand(subscriptId);

        subscriptionCommandService.handle(deleteSubscriptionCommand);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<GetSubscriptionByUserResource> getSubscriptionByOrganizerId(@PathVariable Long userId) {
        var query = new GetSubscriptionByUserIdQuery(userId);
        var subscription = subscriptionQueryService.handle(query);

        if (subscription == null)
            return ResponseEntity.badRequest().build();

        var plan = planQueryService.handle(new GetPlanByIdQuery(subscription.getPlan().getId()));

        var subscriptionResource = SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription);
        var planResource = PlanResourceFromEntityAssembler.toResourceFromEntity(plan);

        return ResponseEntity.ok(new GetSubscriptionByUserResource(
                subscriptionResource.id(),
                subscriptionResource.startDate(),
                subscriptionResource.endDate(),
                subscriptionResource.userId(),
                planResource
        ));
    }

}
