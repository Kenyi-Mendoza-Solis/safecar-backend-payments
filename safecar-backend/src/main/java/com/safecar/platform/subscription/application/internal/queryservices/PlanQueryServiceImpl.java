package com.safecar.platform.subscription.application.internal.queryservices;

import com.safecar.platform.subscription.domain.model.entities.Plan;
import com.safecar.platform.subscription.domain.model.queries.GetAllPlansQuery;
import com.safecar.platform.subscription.domain.model.queries.GetPlanByIdQuery;
import com.safecar.platform.subscription.domain.services.PlanQueryService;
import com.safecar.platform.subscription.infrastructure.persistence.jpa.repositories.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanQueryServiceImpl implements PlanQueryService {

    private final PlanRepository planRepository;

    public PlanQueryServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public Plan handle(GetPlanByIdQuery query) {
        return planRepository.findById(query.planId())
                .orElseThrow(() -> new IllegalArgumentException("Plan with ID " + query.planId() + " not found."));
    }

    @Override
    public List<Plan> handle(GetAllPlansQuery query) {
        return planRepository.findAll();
    }
}
