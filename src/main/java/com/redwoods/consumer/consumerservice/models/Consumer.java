package com.redwoods.consumer.consumerservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consumers")
public class Consumer extends BaseModel {

	private String consumerName;
	private String UIdType;
	private String UIdValue;
	private String displayName;
	private String pcFirstName;
	private String pcLastName;
	private String pcEmail;
	private String pcMobilePhoneNumber;
	private String pcBusinessPhoneNumber;
	private String type;
	private String category;
	@Lob
	@Column(name = "logo")
	private byte[] logo;
	private String hqCountry;
	private String defLang;
	private String defCurrency;
	private Boolean isDataPrivacyEnabled;
	private String esgScore;
	private String badge;
	private Boolean isNetZeroCommitted;
	private Long netZeroCommitDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "consumer", orphanRemoval = true)
	@Setter(AccessLevel.NONE) private List<Address> address;

	@OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, orphanRemoval = true)
	@Setter(AccessLevel.NONE) private List<ConsumerData> consumerData;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "consumer_contact_id", referencedColumnName = "id")
	private ConsumerContact consumerContact;

	/*@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
	@Setter(AccessLevel.NONE)  private List<SupplierNetzero> supplierNetzero;*/

	/*@OneToMany(mappedBy = "supplier")
	private List<SupplierMaterials> supplierMaterials;*/

	public void setAddress(List<Address> address){
		this.address = address;
		address.forEach(entity -> entity.setConsumer(this));
	}

	public void setConsumerData(List<ConsumerData> consumerData){
		this.consumerData = consumerData;
		consumerData.forEach(entity -> entity.setConsumer(this));
	}

	/*public void setSupplierNetzero(List<SupplierNetzero> supplierNetzero){
		this.supplierNetzero = supplierNetzero;
		supplierNetzero.forEach(entity -> entity.setSupplier(this));
	}*/

	/*public void setSupplierMaterials(List<SupplierMaterials> supplierMaterials){
		this.supplierMaterials = supplierMaterials;
		supplierMaterials.forEach(entity -> entity.setSupplier(this));
	}*/
}
