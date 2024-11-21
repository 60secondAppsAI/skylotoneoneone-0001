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
import com.skylotoneoneone.dao.PaymentMethodDAO;
import com.skylotoneoneone.domain.PaymentMethod;
import com.skylotoneoneone.dto.PaymentMethodDTO;
import com.skylotoneoneone.dto.PaymentMethodSearchDTO;
import com.skylotoneoneone.dto.PaymentMethodPageDTO;
import com.skylotoneoneone.dto.PaymentMethodConvertCriteriaDTO;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import com.skylotoneoneone.service.PaymentMethodService;
import com.skylotoneoneone.util.ControllerUtils;





@Service
public class PaymentMethodServiceImpl extends GenericServiceImpl<PaymentMethod, Integer> implements PaymentMethodService {

    private final static Logger logger = LoggerFactory.getLogger(PaymentMethodServiceImpl.class);

	@Autowired
	PaymentMethodDAO paymentMethodDao;

	


	@Override
	public GenericDAO<PaymentMethod, Integer> getDAO() {
		return (GenericDAO<PaymentMethod, Integer>) paymentMethodDao;
	}
	
	public List<PaymentMethod> findAll () {
		List<PaymentMethod> paymentMethods = paymentMethodDao.findAll();
		
		return paymentMethods;	
		
	}

	public ResultDTO addPaymentMethod(PaymentMethodDTO paymentMethodDTO, RequestDTO requestDTO) {

		PaymentMethod paymentMethod = new PaymentMethod();

		paymentMethod.setPaymentMethodId(paymentMethodDTO.getPaymentMethodId());


		paymentMethod.setMethodName(paymentMethodDTO.getMethodName());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		paymentMethod = paymentMethodDao.save(paymentMethod);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PaymentMethod> getAllPaymentMethods(Pageable pageable) {
		return paymentMethodDao.findAll(pageable);
	}

	public Page<PaymentMethod> getAllPaymentMethods(Specification<PaymentMethod> spec, Pageable pageable) {
		return paymentMethodDao.findAll(spec, pageable);
	}

	public ResponseEntity<PaymentMethodPageDTO> getPaymentMethods(PaymentMethodSearchDTO paymentMethodSearchDTO) {
	
			Integer paymentMethodId = paymentMethodSearchDTO.getPaymentMethodId(); 
 			String methodName = paymentMethodSearchDTO.getMethodName(); 
 			String sortBy = paymentMethodSearchDTO.getSortBy();
			String sortOrder = paymentMethodSearchDTO.getSortOrder();
			String searchQuery = paymentMethodSearchDTO.getSearchQuery();
			Integer page = paymentMethodSearchDTO.getPage();
			Integer size = paymentMethodSearchDTO.getSize();

	        Specification<PaymentMethod> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, paymentMethodId, "paymentMethodId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, methodName, "methodName"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("methodName")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<PaymentMethod> paymentMethods = this.getAllPaymentMethods(spec, pageable);
		
		//System.out.println(String.valueOf(paymentMethods.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(paymentMethods.getTotalPages()));
		
		List<PaymentMethod> paymentMethodsList = paymentMethods.getContent();
		
		PaymentMethodConvertCriteriaDTO convertCriteria = new PaymentMethodConvertCriteriaDTO();
		List<PaymentMethodDTO> paymentMethodDTOs = this.convertPaymentMethodsToPaymentMethodDTOs(paymentMethodsList,convertCriteria);
		
		PaymentMethodPageDTO paymentMethodPageDTO = new PaymentMethodPageDTO();
		paymentMethodPageDTO.setPaymentMethods(paymentMethodDTOs);
		paymentMethodPageDTO.setTotalElements(paymentMethods.getTotalElements());
		return ResponseEntity.ok(paymentMethodPageDTO);
	}

	public List<PaymentMethodDTO> convertPaymentMethodsToPaymentMethodDTOs(List<PaymentMethod> paymentMethods, PaymentMethodConvertCriteriaDTO convertCriteria) {
		
		List<PaymentMethodDTO> paymentMethodDTOs = new ArrayList<PaymentMethodDTO>();
		
		for (PaymentMethod paymentMethod : paymentMethods) {
			paymentMethodDTOs.add(convertPaymentMethodToPaymentMethodDTO(paymentMethod,convertCriteria));
		}
		
		return paymentMethodDTOs;

	}
	
	public PaymentMethodDTO convertPaymentMethodToPaymentMethodDTO(PaymentMethod paymentMethod, PaymentMethodConvertCriteriaDTO convertCriteria) {
		
		PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
		
		paymentMethodDTO.setPaymentMethodId(paymentMethod.getPaymentMethodId());

	
		paymentMethodDTO.setMethodName(paymentMethod.getMethodName());

	

		
		return paymentMethodDTO;
	}

	public ResultDTO updatePaymentMethod(PaymentMethodDTO paymentMethodDTO, RequestDTO requestDTO) {
		
		PaymentMethod paymentMethod = paymentMethodDao.getById(paymentMethodDTO.getPaymentMethodId());

		paymentMethod.setPaymentMethodId(ControllerUtils.setValue(paymentMethod.getPaymentMethodId(), paymentMethodDTO.getPaymentMethodId()));

		paymentMethod.setMethodName(ControllerUtils.setValue(paymentMethod.getMethodName(), paymentMethodDTO.getMethodName()));



        paymentMethod = paymentMethodDao.save(paymentMethod);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PaymentMethodDTO getPaymentMethodDTOById(Integer paymentMethodId) {
	
		PaymentMethod paymentMethod = paymentMethodDao.getById(paymentMethodId);
			
		
		PaymentMethodConvertCriteriaDTO convertCriteria = new PaymentMethodConvertCriteriaDTO();
		return(this.convertPaymentMethodToPaymentMethodDTO(paymentMethod,convertCriteria));
	}







}
