package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.Session;





public interface SessionDAO extends GenericDAO<Session, Integer> {
  
	List<Session> findAll();
	






}


