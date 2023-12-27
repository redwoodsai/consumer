package com.redwoods.consumer.consumerservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
@Table(name = "suppliers_contact")
public class ConsumerContact extends BaseModel {
    private String firstName;
    private String lastName;
    private String email;
    private String workPhone;
    private String mobile;
    @OneToOne(mappedBy = "supplierContact")
    private Consumer consumer;
}
