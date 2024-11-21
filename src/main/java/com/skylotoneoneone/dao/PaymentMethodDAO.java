package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.PaymentMethod;





public interface PaymentMethodDAO extends GenericDAO<PaymentMethod, Integer> {
  
	List<PaymentMethod> findAll();
	






}


