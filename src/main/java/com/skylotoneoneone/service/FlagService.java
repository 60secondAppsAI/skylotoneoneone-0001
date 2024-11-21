package com.skylotoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skylotoneoneone.domain.Flag;
import com.skylotoneoneone.dto.FlagDTO;
import com.skylotoneoneone.dto.FlagSearchDTO;
import com.skylotoneoneone.dto.FlagPageDTO;
import com.skylotoneoneone.dto.FlagConvertCriteriaDTO;
import com.skylotoneoneone.service.GenericService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FlagService extends GenericService<Flag, Integer> {

	List<Flag> findAll();

	ResultDTO addFlag(FlagDTO flagDTO, RequestDTO requestDTO);

	ResultDTO updateFlag(FlagDTO flagDTO, RequestDTO requestDTO);

    Page<Flag> getAllFlags(Pageable pageable);

    Page<Flag> getAllFlags(Specification<Flag> spec, Pageable pageable);

	ResponseEntity<FlagPageDTO> getFlags(FlagSearchDTO flagSearchDTO);
	
	List<FlagDTO> convertFlagsToFlagDTOs(List<Flag> flags, FlagConvertCriteriaDTO convertCriteria);

	FlagDTO getFlagDTOById(Integer flagId);







}





