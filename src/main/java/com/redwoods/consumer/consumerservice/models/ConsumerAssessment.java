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
@Table(name = "suppliers_assessment")
public class ConsumerAssessment extends BaseModel {
    private Long supplierCustomerId;
    private Long start;
    private Long end;
    private String status;
    private Long lastCompletion;
    private Integer score;
    private String results;
    @ManyToOne
    @JoinColumn(name = "supplier_assessment_id", nullable = false)
    private Consumer consumer;
}
