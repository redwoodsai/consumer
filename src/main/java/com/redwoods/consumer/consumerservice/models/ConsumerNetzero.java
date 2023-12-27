package com.redwoods.consumer.consumerservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suppliers_netzero")
public class ConsumerNetzero extends BaseModel {
    private Long dataTime;
    private String reportingPeriod;
    private Integer score;
    private Integer goalPlanned;
    private Integer goalAchieved;
    @ManyToOne
    @JoinColumn(name = "supplier_netzero_id", nullable = false)
    private Consumer consumer;
}
