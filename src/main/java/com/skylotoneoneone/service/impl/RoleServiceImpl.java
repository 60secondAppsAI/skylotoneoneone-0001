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
import com.skylotoneoneone.dao.RoleDAO;
import com.skylotoneoneone.domain.Role;
import com.skylotoneoneone.dto.RoleDTO;
import com.skylotoneoneone.dto.RoleSearchDTO;
import com.skylotoneoneone.dto.RolePageDTO;
import com.skylotoneoneone.dto.RoleConvertCriteriaDTO;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import com.skylotoneoneone.service.RoleService;
import com.skylotoneoneone.util.ControllerUtils;





@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Integer> implements RoleService {

    private final static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	RoleDAO roleDao;

	


	@Override
	public GenericDAO<Role, Integer> getDAO() {
		return (GenericDAO<Role, Integer>) roleDao;
	}
	
	public List<Role> findAll () {
		List<Role> roles = roleDao.findAll();
		
		return roles;	
		
	}

	public ResultDTO addRole(RoleDTO roleDTO, RequestDTO requestDTO) {

		Role role = new Role();

		role.setRoleId(roleDTO.getRoleId());


		role.setName(roleDTO.getName());


		role.setDescription(roleDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		role = roleDao.save(role);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Role> getAllRoles(Pageable pageable) {
		return roleDao.findAll(pageable);
	}

	public Page<Role> getAllRoles(Specification<Role> spec, Pageable pageable) {
		return roleDao.findAll(spec, pageable);
	}

	public ResponseEntity<RolePageDTO> getRoles(RoleSearchDTO roleSearchDTO) {
	
			Integer roleId = roleSearchDTO.getRoleId(); 
 			String name = roleSearchDTO.getName(); 
 			String description = roleSearchDTO.getDescription(); 
 			String sortBy = roleSearchDTO.getSortBy();
			String sortOrder = roleSearchDTO.getSortOrder();
			String searchQuery = roleSearchDTO.getSearchQuery();
			Integer page = roleSearchDTO.getPage();
			Integer size = roleSearchDTO.getSize();

	        Specification<Role> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, roleId, "roleId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Role> roles = this.getAllRoles(spec, pageable);
		
		//System.out.println(String.valueOf(roles.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(roles.getTotalPages()));
		
		List<Role> rolesList = roles.getContent();
		
		RoleConvertCriteriaDTO convertCriteria = new RoleConvertCriteriaDTO();
		List<RoleDTO> roleDTOs = this.convertRolesToRoleDTOs(rolesList,convertCriteria);
		
		RolePageDTO rolePageDTO = new RolePageDTO();
		rolePageDTO.setRoles(roleDTOs);
		rolePageDTO.setTotalElements(roles.getTotalElements());
		return ResponseEntity.ok(rolePageDTO);
	}

	public List<RoleDTO> convertRolesToRoleDTOs(List<Role> roles, RoleConvertCriteriaDTO convertCriteria) {
		
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		
		for (Role role : roles) {
			roleDTOs.add(convertRoleToRoleDTO(role,convertCriteria));
		}
		
		return roleDTOs;

	}
	
	public RoleDTO convertRoleToRoleDTO(Role role, RoleConvertCriteriaDTO convertCriteria) {
		
		RoleDTO roleDTO = new RoleDTO();
		
		roleDTO.setRoleId(role.getRoleId());

	
		roleDTO.setName(role.getName());

	
		roleDTO.setDescription(role.getDescription());

	

		
		return roleDTO;
	}

	public ResultDTO updateRole(RoleDTO roleDTO, RequestDTO requestDTO) {
		
		Role role = roleDao.getById(roleDTO.getRoleId());

		role.setRoleId(ControllerUtils.setValue(role.getRoleId(), roleDTO.getRoleId()));

		role.setName(ControllerUtils.setValue(role.getName(), roleDTO.getName()));

		role.setDescription(ControllerUtils.setValue(role.getDescription(), roleDTO.getDescription()));



        role = roleDao.save(role);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RoleDTO getRoleDTOById(Integer roleId) {
	
		Role role = roleDao.getById(roleId);
			
		
		RoleConvertCriteriaDTO convertCriteria = new RoleConvertCriteriaDTO();
		return(this.convertRoleToRoleDTO(role,convertCriteria));
	}







}
