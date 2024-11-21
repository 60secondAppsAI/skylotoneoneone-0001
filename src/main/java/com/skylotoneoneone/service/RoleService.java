package com.skylotoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skylotoneoneone.domain.Role;
import com.skylotoneoneone.dto.RoleDTO;
import com.skylotoneoneone.dto.RoleSearchDTO;
import com.skylotoneoneone.dto.RolePageDTO;
import com.skylotoneoneone.dto.RoleConvertCriteriaDTO;
import com.skylotoneoneone.service.GenericService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RoleService extends GenericService<Role, Integer> {

	List<Role> findAll();

	ResultDTO addRole(RoleDTO roleDTO, RequestDTO requestDTO);

	ResultDTO updateRole(RoleDTO roleDTO, RequestDTO requestDTO);

    Page<Role> getAllRoles(Pageable pageable);

    Page<Role> getAllRoles(Specification<Role> spec, Pageable pageable);

	ResponseEntity<RolePageDTO> getRoles(RoleSearchDTO roleSearchDTO);
	
	List<RoleDTO> convertRolesToRoleDTOs(List<Role> roles, RoleConvertCriteriaDTO convertCriteria);

	RoleDTO getRoleDTOById(Integer roleId);







}





