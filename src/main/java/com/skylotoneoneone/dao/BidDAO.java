package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.Bid;





public interface BidDAO extends GenericDAO<Bid, Integer> {
  
	List<Bid> findAll();
	






}


