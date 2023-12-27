package com.redwoods.consumer.consumerservice.controllers;

import com.redwoods.consumer.consumerservice.dtos.ConsumerRequestDto;
import com.redwoods.consumer.consumerservice.dtos.ConsumerResponseDto;
import com.redwoods.consumer.consumerservice.models.Consumer;
import com.redwoods.consumer.consumerservice.service.ConsumerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

	private ConsumerService consumerService;

	private ModelMapper modelMapper;

	private final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);

	public ConsumerController(ConsumerService consumerService, ModelMapper modelMapper) {
		this.consumerService = consumerService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/{consumerId}")
	public ResponseEntity<?> getConsumer(@PathVariable("consumerId") Long consumerId) {
		try{
			ConsumerResponseDto consumerResponseDto = consumerService.getConsumer(consumerId);
			return new ResponseEntity<>(consumerResponseDto, HttpStatus.OK);
		}catch (Exception ex){
			LOGGER.error("Error occurred while processing /suppliers/supplierId", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("")
	public ResponseEntity<?> getAllSuppliers() {
		try{
			List<ConsumerResponseDto> supplierDtos = consumerService.getConsumer();
			if (supplierDtos != null) {
				return new ResponseEntity<>(supplierDtos, HttpStatus.OK);
			}else{
				return new ResponseEntity<>("No suppliers available!!", HttpStatus.OK);
			}
		}catch (Exception ex){
			LOGGER.error("Error occurred while processing /suppliers", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<String> addSupplier(@RequestBody ConsumerRequestDto consumerRequestDto) {
		try{
			Consumer supplier = modelMapper.map(consumerRequestDto, Consumer.class);
			Long supplierId = consumerService.addConsumer(supplier);
			return new ResponseEntity<>(String.format("New supplier added %s successfully.", supplierId), HttpStatus.CREATED);
		}catch (Exception ex){
			LOGGER.error("Error occurred while processing /suppliers", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{supplierId}")
	public ResponseEntity<?> updateSupplier(@PathVariable("supplierId") Long supplierId,
															  @RequestBody ConsumerRequestDto supplierRequestDto) {
		try{
			ConsumerResponseDto supplierResponseDto = consumerService.updateConsumer(supplierId, supplierRequestDto);
			if (supplierResponseDto != null)
				return new ResponseEntity<>(supplierResponseDto, HttpStatus.CREATED);
			else
				return new ResponseEntity<>(String.format("No Supplier exist in the database with provided id %s.", supplierId), HttpStatus.OK);
		}catch (Exception ex){
			LOGGER.error("Error occurred while processing /suppliers/supplierId", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{supplierId}")
	public ResponseEntity<String> deleteSupplier(@PathVariable("supplierId") Long supplierId) {
		String message = consumerService.deleteConsumer(supplierId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}


}
