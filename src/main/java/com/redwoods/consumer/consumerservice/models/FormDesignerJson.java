package com.redwoods.consumer.consumerservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Form_Designer_JSON")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class FormDesignerJson extends BaseModel {


    @Column(name = "FormID", length = 10)
    private String formId;

    @Column(name = "MetaData", length = 5000)
    private String metaData;

    @Column(name = "Version")
    private Integer version;

    @Column(name = "Status", length = 10)
    private String status;



  /*  @OneToOne
    @JoinColumn(name = "id")
    private FormDesigner formDesigner;*/

}

