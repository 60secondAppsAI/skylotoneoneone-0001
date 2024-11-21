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

import com.skylotoneoneone.domain.Flag;
import com.skylotoneoneone.dto.FlagDTO;
import com.skylotoneoneone.dto.FlagSearchDTO;
import com.skylotoneoneone.dto.FlagPageDTO;
import com.skylotoneoneone.service.FlagService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/flag")
@RestController
public class FlagController {

	private final static Logger logger = LoggerFactory.getLogger(FlagController.class);

	@Autowired
	FlagService flagService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Flag> getAll() {

		List<Flag> flags = flagService.findAll();
		
		return flags;	
	}

	@GetMapping(value = "/{flagId}")
	@ResponseBody
	public FlagDTO getFlag(@PathVariable Integer flagId) {
		
		return (flagService.getFlagDTOById(flagId));
	}

 	@RequestMapping(value = "/addFlag", method = RequestMethod.POST)
	public ResponseEntity<?> addFlag(@RequestBody FlagDTO flagDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = flagService.addFlag(flagDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/flags")
	public ResponseEntity<FlagPageDTO> getFlags(FlagSearchDTO flagSearchDTO) {
 
		return flagService.getFlags(flagSearchDTO);
	}	

	@RequestMapping(value = "/updateFlag", method = RequestMethod.POST)
	public ResponseEntity<?> updateFlag(@RequestBody FlagDTO flagDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = flagService.updateFlag(flagDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
