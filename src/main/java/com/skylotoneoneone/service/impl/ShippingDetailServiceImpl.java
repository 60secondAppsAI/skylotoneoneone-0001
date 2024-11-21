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
import com.skylotoneoneone.dao.ShippingDetailDAO;
import com.skylotoneoneone.domain.ShippingDetail;
import com.skylotoneoneone.dto.ShippingDetailDTO;
import com.skylotoneoneone.dto.ShippingDetailSearchDTO;
import com.skylotoneoneone.dto.ShippingDetailPageDTO;
import com.skylotoneoneone.dto.ShippingDetailConvertCriteriaDTO;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import com.skylotoneoneone.service.ShippingDetailService;
import com.skylotoneoneone.util.ControllerUtils;





@Service
public class ShippingDetailServiceImpl extends GenericServiceImpl<ShippingDetail, Integer> implements ShippingDetailService {

    private final static Logger logger = LoggerFactory.getLogger(ShippingDetailServiceImpl.class);

	@Autowired
	ShippingDetailDAO shippingDetailDao;

	


	@Override
	public GenericDAO<ShippingDetail, Integer> getDAO() {
		return (GenericDAO<ShippingDetail, Integer>) shippingDetailDao;
	}
	
	public List<ShippingDetail> findAll () {
		List<ShippingDetail> shippingDetails = shippingDetailDao.findAll();
		
		return shippingDetails;	
		
	}

	public ResultDTO addShippingDetail(ShippingDetailDTO shippingDetailDTO, RequestDTO requestDTO) {

		ShippingDetail shippingDetail = new ShippingDetail();

		shippingDetail.setShippingDetailId(shippingDetailDTO.getShippingDetailId());


		shippingDetail.setAddress(shippingDetailDTO.getAddress());


		shippingDetail.setCity(shippingDetailDTO.getCity());


		shippingDetail.setPostalCode(shippingDetailDTO.getPostalCode());


		shippingDetail.setCountry(shippingDetailDTO.getCountry());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		shippingDetail = shippingDetailDao.save(shippingDetail);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ShippingDetail> getAllShippingDetails(Pageable pageable) {
		return shippingDetailDao.findAll(pageable);
	}

	public Page<ShippingDetail> getAllShippingDetails(Specification<ShippingDetail> spec, Pageable pageable) {
		return shippingDetailDao.findAll(spec, pageable);
	}

	public ResponseEntity<ShippingDetailPageDTO> getShippingDetails(ShippingDetailSearchDTO shippingDetailSearchDTO) {
	
			Integer shippingDetailId = shippingDetailSearchDTO.getShippingDetailId(); 
 			String address = shippingDetailSearchDTO.getAddress(); 
 			String city = shippingDetailSearchDTO.getCity(); 
 			String postalCode = shippingDetailSearchDTO.getPostalCode(); 
 			String country = shippingDetailSearchDTO.getCountry(); 
 			String sortBy = shippingDetailSearchDTO.getSortBy();
			String sortOrder = shippingDetailSearchDTO.getSortOrder();
			String searchQuery = shippingDetailSearchDTO.getSearchQuery();
			Integer page = shippingDetailSearchDTO.getPage();
			Integer size = shippingDetailSearchDTO.getSize();

	        Specification<ShippingDetail> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, shippingDetailId, "shippingDetailId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, address, "address"); 
			
			spec = ControllerUtils.andIfNecessary(spec, city, "city"); 
			
			spec = ControllerUtils.andIfNecessary(spec, postalCode, "postalCode"); 
			
			spec = ControllerUtils.andIfNecessary(spec, country, "country"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("address")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("city")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("postalCode")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("country")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<ShippingDetail> shippingDetails = this.getAllShippingDetails(spec, pageable);
		
		//System.out.println(String.valueOf(shippingDetails.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(shippingDetails.getTotalPages()));
		
		List<ShippingDetail> shippingDetailsList = shippingDetails.getContent();
		
		ShippingDetailConvertCriteriaDTO convertCriteria = new ShippingDetailConvertCriteriaDTO();
		List<ShippingDetailDTO> shippingDetailDTOs = this.convertShippingDetailsToShippingDetailDTOs(shippingDetailsList,convertCriteria);
		
		ShippingDetailPageDTO shippingDetailPageDTO = new ShippingDetailPageDTO();
		shippingDetailPageDTO.setShippingDetails(shippingDetailDTOs);
		shippingDetailPageDTO.setTotalElements(shippingDetails.getTotalElements());
		return ResponseEntity.ok(shippingDetailPageDTO);
	}

	public List<ShippingDetailDTO> convertShippingDetailsToShippingDetailDTOs(List<ShippingDetail> shippingDetails, ShippingDetailConvertCriteriaDTO convertCriteria) {
		
		List<ShippingDetailDTO> shippingDetailDTOs = new ArrayList<ShippingDetailDTO>();
		
		for (ShippingDetail shippingDetail : shippingDetails) {
			shippingDetailDTOs.add(convertShippingDetailToShippingDetailDTO(shippingDetail,convertCriteria));
		}
		
		return shippingDetailDTOs;

	}
	
	public ShippingDetailDTO convertShippingDetailToShippingDetailDTO(ShippingDetail shippingDetail, ShippingDetailConvertCriteriaDTO convertCriteria) {
		
		ShippingDetailDTO shippingDetailDTO = new ShippingDetailDTO();
		
		shippingDetailDTO.setShippingDetailId(shippingDetail.getShippingDetailId());

	
		shippingDetailDTO.setAddress(shippingDetail.getAddress());

	
		shippingDetailDTO.setCity(shippingDetail.getCity());

	
		shippingDetailDTO.setPostalCode(shippingDetail.getPostalCode());

	
		shippingDetailDTO.setCountry(shippingDetail.getCountry());

	

		
		return shippingDetailDTO;
	}

	public ResultDTO updateShippingDetail(ShippingDetailDTO shippingDetailDTO, RequestDTO requestDTO) {
		
		ShippingDetail shippingDetail = shippingDetailDao.getById(shippingDetailDTO.getShippingDetailId());

		shippingDetail.setShippingDetailId(ControllerUtils.setValue(shippingDetail.getShippingDetailId(), shippingDetailDTO.getShippingDetailId()));

		shippingDetail.setAddress(ControllerUtils.setValue(shippingDetail.getAddress(), shippingDetailDTO.getAddress()));

		shippingDetail.setCity(ControllerUtils.setValue(shippingDetail.getCity(), shippingDetailDTO.getCity()));

		shippingDetail.setPostalCode(ControllerUtils.setValue(shippingDetail.getPostalCode(), shippingDetailDTO.getPostalCode()));

		shippingDetail.setCountry(ControllerUtils.setValue(shippingDetail.getCountry(), shippingDetailDTO.getCountry()));



        shippingDetail = shippingDetailDao.save(shippingDetail);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ShippingDetailDTO getShippingDetailDTOById(Integer shippingDetailId) {
	
		ShippingDetail shippingDetail = shippingDetailDao.getById(shippingDetailId);
			
		
		ShippingDetailConvertCriteriaDTO convertCriteria = new ShippingDetailConvertCriteriaDTO();
		return(this.convertShippingDetailToShippingDetailDTO(shippingDetail,convertCriteria));
	}







}
