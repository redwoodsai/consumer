package com.redwoods.consumer.consumerservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ConsumerResponseDto {

	private String supplierName;
	private String uIdType;
	private String uIdValue;
	private String displayName;
	private String pcFirstName;
	private String pcLastName;
	private String pcEmail;
	private String pcMobilePhoneNumber;
	private String pcBusinessPhoneNumber;
	private String hqCountry;
	private String defLang;
	private String defCurrency;
	private String type;
	private String category;
	private Boolean isDataPrivacyEnabled;
	private String esgScore;
	private String badge;
	private Boolean isNetZeroCommitted;
	private Date netZeroCommitDate;
	private List<AddressDto> address;
	private List<ConsumerDataDto> supplierData;
	private ConsumerContactDto consumerContactDto;
}
