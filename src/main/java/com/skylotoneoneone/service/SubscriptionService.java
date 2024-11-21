package com.skylotoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skylotoneoneone.domain.Subscription;
import com.skylotoneoneone.dto.SubscriptionDTO;
import com.skylotoneoneone.dto.SubscriptionSearchDTO;
import com.skylotoneoneone.dto.SubscriptionPageDTO;
import com.skylotoneoneone.dto.SubscriptionConvertCriteriaDTO;
import com.skylotoneoneone.service.GenericService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SubscriptionService extends GenericService<Subscription, Integer> {

	List<Subscription> findAll();

	ResultDTO addSubscription(SubscriptionDTO subscriptionDTO, RequestDTO requestDTO);

	ResultDTO updateSubscription(SubscriptionDTO subscriptionDTO, RequestDTO requestDTO);

    Page<Subscription> getAllSubscriptions(Pageable pageable);

    Page<Subscription> getAllSubscriptions(Specification<Subscription> spec, Pageable pageable);

	ResponseEntity<SubscriptionPageDTO> getSubscriptions(SubscriptionSearchDTO subscriptionSearchDTO);
	
	List<SubscriptionDTO> convertSubscriptionsToSubscriptionDTOs(List<Subscription> subscriptions, SubscriptionConvertCriteriaDTO convertCriteria);

	SubscriptionDTO getSubscriptionDTOById(Integer subscriptionId);







}





