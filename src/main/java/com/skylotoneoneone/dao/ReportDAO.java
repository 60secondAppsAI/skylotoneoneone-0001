package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.Report;





public interface ReportDAO extends GenericDAO<Report, Integer> {
  
	List<Report> findAll();
	






}


