package com.safecar.platform.subscription.domain.model.aggregates;

import com.safecar.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.safecar.platform.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.safecar.platform.subscription.domain.model.commands.UpdateSubscriptionCommand;
import com.safecar.platform.subscription.domain.model.entities.Plan;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class Subscription extends AuditableAbstractAggregateRoot<Subscription> {

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Subscription() {
    }

    public Subscription(CreateSubscriptionCommand command, Plan plan) {
        this.startDate = command.startDate();
        this.endDate = command.endDate();
        this.plan = plan;
        this.userId = command.userId();
    }

    public Subscription updateSubscription(UpdateSubscriptionCommand command, Plan plan) {
        this.endDate = command.endDate();
        this.plan = plan;

        return this;
    }

}

