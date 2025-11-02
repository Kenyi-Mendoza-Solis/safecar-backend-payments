package com.safecar.platform.subscription.domain.services;

import com.safecar.platform.subscription.domain.model.entities.Plan;
import com.safecar.platform.subscription.domain.model.queries.GetAllPlansQuery;
import com.safecar.platform.subscription.domain.model.queries.GetPlanByIdQuery;

import java.util.List;

public interface PlanQueryService {
    Plan handle(GetPlanByIdQuery query);

    List<Plan> handle(GetAllPlansQuery query);
}
