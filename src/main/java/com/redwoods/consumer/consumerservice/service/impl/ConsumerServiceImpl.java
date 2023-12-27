package com.redwoods.consumer.consumerservice.service.impl;

import com.redwoods.consumer.consumerservice.dtos.ConsumerRequestDto;
import com.redwoods.consumer.consumerservice.dtos.ConsumerResponseDto;
import com.redwoods.consumer.consumerservice.exceptions.NotFoundException;
import com.redwoods.consumer.consumerservice.models.*;
import com.redwoods.consumer.consumerservice.repos.ConsumerRepository;
import com.redwoods.consumer.consumerservice.repos.FormDesignerJsonRepository;
import com.redwoods.consumer.consumerservice.repos.FormDesignerRepository;
import com.redwoods.consumer.consumerservice.service.ConsumerService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	private ConsumerRepository consumerRepository;

	private ModelMapper modelMapper;

	@Autowired
	FormDesignerJson formDesignerJson;

	@Autowired
	private FormDesignerJsonRepository formDesignerJsonRepository;

	@Autowired
	private FormDesignerRepository formDesignerRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerServiceImpl.class);

	public ConsumerServiceImpl(ConsumerRepository consumerRepository, ModelMapper modelMapper) {
		this.consumerRepository = consumerRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ConsumerResponseDto getConsumer(Long consumerId) throws NotFoundException {
		try{
			Optional<Consumer> optionalConsumer = consumerRepository.findById(consumerId);

			if (optionalConsumer.isEmpty()) {
				throw new NotFoundException("CONSUMER Doesn't exists.");
			}

			return modelMapper.map(optionalConsumer.get(), ConsumerResponseDto.class);
		}catch (Exception ex){
			LOGGER.error("Error occurred while processing getSupplier", ex);
			throw new RuntimeException("Error occurred while processing getSupplier", ex);
		}
	}

	@Override
	public List<ConsumerResponseDto> getConsumer() {
		try{
			List<Consumer> suppliers = consumerRepository.findAll();
			List<ConsumerResponseDto> consumerResponseDtos = null;
			if (!suppliers.isEmpty()) {
				consumerResponseDtos = new ArrayList<>();
				for (Consumer supplier : suppliers) {
					consumerResponseDtos.add(modelMapper.map(supplier, ConsumerResponseDto.class));
				}
			}
			return consumerResponseDtos;
		}catch (Exception ex){
			LOGGER.error("Error occurred while processing getSuppliers", ex);
			throw new RuntimeException("Error occurred while processing getSuppliers", ex);
		}
	}

	@Override
	public String deleteConsumer(Long consumerId) {
		if (consumerRepository.findById(consumerId).isPresent()) {
			consumerRepository.deleteById(consumerId);
			return "Consumer Deleted Successfully!!";
		}

		return "No Consumer exist in the database with provided id..";
	}

	@Override
	public ConsumerResponseDto updateConsumer(Long consumerId, ConsumerRequestDto consumerRequestDto) {
		try{
			Optional<Consumer> optionalConsumer = consumerRepository.findById(consumerId);
			if (optionalConsumer.isPresent()) {
				Consumer supplier = optionalConsumer.get();
				supplier.setSupplierName(consumerRequestDto.getSupplierName());
				supplier.setBadge(consumerRequestDto.getBadge());
				supplier.setDefCurrency(consumerRequestDto.getDefCurrency());
				supplier.setDefLang(consumerRequestDto.getDefLang());
				supplier.setDisplayName(consumerRequestDto.getDisplayName());
				supplier.setEsgScore(consumerRequestDto.getEsgScore());
				supplier.setHqCountry(consumerRequestDto.getHqCountry());
				supplier.setIsDataPrivacyEnabled(consumerRequestDto.getIsDataPrivacyEnabled());
				supplier.setIsNetZeroCommitted(consumerRequestDto.getIsNetZeroCommitted());
				supplier.setLogo(consumerRequestDto.getLogo());
				supplier.setNetZeroCommitDate(consumerRequestDto.getNetZeroCommitDate().toInstant().toEpochMilli());
				supplier.setPcBusinessPhoneNumber(consumerRequestDto.getPcBusinessPhoneNumber());
				supplier.setPcMobilePhoneNumber(consumerRequestDto.getPcMobilePhoneNumber());
				supplier.setUIdType(consumerRequestDto.getUIdType());
				supplier.setUIdValue(consumerRequestDto.getUIdValue());
				supplier.setPcFirstName(consumerRequestDto.getPcFirstName());
				supplier.setPcLastName(consumerRequestDto.getPcLastName());
				supplier.setPcEmail(consumerRequestDto.getPcEmail());

				if(consumerRequestDto.getSupplierContact() != null)
				{
					ConsumerContact supplierContact = modelMapper.map(consumerRequestDto.getSupplierContact(), ConsumerContact.class);
					supplier.setConsumerContact(supplierContact);
				}


				// update address
				if(consumerRequestDto.getAddress() != null){
					List<Address> addressList = List.of(modelMapper.map(consumerRequestDto.getAddress(), Address[].class));
					supplier.setAddress(addressList);
				}

				// update Supplier data
				if(consumerRequestDto.getSupplierData() != null){
					List<ConsumerData> supplierDataList = List.of(modelMapper.map(consumerRequestDto.getSupplierData(), ConsumerData[].class));
					supplier.setConsumerData(supplierDataList);
				}

				supplier.setLast_updated_by("admin");
				supplier.setLast_updated_on(System.currentTimeMillis());

				return modelMapper.map(consumerRepository.save(supplier), ConsumerResponseDto.class);
			}else{
				return null;
			}
		}catch (Exception ex){
			LOGGER.error("Error occurred while processing updateSuppliers", ex);
			throw new RuntimeException("Error occurred while processing updateSuppliers", ex);
		}
    }

	@Override
	public Long addConsumer(Consumer consumer) {
		try {
			Consumer newConsumer = consumerRepository.save(consumer);
			return newConsumer.getId();
		}catch (Exception ex){
			LOGGER.error("Error occurred while processing addSuppliers", ex);
			throw new RuntimeException("Error occurred while processing addSuppliers", ex);
		}
	}

	}


