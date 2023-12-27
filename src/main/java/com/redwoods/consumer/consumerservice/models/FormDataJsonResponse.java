package com.redwoods.consumer.consumerservice.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class FormDataJsonResponse {

    private Long id;

    private String formId;

    private String metaData;

    private Integer version;
    private Long created_on ;
    private Long last_updated_on ;
    private String created_by ;
    private String last_updated_by;
    private boolean is_deleted;

}
