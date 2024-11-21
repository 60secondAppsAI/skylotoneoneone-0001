package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.Role;





public interface RoleDAO extends GenericDAO<Role, Integer> {
  
	List<Role> findAll();
	






}


