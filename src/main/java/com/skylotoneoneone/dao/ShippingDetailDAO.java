package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.ShippingDetail;





public interface ShippingDetailDAO extends GenericDAO<ShippingDetail, Integer> {
  
	List<ShippingDetail> findAll();
	






}


