package com.skylotoneoneone.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShippingDetailSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer shippingDetailId;
	
	private String address;
	
	private String city;
	
	private String postalCode;
	
	private String country;
	
}
