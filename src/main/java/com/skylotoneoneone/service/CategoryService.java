package com.skylotoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skylotoneoneone.domain.Category;
import com.skylotoneoneone.dto.CategoryDTO;
import com.skylotoneoneone.dto.CategorySearchDTO;
import com.skylotoneoneone.dto.CategoryPageDTO;
import com.skylotoneoneone.dto.CategoryConvertCriteriaDTO;
import com.skylotoneoneone.service.GenericService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CategoryService extends GenericService<Category, Integer> {

	List<Category> findAll();

	ResultDTO addCategory(CategoryDTO categoryDTO, RequestDTO requestDTO);

	ResultDTO updateCategory(CategoryDTO categoryDTO, RequestDTO requestDTO);

    Page<Category> getAllCategorys(Pageable pageable);

    Page<Category> getAllCategorys(Specification<Category> spec, Pageable pageable);

	ResponseEntity<CategoryPageDTO> getCategorys(CategorySearchDTO categorySearchDTO);
	
	List<CategoryDTO> convertCategorysToCategoryDTOs(List<Category> categorys, CategoryConvertCriteriaDTO convertCriteria);

	CategoryDTO getCategoryDTOById(Integer categoryId);







}





