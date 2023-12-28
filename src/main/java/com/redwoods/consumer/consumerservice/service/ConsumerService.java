package com.redwoods.consumer.consumerservice.service;

import com.redwoods.consumer.consumerservice.dtos.ConsumerRequestDto;
import com.redwoods.consumer.consumerservice.dtos.ConsumerResponseDto;
import com.redwoods.consumer.consumerservice.models.Consumer;

import java.util.List;

public interface ConsumerService {

	ConsumerResponseDto getConsumer(Long supplierId);

	List<ConsumerResponseDto> getConsumer();

	String deleteConsumer(Long supplierId);

	ConsumerResponseDto updateConsumer(Long supplierId, ConsumerRequestDto supplierRequestDto);

	Long addConsumer(Consumer supplier);


}
