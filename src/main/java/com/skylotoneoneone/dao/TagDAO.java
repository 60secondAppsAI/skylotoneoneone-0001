package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.Tag;





public interface TagDAO extends GenericDAO<Tag, Integer> {
  
	List<Tag> findAll();
	






}


