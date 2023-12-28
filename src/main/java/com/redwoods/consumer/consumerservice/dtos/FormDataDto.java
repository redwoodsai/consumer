package com.redwoods.consumer.consumerservice.dtos;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormDataDto  {

    private Long id;

    private String title;
    private int version;
    private String status;

    JsonNode metaData;

    //private Map<String, Object> metaData;


}
