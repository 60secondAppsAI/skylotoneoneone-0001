package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.Subscription;





public interface SubscriptionDAO extends GenericDAO<Subscription, Integer> {
  
	List<Subscription> findAll();
	






}


