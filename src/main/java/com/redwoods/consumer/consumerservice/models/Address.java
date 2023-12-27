package com.redwoods.consumer.consumerservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address extends BaseModel {

	private String addressType;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String country;
	private String locale;
	private String currency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consumer_id")
	private Consumer consumer;
}
