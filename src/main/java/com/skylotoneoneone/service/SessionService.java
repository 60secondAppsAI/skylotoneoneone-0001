package com.skylotoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skylotoneoneone.domain.Session;
import com.skylotoneoneone.dto.SessionDTO;
import com.skylotoneoneone.dto.SessionSearchDTO;
import com.skylotoneoneone.dto.SessionPageDTO;
import com.skylotoneoneone.dto.SessionConvertCriteriaDTO;
import com.skylotoneoneone.service.GenericService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SessionService extends GenericService<Session, Integer> {

	List<Session> findAll();

	ResultDTO addSession(SessionDTO sessionDTO, RequestDTO requestDTO);

	ResultDTO updateSession(SessionDTO sessionDTO, RequestDTO requestDTO);

    Page<Session> getAllSessions(Pageable pageable);

    Page<Session> getAllSessions(Specification<Session> spec, Pageable pageable);

	ResponseEntity<SessionPageDTO> getSessions(SessionSearchDTO sessionSearchDTO);
	
	List<SessionDTO> convertSessionsToSessionDTOs(List<Session> sessions, SessionConvertCriteriaDTO convertCriteria);

	SessionDTO getSessionDTOById(Integer sessionId);







}





