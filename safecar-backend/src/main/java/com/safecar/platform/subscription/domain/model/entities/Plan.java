package com.safecar.platform.subscription.domain.model.entities;

import com.safecar.platform.shared.domain.model.entities.AuditableModel;
import com.safecar.platform.subscription.domain.model.commands.CreatePlanCommand;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Entity
public class Plan extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public Plan() {}

    public Plan(CreatePlanCommand command) {
        this.name = command.name();
        this.price = command.price();
        this.description = command.description();
    }
}
