package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.Permission;





public interface PermissionDAO extends GenericDAO<Permission, Integer> {
  
	List<Permission> findAll();
	






}


