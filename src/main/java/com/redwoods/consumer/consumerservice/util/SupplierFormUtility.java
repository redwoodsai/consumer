package com.redwoods.consumer.consumerservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redwoods.consumer.consumerservice.dtos.MetaDataItemDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SupplierFormUtility {
    public static String convertListToJsonString(List<MetaDataItemDto> metaDataList) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert the list to a JSON string
            return objectMapper.writeValueAsString(metaDataList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return e.getMessage();
        }
    }

    public static String mapToString(Map<String, Object> map) {
        return map.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":" + objectToString(entry.getValue()))
                .collect(Collectors.joining(",", "{", "}"));
    }

    private static String objectToString(Object obj) {
        if (obj instanceof Map) {
            return mapToString((Map<String, Object>) obj);
        } else if (obj instanceof String) {
            return "\"" + obj + "\"";
        } else {
            return String.valueOf(obj);
        }
    }

    public static String jsonNodeToString(JsonNode jsonNode) {
        // Using Jackson's ObjectMapper to convert JsonNode to String
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(jsonNode);
        } catch (Exception e) {
            // Handle the exception, e.g., log it or throw a RuntimeException
            e.printStackTrace();
            return null;
        }
    }
}
