package com.redwoods.consumer.consumerservice.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Form_Designer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class FormDesigner extends BaseModel {


    @Column(name = "Title", length = 10)
    private String title;


   @JsonManagedReference
   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "form_id")
    private FormDesignerJson formDesignerJson;

}

