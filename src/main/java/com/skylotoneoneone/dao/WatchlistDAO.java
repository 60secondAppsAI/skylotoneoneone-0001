package com.skylotoneoneone.dao;

import java.util.List;

import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.domain.Watchlist;





public interface WatchlistDAO extends GenericDAO<Watchlist, Integer> {
  
	List<Watchlist> findAll();
	






}


