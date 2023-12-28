package com.redwoods.consumer.consumerservice.dtos;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class FormDataUpdateDto {
    private Long id;
    private String title;
    private String updatedBy;
    JsonNode metaData;
}