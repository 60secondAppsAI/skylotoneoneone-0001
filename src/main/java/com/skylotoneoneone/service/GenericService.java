package com.skylotoneoneone.service;

import com.skylotoneoneone.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}