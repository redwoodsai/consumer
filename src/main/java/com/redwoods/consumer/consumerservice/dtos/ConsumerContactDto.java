package com.redwoods.consumer.consumerservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumerContactDto {
    private String firstName;
    private String lastName;
    private String email;
    private String workPhone;
    private String mobile;
}
