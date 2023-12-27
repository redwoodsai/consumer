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
			LOGGER.error("Error occurred while processing /consumers/consumerId", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("")
	public ResponseEntity<?> getAllConsumer() {
		try{
			List<ConsumerResponseDto> supplierDtos = consumerService.getConsumer();
			if (supplierDtos != null) {
				return new ResponseEntity<>(supplierDtos, HttpStatus.OK);
			}else{
				return new ResponseEntity<>("No consumers available!!", HttpStatus.OK);
			}
		}catch (Exception ex){
			LOGGER.error("Error occurred while processing consumers", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<String> addConsumer(@RequestBody ConsumerRequestDto consumerRequestDto) {
		try{
			Consumer consumer = modelMapper.map(consumerRequestDto, Consumer.class);
			Long consumerId = consumerService.addConsumer(consumer);
			return new ResponseEntity<>(String.format("New ConsumerId added %s successfully.", consumerId), HttpStatus.CREATED);
		}catch (Exception ex){
			LOGGER.error("Error occurred while processing consumers", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{consumerId}")
	public ResponseEntity<?> updateConsumer(@PathVariable("consumerId") Long consumerId,
															  @RequestBody ConsumerRequestDto supplierRequestDto) {
		try{
			ConsumerResponseDto consumerResponseDto = consumerService.updateConsumer(consumerId, supplierRequestDto);
			if (consumerResponseDto != null)
				return new ResponseEntity<>(consumerResponseDto, HttpStatus.CREATED);
			else
				return new ResponseEntity<>(String.format("No Supplier exist in the database with provided id %s.", consumerId), HttpStatus.OK);
		}catch (Exception ex){
			LOGGER.error("Error occurred while processing /consumerId/consumerId", ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{consumerId}")
	public ResponseEntity<String> deleteSupplier(@PathVariable("consumerId") Long consumerId) {
		String message = consumerService.deleteConsumer(consumerId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}


}
