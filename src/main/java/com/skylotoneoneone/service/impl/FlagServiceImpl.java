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
import com.skylotoneoneone.dao.FlagDAO;
import com.skylotoneoneone.domain.Flag;
import com.skylotoneoneone.dto.FlagDTO;
import com.skylotoneoneone.dto.FlagSearchDTO;
import com.skylotoneoneone.dto.FlagPageDTO;
import com.skylotoneoneone.dto.FlagConvertCriteriaDTO;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import com.skylotoneoneone.service.FlagService;
import com.skylotoneoneone.util.ControllerUtils;





@Service
public class FlagServiceImpl extends GenericServiceImpl<Flag, Integer> implements FlagService {

    private final static Logger logger = LoggerFactory.getLogger(FlagServiceImpl.class);

	@Autowired
	FlagDAO flagDao;

	


	@Override
	public GenericDAO<Flag, Integer> getDAO() {
		return (GenericDAO<Flag, Integer>) flagDao;
	}
	
	public List<Flag> findAll () {
		List<Flag> flags = flagDao.findAll();
		
		return flags;	
		
	}

	public ResultDTO addFlag(FlagDTO flagDTO, RequestDTO requestDTO) {

		Flag flag = new Flag();

		flag.setFlagId(flagDTO.getFlagId());


		flag.setComment(flagDTO.getComment());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		flag = flagDao.save(flag);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Flag> getAllFlags(Pageable pageable) {
		return flagDao.findAll(pageable);
	}

	public Page<Flag> getAllFlags(Specification<Flag> spec, Pageable pageable) {
		return flagDao.findAll(spec, pageable);
	}

	public ResponseEntity<FlagPageDTO> getFlags(FlagSearchDTO flagSearchDTO) {
	
			Integer flagId = flagSearchDTO.getFlagId(); 
 			String comment = flagSearchDTO.getComment(); 
 			String sortBy = flagSearchDTO.getSortBy();
			String sortOrder = flagSearchDTO.getSortOrder();
			String searchQuery = flagSearchDTO.getSearchQuery();
			Integer page = flagSearchDTO.getPage();
			Integer size = flagSearchDTO.getSize();

	        Specification<Flag> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, flagId, "flagId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, comment, "comment"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("comment")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Flag> flags = this.getAllFlags(spec, pageable);
		
		//System.out.println(String.valueOf(flags.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(flags.getTotalPages()));
		
		List<Flag> flagsList = flags.getContent();
		
		FlagConvertCriteriaDTO convertCriteria = new FlagConvertCriteriaDTO();
		List<FlagDTO> flagDTOs = this.convertFlagsToFlagDTOs(flagsList,convertCriteria);
		
		FlagPageDTO flagPageDTO = new FlagPageDTO();
		flagPageDTO.setFlags(flagDTOs);
		flagPageDTO.setTotalElements(flags.getTotalElements());
		return ResponseEntity.ok(flagPageDTO);
	}

	public List<FlagDTO> convertFlagsToFlagDTOs(List<Flag> flags, FlagConvertCriteriaDTO convertCriteria) {
		
		List<FlagDTO> flagDTOs = new ArrayList<FlagDTO>();
		
		for (Flag flag : flags) {
			flagDTOs.add(convertFlagToFlagDTO(flag,convertCriteria));
		}
		
		return flagDTOs;

	}
	
	public FlagDTO convertFlagToFlagDTO(Flag flag, FlagConvertCriteriaDTO convertCriteria) {
		
		FlagDTO flagDTO = new FlagDTO();
		
		flagDTO.setFlagId(flag.getFlagId());

	
		flagDTO.setComment(flag.getComment());

	

		
		return flagDTO;
	}

	public ResultDTO updateFlag(FlagDTO flagDTO, RequestDTO requestDTO) {
		
		Flag flag = flagDao.getById(flagDTO.getFlagId());

		flag.setFlagId(ControllerUtils.setValue(flag.getFlagId(), flagDTO.getFlagId()));

		flag.setComment(ControllerUtils.setValue(flag.getComment(), flagDTO.getComment()));



        flag = flagDao.save(flag);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public FlagDTO getFlagDTOById(Integer flagId) {
	
		Flag flag = flagDao.getById(flagId);
			
		
		FlagConvertCriteriaDTO convertCriteria = new FlagConvertCriteriaDTO();
		return(this.convertFlagToFlagDTO(flag,convertCriteria));
	}







}
