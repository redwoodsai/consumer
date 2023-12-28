package com.redwoods.consumer.consumerservice.controllers;

import com.redwoods.consumer.consumerservice.dtos.FormDataDto;
import com.redwoods.consumer.consumerservice.dtos.FormDataUpdateDto;
import com.redwoods.consumer.consumerservice.models.FormDataResponse;
import com.redwoods.consumer.consumerservice.models.FormDesigner;
import com.redwoods.consumer.consumerservice.service.FormDesignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers/formdesigner")
public class FormDesignerController {
    @Autowired
    FormDesignService formDesignService;

    private final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);
    @PostMapping("/processdesigndata")
    public ResponseEntity<?>  processFormDesignerData(@RequestBody FormDataDto formDataDto) {
        try{
            FormDataResponse formDataResponse  = formDesignService.proceesFormData(formDataDto);
            formDataResponse.setMessage("Data inserted Successfully !!");
            return new ResponseEntity<>(formDataResponse, HttpStatus.OK);
        }catch (Exception ex){
            LOGGER.error("Error occurred while processing /suppliers", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/retriveprocessdata")
    public ResponseEntity<?>  retriveAllFormDesignerData() {
        try{
            List<FormDesigner> response = formDesignService.getAllData();
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No suppliers available!!", HttpStatus.OK);
            }
        }catch (Exception ex){
            LOGGER.error("Error occurred while processing form", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/retriveprocessdata/{id}")
    public ResponseEntity<?>  retriveByIdFormDesignerData(@PathVariable("id") Long id) {
        try{
            FormDesigner response = formDesignService.getById(id);
            if (response != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No suppliers available!!", HttpStatus.OK);
            }
        }catch (Exception ex){
            LOGGER.error("Error occurred while processing form", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateprocessdata")
    public ResponseEntity<?> updateSupplier(@RequestBody FormDataUpdateDto formDataDto) {
        try{
            String supplierResponseDto = formDesignService.updateData(formDataDto);
            if (supplierResponseDto != null)
                return new ResponseEntity<>(supplierResponseDto, HttpStatus.CREATED);
            else
                return new ResponseEntity<>(String.format("No Supplier exist in the database with provided id %s.", formDataDto.getId()), HttpStatus.OK);
        }catch (Exception ex){
            LOGGER.error("Error occurred while processing /suppliers/supplierId", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteprocessdata/{formid}")
    public ResponseEntity<String> deleteform(@PathVariable("formid") Long formId) {
        String message = formDesignService.deleteForm(formId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

