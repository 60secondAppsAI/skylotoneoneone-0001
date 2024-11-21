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

import com.skylotoneoneone.domain.Session;
import com.skylotoneoneone.dto.SessionDTO;
import com.skylotoneoneone.dto.SessionSearchDTO;
import com.skylotoneoneone.dto.SessionPageDTO;
import com.skylotoneoneone.service.SessionService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/session")
@RestController
public class SessionController {

	private final static Logger logger = LoggerFactory.getLogger(SessionController.class);

	@Autowired
	SessionService sessionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Session> getAll() {

		List<Session> sessions = sessionService.findAll();
		
		return sessions;	
	}

	@GetMapping(value = "/{sessionId}")
	@ResponseBody
	public SessionDTO getSession(@PathVariable Integer sessionId) {
		
		return (sessionService.getSessionDTOById(sessionId));
	}

 	@RequestMapping(value = "/addSession", method = RequestMethod.POST)
	public ResponseEntity<?> addSession(@RequestBody SessionDTO sessionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sessionService.addSession(sessionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/sessions")
	public ResponseEntity<SessionPageDTO> getSessions(SessionSearchDTO sessionSearchDTO) {
 
		return sessionService.getSessions(sessionSearchDTO);
	}	

	@RequestMapping(value = "/updateSession", method = RequestMethod.POST)
	public ResponseEntity<?> updateSession(@RequestBody SessionDTO sessionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sessionService.updateSession(sessionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
