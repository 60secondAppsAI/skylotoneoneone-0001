package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


