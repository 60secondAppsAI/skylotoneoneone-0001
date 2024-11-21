package com.skylotoneoneone.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skylotoneoneone.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skylotoneoneone.domain.ShippingDetail;
import com.skylotoneoneone.dto.ShippingDetailDTO;
import com.skylotoneoneone.dto.ShippingDetailSearchDTO;
import com.skylotoneoneone.dto.ShippingDetailPageDTO;
import com.skylotoneoneone.service.ShippingDetailService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/shippingDetail")
@RestController
public class ShippingDetailController {

	private final static Logger logger = LoggerFactory.getLogger(ShippingDetailController.class);

	@Autowired
	ShippingDetailService shippingDetailService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ShippingDetail> getAll() {

		List<ShippingDetail> shippingDetails = shippingDetailService.findAll();
		
		return shippingDetails;	
	}

	@GetMapping(value = "/{shippingDetailId}")
	@ResponseBody
	public ShippingDetailDTO getShippingDetail(@PathVariable Integer shippingDetailId) {
		
		return (shippingDetailService.getShippingDetailDTOById(shippingDetailId));
	}

 	@RequestMapping(value = "/addShippingDetail", method = RequestMethod.POST)
	public ResponseEntity<?> addShippingDetail(@RequestBody ShippingDetailDTO shippingDetailDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = shippingDetailService.addShippingDetail(shippingDetailDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/shippingDetails")
	public ResponseEntity<ShippingDetailPageDTO> getShippingDetails(ShippingDetailSearchDTO shippingDetailSearchDTO) {
 
		return shippingDetailService.getShippingDetails(shippingDetailSearchDTO);
	}	

	@RequestMapping(value = "/updateShippingDetail", method = RequestMethod.POST)
	public ResponseEntity<?> updateShippingDetail(@RequestBody ShippingDetailDTO shippingDetailDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = shippingDetailService.updateShippingDetail(shippingDetailDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
