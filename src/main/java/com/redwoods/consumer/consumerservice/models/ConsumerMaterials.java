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
@Table(name = "suppliers_materials")
public class ConsumerMaterials extends BaseModel {
    private String materialType;
    private String materialId;
    private String materialName;
    private String materialDetails;
    @ManyToOne
    @JoinColumn(name = "supplier_material_id", nullable = false)
    private Consumer consumer;
}
