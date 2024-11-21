package com.skylotoneoneone.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="shipping_details")
@Getter @Setter @NoArgsConstructor
public class ShippingDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="shipping_detail_id")
	private Integer shippingDetailId;
    
  	@Column(name="address")
	private String address;
    
  	@Column(name="city")
	private String city;
    
  	@Column(name="postal_code")
	private String postalCode;
    
  	@Column(name="country")
	private String country;
    
	




}
