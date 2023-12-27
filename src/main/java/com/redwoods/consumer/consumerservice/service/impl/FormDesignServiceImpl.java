package com.redwoods.consumer.consumerservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redwoods.consumer.consumerservice.dtos.*;
import com.redwoods.consumer.consumerservice.models.FormDataResponse;
import com.redwoods.consumer.consumerservice.models.FormDesigner;
import com.redwoods.consumer.consumerservice.models.FormDesignerJson;
import com.redwoods.consumer.consumerservice.repos.FormDesignerJsonRepository;
import com.redwoods.consumer.consumerservice.repos.FormDesignerRepository;
import com.redwoods.consumer.consumerservice.service.FormDesignService;
import com.redwoods.consumer.consumerservice.util.SupplierFormUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormDesignServiceImpl implements FormDesignService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormDesignServiceImpl.class);

    @Autowired
    private FormDesignerRepository formDesignerRepository;

    @Autowired
    private FormDesignerJsonRepository formDesignerJsonRepository;
    @Autowired
    FormDesigner formDesigner;
    @Autowired
    FormDesignerJson formDesignerJson;
    @Autowired
    FormDataResponse formDataResponse;


    @Override
    public FormDataResponse proceesFormData(FormDataDto formDataDto) {
        formDesignerJson.setFormId("11");
        formDesignerJson.setVersion(formDataDto.getVersion());
       // Map<String,Object>  map = formDataDto.getMetaData();
        String metaDataValue = SupplierFormUtility.jsonNodeToString(formDataDto.getMetaData());
       formDesignerJson.setMetaData(metaDataValue);
        formDesigner.setTitle(formDataDto.getTitle());
       // formDesigner.setStatus(formDataDto.getStatus());
        FormDesigner formDesigner1 = formDesignerRepository.save(formDesigner);
        Long id = formDesigner1.getId();
        formDesignerJson.setFormId(String.valueOf(id));
        formDesignerJson.setStatus(formDataDto.getStatus());
        FormDesignerJson formDesignerJson1 = formDesignerJsonRepository.save(formDesignerJson);
        formDataResponse.setId(id);
        formDataResponse.setTitle(formDesigner1.getTitle());
        formDataResponse.setStatus(formDataDto.getStatus());
        formDataResponse.setVersion(formDesignerJson1.getVersion());

        return formDataResponse;

    }

    public FormDesigner getById(Long formId) {

        try {
            Optional<FormDesigner> formDesigner = formDesignerRepository.findById(formId);
            FormDesignerJson formDesignerJson1 = formDesignerJsonRepository.getData(formId);
            formDesigner.get().setFormDesignerJson(formDesignerJson1);

            return formDesigner.get();
        } catch (Exception ex) {
            LOGGER.error("Error occurred while processing forms", ex);
            throw new RuntimeException("Error occurred while processing fprms", ex);
        }

    }

    public List<FormDesigner> getAllData() {

        try {
            List<FormDesigner> formDesigner = formDesignerRepository.findAll();
            for(FormDesigner formDesigner1 : formDesigner){
                Long formId = formDesigner1.getId();
                FormDesignerJson formDesignerJson1 = formDesignerJsonRepository.getData(formId);
                formDesigner1.setFormDesignerJson(formDesignerJson1);

            }



            return formDesigner;
        } catch (Exception ex) {
            LOGGER.error("Error occurred while processing forms", ex);
            throw new RuntimeException("Error occurred while processing fprms", ex);
        }
    }

    public FormFetchResponse getDataById(Long id) {

        try {
            List<FormDesigner> formDesigner = formDesignerRepository.findAll();
            FormFetchResponse response = new FormFetchResponse();
            response.setFormDesigner(formDesigner);



            return response;
        } catch (Exception ex) {
            LOGGER.error("Error occurred while processing forms", ex);
            throw new RuntimeException("Error occurred while processing fprms", ex);
        }
    }

    public String updateData(Long formId, FormDataDto formDataDto) {

        if (formDesignerRepository.findById(formId).isPresent()) {
            formDesignerRepository.deleteById(formId);
            return "form Deleted Successfully!!";
        }

        return "No Supplier exist in the database with provided id..";
    }

    public String DeleteData(Long formId) {

        if (formDesignerRepository.findById(formId).isPresent()) {
            formDesignerRepository.deleteById(formId);
            return "form Deleted Successfully!!";
        }

        return "No Supplier exist in the database with provided id..";
    }


    private static String convertListToJsonString(List<MetaDataItemDto> metaDataList) {
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
    @Override
    public String updateData(FormDataUpdateDto formDataDto) {
        try {
            Optional<FormDesignerJson> formDesignerJsonOptional = formDesignerJsonRepository.findByFormId(formDataDto.getId());
            Optional<FormDesigner> formDesignerOptional = formDesignerRepository.findById(formDataDto.getId());


            if (formDesignerJsonOptional.isPresent()) {
                FormDesignerJson existingFormDesignerJson = formDesignerJsonOptional.get();
                FormDesigner existingFormDesigner = formDesignerOptional.get();

//                // Set the status of the existing record to "inactive"
                existingFormDesignerJson.setStatus("inactive");
                formDesignerJsonRepository.save(existingFormDesignerJson);
//
//                // Create a new FormDesignerJson instance
                FormDesignerJson newFormDesignerJson = new FormDesignerJson();
                newFormDesignerJson.setVersion(existingFormDesignerJson.getVersion() + 1);
                newFormDesignerJson.setStatus("active");
                newFormDesignerJson.setLast_updated_by(formDataDto.getUpdatedBy());
                newFormDesignerJson.setFormId(String.valueOf(existingFormDesignerJson.getFormId()));


                String metaDataValue = SupplierFormUtility.jsonNodeToString(formDataDto.getMetaData());
                newFormDesignerJson.setMetaData(metaDataValue);
                formDesignerJsonRepository.save(newFormDesignerJson);

                newFormDesignerJson.setFormId(existingFormDesignerJson.getFormId());
                formDesignerJsonRepository.save(newFormDesignerJson);
//
//                // Update the FormDesigner record with the new FormDesignerJson
                FormDesigner newFormDesigner = new FormDesigner();
                newFormDesigner.setTitle(formDataDto.getTitle()+"-Custom");
                newFormDesigner.setLast_updated_by(formDataDto.getUpdatedBy());
//                newFormDesigner.setFormDesignerJson(newFormDesignerJson);
                formDesignerRepository.save(newFormDesigner);

                return "Records saved successfully....!";
            } else {
                return "Record not found for the provided ID.";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Failed to save records.....!";
        }
    }

    private String convertMetadataListToString(List<ConsumerDataDto> metadataList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(metadataList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String deleteForm(Long formId) {
        if (formDesignerJsonRepository.findById(formId).isPresent()) {
            formDesignerJsonRepository.deleteById(formId);
            formDesignerRepository.deleteById(formId);
            return "Supplier Deleted Successfully!!";
        }
        return "No Supplier exist in the database with provided id..";
    }
}
