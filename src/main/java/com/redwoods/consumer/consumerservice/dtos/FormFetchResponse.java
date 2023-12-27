package com.redwoods.consumer.consumerservice.dtos;

import com.redwoods.consumer.consumerservice.models.FormDesigner;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FormFetchResponse {
    List<FormDesigner> formDesigner;
}
