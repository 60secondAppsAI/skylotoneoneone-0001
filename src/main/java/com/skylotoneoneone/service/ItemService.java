package com.skylotoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skylotoneoneone.domain.Item;
import com.skylotoneoneone.dto.ItemDTO;
import com.skylotoneoneone.dto.ItemSearchDTO;
import com.skylotoneoneone.dto.ItemPageDTO;
import com.skylotoneoneone.dto.ItemConvertCriteriaDTO;
import com.skylotoneoneone.service.GenericService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ItemService extends GenericService<Item, Integer> {

	List<Item> findAll();

	ResultDTO addItem(ItemDTO itemDTO, RequestDTO requestDTO);

	ResultDTO updateItem(ItemDTO itemDTO, RequestDTO requestDTO);

    Page<Item> getAllItems(Pageable pageable);

    Page<Item> getAllItems(Specification<Item> spec, Pageable pageable);

	ResponseEntity<ItemPageDTO> getItems(ItemSearchDTO itemSearchDTO);
	
	List<ItemDTO> convertItemsToItemDTOs(List<Item> items, ItemConvertCriteriaDTO convertCriteria);

	ItemDTO getItemDTOById(Integer itemId);







}





