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

import com.skylotoneoneone.domain.Report;
import com.skylotoneoneone.dto.ReportDTO;
import com.skylotoneoneone.dto.ReportSearchDTO;
import com.skylotoneoneone.dto.ReportPageDTO;
import com.skylotoneoneone.service.ReportService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/report")
@RestController
public class ReportController {

	private final static Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	ReportService reportService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Report> getAll() {

		List<Report> reports = reportService.findAll();
		
		return reports;	
	}

	@GetMapping(value = "/{reportId}")
	@ResponseBody
	public ReportDTO getReport(@PathVariable Integer reportId) {
		
		return (reportService.getReportDTOById(reportId));
	}

 	@RequestMapping(value = "/addReport", method = RequestMethod.POST)
	public ResponseEntity<?> addReport(@RequestBody ReportDTO reportDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = reportService.addReport(reportDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/reports")
	public ResponseEntity<ReportPageDTO> getReports(ReportSearchDTO reportSearchDTO) {
 
		return reportService.getReports(reportSearchDTO);
	}	

	@RequestMapping(value = "/updateReport", method = RequestMethod.POST)
	public ResponseEntity<?> updateReport(@RequestBody ReportDTO reportDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = reportService.updateReport(reportDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
