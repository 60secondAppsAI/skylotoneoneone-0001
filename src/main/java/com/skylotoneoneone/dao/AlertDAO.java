package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.Alert;





public interface AlertDAO extends GenericDAO<Alert, Integer> {
  
	List<Alert> findAll();
	






}


