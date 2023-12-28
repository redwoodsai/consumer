package com.redwoods.consumer.consumerservice.service;

import com.redwoods.consumer.consumerservice.dtos.FormDataDto;
import com.redwoods.consumer.consumerservice.dtos.FormDataUpdateDto;
import com.redwoods.consumer.consumerservice.dtos.FormFetchResponse;
import com.redwoods.consumer.consumerservice.models.FormDataResponse;
import com.redwoods.consumer.consumerservice.models.FormDesigner;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FormDesignService {

    FormDataResponse proceesFormData(FormDataDto formDesigner);
    public FormFetchResponse getDataById(Long formId);


    List<FormDesigner> getAllData();

    FormDesigner getById(Long id);

    String updateData(FormDataUpdateDto formDataDto);

    String deleteForm(Long formId);




}
