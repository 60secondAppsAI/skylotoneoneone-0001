package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.Notification;





public interface NotificationDAO extends GenericDAO<Notification, Integer> {
  
	List<Notification> findAll();
	






}


