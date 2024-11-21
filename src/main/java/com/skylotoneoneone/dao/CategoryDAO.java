package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.Category;





public interface CategoryDAO extends GenericDAO<Category, Integer> {
  
	List<Category> findAll();
	






}


