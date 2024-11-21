package com.skylotoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skylotoneoneone.domain.Permission;
import com.skylotoneoneone.dto.PermissionDTO;
import com.skylotoneoneone.dto.PermissionSearchDTO;
import com.skylotoneoneone.dto.PermissionPageDTO;
import com.skylotoneoneone.dto.PermissionConvertCriteriaDTO;
import com.skylotoneoneone.service.GenericService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PermissionService extends GenericService<Permission, Integer> {

	List<Permission> findAll();

	ResultDTO addPermission(PermissionDTO permissionDTO, RequestDTO requestDTO);

	ResultDTO updatePermission(PermissionDTO permissionDTO, RequestDTO requestDTO);

    Page<Permission> getAllPermissions(Pageable pageable);

    Page<Permission> getAllPermissions(Specification<Permission> spec, Pageable pageable);

	ResponseEntity<PermissionPageDTO> getPermissions(PermissionSearchDTO permissionSearchDTO);
	
	List<PermissionDTO> convertPermissionsToPermissionDTOs(List<Permission> permissions, PermissionConvertCriteriaDTO convertCriteria);

	PermissionDTO getPermissionDTOById(Integer permissionId);







}





