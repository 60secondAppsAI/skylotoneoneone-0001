package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.Flag;





public interface FlagDAO extends GenericDAO<Flag, Integer> {
  
	List<Flag> findAll();
	






}


