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

import com.skylotoneoneone.domain.Alert;
import com.skylotoneoneone.dto.AlertDTO;
import com.skylotoneoneone.dto.AlertSearchDTO;
import com.skylotoneoneone.dto.AlertPageDTO;
import com.skylotoneoneone.service.AlertService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/alert")
@RestController
public class AlertController {

	private final static Logger logger = LoggerFactory.getLogger(AlertController.class);

	@Autowired
	AlertService alertService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Alert> getAll() {

		List<Alert> alerts = alertService.findAll();
		
		return alerts;	
	}

	@GetMapping(value = "/{alertId}")
	@ResponseBody
	public AlertDTO getAlert(@PathVariable Integer alertId) {
		
		return (alertService.getAlertDTOById(alertId));
	}

 	@RequestMapping(value = "/addAlert", method = RequestMethod.POST)
	public ResponseEntity<?> addAlert(@RequestBody AlertDTO alertDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = alertService.addAlert(alertDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/alerts")
	public ResponseEntity<AlertPageDTO> getAlerts(AlertSearchDTO alertSearchDTO) {
 
		return alertService.getAlerts(alertSearchDTO);
	}	

	@RequestMapping(value = "/updateAlert", method = RequestMethod.POST)
	public ResponseEntity<?> updateAlert(@RequestBody AlertDTO alertDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = alertService.updateAlert(alertDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
