package com.skylotoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skylotoneoneone.domain.ShippingDetail;
import com.skylotoneoneone.dto.ShippingDetailDTO;
import com.skylotoneoneone.dto.ShippingDetailSearchDTO;
import com.skylotoneoneone.dto.ShippingDetailPageDTO;
import com.skylotoneoneone.dto.ShippingDetailConvertCriteriaDTO;
import com.skylotoneoneone.service.GenericService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ShippingDetailService extends GenericService<ShippingDetail, Integer> {

	List<ShippingDetail> findAll();

	ResultDTO addShippingDetail(ShippingDetailDTO shippingDetailDTO, RequestDTO requestDTO);

	ResultDTO updateShippingDetail(ShippingDetailDTO shippingDetailDTO, RequestDTO requestDTO);

    Page<ShippingDetail> getAllShippingDetails(Pageable pageable);

    Page<ShippingDetail> getAllShippingDetails(Specification<ShippingDetail> spec, Pageable pageable);

	ResponseEntity<ShippingDetailPageDTO> getShippingDetails(ShippingDetailSearchDTO shippingDetailSearchDTO);
	
	List<ShippingDetailDTO> convertShippingDetailsToShippingDetailDTOs(List<ShippingDetail> shippingDetails, ShippingDetailConvertCriteriaDTO convertCriteria);

	ShippingDetailDTO getShippingDetailDTOById(Integer shippingDetailId);







}





