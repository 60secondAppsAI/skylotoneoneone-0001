package com.skylotoneoneone.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.service.GenericService;
import com.skylotoneoneone.service.impl.GenericServiceImpl;
import com.skylotoneoneone.dao.AlertDAO;
import com.skylotoneoneone.domain.Alert;
import com.skylotoneoneone.dto.AlertDTO;
import com.skylotoneoneone.dto.AlertSearchDTO;
import com.skylotoneoneone.dto.AlertPageDTO;
import com.skylotoneoneone.dto.AlertConvertCriteriaDTO;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import com.skylotoneoneone.service.AlertService;
import com.skylotoneoneone.util.ControllerUtils;





@Service
public class AlertServiceImpl extends GenericServiceImpl<Alert, Integer> implements AlertService {

    private final static Logger logger = LoggerFactory.getLogger(AlertServiceImpl.class);

	@Autowired
	AlertDAO alertDao;

	


	@Override
	public GenericDAO<Alert, Integer> getDAO() {
		return (GenericDAO<Alert, Integer>) alertDao;
	}
	
	public List<Alert> findAll () {
		List<Alert> alerts = alertDao.findAll();
		
		return alerts;	
		
	}

	public ResultDTO addAlert(AlertDTO alertDTO, RequestDTO requestDTO) {

		Alert alert = new Alert();

		alert.setAlertId(alertDTO.getAlertId());


		alert.setTitle(alertDTO.getTitle());


		alert.setMessage(alertDTO.getMessage());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		alert = alertDao.save(alert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Alert> getAllAlerts(Pageable pageable) {
		return alertDao.findAll(pageable);
	}

	public Page<Alert> getAllAlerts(Specification<Alert> spec, Pageable pageable) {
		return alertDao.findAll(spec, pageable);
	}

	public ResponseEntity<AlertPageDTO> getAlerts(AlertSearchDTO alertSearchDTO) {
	
			Integer alertId = alertSearchDTO.getAlertId(); 
 			String title = alertSearchDTO.getTitle(); 
 			String message = alertSearchDTO.getMessage(); 
 			String sortBy = alertSearchDTO.getSortBy();
			String sortOrder = alertSearchDTO.getSortOrder();
			String searchQuery = alertSearchDTO.getSearchQuery();
			Integer page = alertSearchDTO.getPage();
			Integer size = alertSearchDTO.getSize();

	        Specification<Alert> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, alertId, "alertId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, title, "title"); 
			
			spec = ControllerUtils.andIfNecessary(spec, message, "message"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("title")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("message")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Alert> alerts = this.getAllAlerts(spec, pageable);
		
		//System.out.println(String.valueOf(alerts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(alerts.getTotalPages()));
		
		List<Alert> alertsList = alerts.getContent();
		
		AlertConvertCriteriaDTO convertCriteria = new AlertConvertCriteriaDTO();
		List<AlertDTO> alertDTOs = this.convertAlertsToAlertDTOs(alertsList,convertCriteria);
		
		AlertPageDTO alertPageDTO = new AlertPageDTO();
		alertPageDTO.setAlerts(alertDTOs);
		alertPageDTO.setTotalElements(alerts.getTotalElements());
		return ResponseEntity.ok(alertPageDTO);
	}

	public List<AlertDTO> convertAlertsToAlertDTOs(List<Alert> alerts, AlertConvertCriteriaDTO convertCriteria) {
		
		List<AlertDTO> alertDTOs = new ArrayList<AlertDTO>();
		
		for (Alert alert : alerts) {
			alertDTOs.add(convertAlertToAlertDTO(alert,convertCriteria));
		}
		
		return alertDTOs;

	}
	
	public AlertDTO convertAlertToAlertDTO(Alert alert, AlertConvertCriteriaDTO convertCriteria) {
		
		AlertDTO alertDTO = new AlertDTO();
		
		alertDTO.setAlertId(alert.getAlertId());

	
		alertDTO.setTitle(alert.getTitle());

	
		alertDTO.setMessage(alert.getMessage());

	

		
		return alertDTO;
	}

	public ResultDTO updateAlert(AlertDTO alertDTO, RequestDTO requestDTO) {
		
		Alert alert = alertDao.getById(alertDTO.getAlertId());

		alert.setAlertId(ControllerUtils.setValue(alert.getAlertId(), alertDTO.getAlertId()));

		alert.setTitle(ControllerUtils.setValue(alert.getTitle(), alertDTO.getTitle()));

		alert.setMessage(ControllerUtils.setValue(alert.getMessage(), alertDTO.getMessage()));



        alert = alertDao.save(alert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AlertDTO getAlertDTOById(Integer alertId) {
	
		Alert alert = alertDao.getById(alertId);
			
		
		AlertConvertCriteriaDTO convertCriteria = new AlertConvertCriteriaDTO();
		return(this.convertAlertToAlertDTO(alert,convertCriteria));
	}







}
