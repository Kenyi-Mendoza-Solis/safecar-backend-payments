package com.safecar.platform.subscription.infrastructure.persistence.jpa.repositories;

import com.safecar.platform.subscription.domain.model.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    boolean existsByName(String name);
}
