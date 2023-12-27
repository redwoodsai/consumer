package com.redwoods.consumer.consumerservice.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class FormDataResponse {

    private Long id;

    private String title;
    private int version;
    private String status;
    private String Message;
}
