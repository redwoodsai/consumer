package com.redwoods.consumer.consumerservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportFormDesginerJsonDto {
    private Long id;
    private String formId;

    private String metaData;

    private Integer version;
    private Long created_on;
    private Long last_updated_on ;
    private String created_by ;
    private String last_updated_by ;
    private boolean is_deleted;
}
