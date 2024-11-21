package com.skylotoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skylotoneoneone.domain.Watchlist;
import com.skylotoneoneone.dto.WatchlistDTO;
import com.skylotoneoneone.dto.WatchlistSearchDTO;
import com.skylotoneoneone.dto.WatchlistPageDTO;
import com.skylotoneoneone.dto.WatchlistConvertCriteriaDTO;
import com.skylotoneoneone.service.GenericService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WatchlistService extends GenericService<Watchlist, Integer> {

	List<Watchlist> findAll();

	ResultDTO addWatchlist(WatchlistDTO watchlistDTO, RequestDTO requestDTO);

	ResultDTO updateWatchlist(WatchlistDTO watchlistDTO, RequestDTO requestDTO);

    Page<Watchlist> getAllWatchlists(Pageable pageable);

    Page<Watchlist> getAllWatchlists(Specification<Watchlist> spec, Pageable pageable);

	ResponseEntity<WatchlistPageDTO> getWatchlists(WatchlistSearchDTO watchlistSearchDTO);
	
	List<WatchlistDTO> convertWatchlistsToWatchlistDTOs(List<Watchlist> watchlists, WatchlistConvertCriteriaDTO convertCriteria);

	WatchlistDTO getWatchlistDTOById(Integer watchlistId);







}





