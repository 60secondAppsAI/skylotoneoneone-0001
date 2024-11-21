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

import com.skylotoneoneone.domain.Notification;
import com.skylotoneoneone.dto.NotificationDTO;
import com.skylotoneoneone.dto.NotificationSearchDTO;
import com.skylotoneoneone.dto.NotificationPageDTO;
import com.skylotoneoneone.service.NotificationService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/notification")
@RestController
public class NotificationController {

	private final static Logger logger = LoggerFactory.getLogger(NotificationController.class);

	@Autowired
	NotificationService notificationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Notification> getAll() {

		List<Notification> notifications = notificationService.findAll();
		
		return notifications;	
	}

	@GetMapping(value = "/{notificationId}")
	@ResponseBody
	public NotificationDTO getNotification(@PathVariable Integer notificationId) {
		
		return (notificationService.getNotificationDTOById(notificationId));
	}

 	@RequestMapping(value = "/addNotification", method = RequestMethod.POST)
	public ResponseEntity<?> addNotification(@RequestBody NotificationDTO notificationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = notificationService.addNotification(notificationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/notifications")
	public ResponseEntity<NotificationPageDTO> getNotifications(NotificationSearchDTO notificationSearchDTO) {
 
		return notificationService.getNotifications(notificationSearchDTO);
	}	

	@RequestMapping(value = "/updateNotification", method = RequestMethod.POST)
	public ResponseEntity<?> updateNotification(@RequestBody NotificationDTO notificationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = notificationService.updateNotification(notificationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
