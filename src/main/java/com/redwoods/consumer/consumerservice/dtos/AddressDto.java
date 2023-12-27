package com.redwoods.consumer.consumerservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

	private String addressType;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String country;
	private String locale;
	private String currency;
}
