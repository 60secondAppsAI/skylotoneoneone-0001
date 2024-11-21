package com.skylotoneoneone.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skylotoneoneone.dao.GenericDAO;
import com.skylotoneoneone.service.GenericService;
import com.skylotoneoneone.service.impl.GenericServiceImpl;
import com.skylotoneoneone.dao.UserDAO;
import com.skylotoneoneone.domain.User;
import com.skylotoneoneone.dto.UserDTO;
import com.skylotoneoneone.dto.UserSearchDTO;
import com.skylotoneoneone.dto.UserPageDTO;
import com.skylotoneoneone.dto.UserConvertCriteriaDTO;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import com.skylotoneoneone.service.UserService;
import com.skylotoneoneone.util.ControllerUtils;





@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDAO userDao;

	


	@Override
	public GenericDAO<User, Integer> getDAO() {
		return (GenericDAO<User, Integer>) userDao;
	}
	
	public List<User> findAll () {
		List<User> users = userDao.findAll();
		
		return users;	
		
	}

	public ResultDTO addUser(UserDTO userDTO, RequestDTO requestDTO) {

		User user = new User();

		user.setUserId(userDTO.getUserId());


		user.setUsername(userDTO.getUsername());


		user.setPasswordHash(userDTO.getPasswordHash());


		user.setEmail(userDTO.getEmail());


		user.setCreationDate(userDTO.getCreationDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		user = userDao.save(user);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<User> getAllUsers(Pageable pageable) {
		return userDao.findAll(pageable);
	}

	public Page<User> getAllUsers(Specification<User> spec, Pageable pageable) {
		return userDao.findAll(spec, pageable);
	}

	public ResponseEntity<UserPageDTO> getUsers(UserSearchDTO userSearchDTO) {
	
			Integer userId = userSearchDTO.getUserId(); 
 			String username = userSearchDTO.getUsername(); 
 			String passwordHash = userSearchDTO.getPasswordHash(); 
 			String email = userSearchDTO.getEmail(); 
   			String sortBy = userSearchDTO.getSortBy();
			String sortOrder = userSearchDTO.getSortOrder();
			String searchQuery = userSearchDTO.getSearchQuery();
			Integer page = userSearchDTO.getPage();
			Integer size = userSearchDTO.getSize();

	        Specification<User> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, userId, "userId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, username, "username"); 
			
			spec = ControllerUtils.andIfNecessary(spec, passwordHash, "passwordHash"); 
			
			spec = ControllerUtils.andIfNecessary(spec, email, "email"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("username")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("passwordHash")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("email")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<User> users = this.getAllUsers(spec, pageable);
		
		//System.out.println(String.valueOf(users.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(users.getTotalPages()));
		
		List<User> usersList = users.getContent();
		
		UserConvertCriteriaDTO convertCriteria = new UserConvertCriteriaDTO();
		List<UserDTO> userDTOs = this.convertUsersToUserDTOs(usersList,convertCriteria);
		
		UserPageDTO userPageDTO = new UserPageDTO();
		userPageDTO.setUsers(userDTOs);
		userPageDTO.setTotalElements(users.getTotalElements());
		return ResponseEntity.ok(userPageDTO);
	}

	public List<UserDTO> convertUsersToUserDTOs(List<User> users, UserConvertCriteriaDTO convertCriteria) {
		
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		
		for (User user : users) {
			userDTOs.add(convertUserToUserDTO(user,convertCriteria));
		}
		
		return userDTOs;

	}
	
	public UserDTO convertUserToUserDTO(User user, UserConvertCriteriaDTO convertCriteria) {
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setUserId(user.getUserId());

	
		userDTO.setUsername(user.getUsername());

	
		userDTO.setPasswordHash(user.getPasswordHash());

	
		userDTO.setEmail(user.getEmail());

	
		userDTO.setCreationDate(user.getCreationDate());

	

		
		return userDTO;
	}

	public ResultDTO updateUser(UserDTO userDTO, RequestDTO requestDTO) {
		
		User user = userDao.getById(userDTO.getUserId());

		user.setUserId(ControllerUtils.setValue(user.getUserId(), userDTO.getUserId()));

		user.setUsername(ControllerUtils.setValue(user.getUsername(), userDTO.getUsername()));

		user.setPasswordHash(ControllerUtils.setValue(user.getPasswordHash(), userDTO.getPasswordHash()));

		user.setEmail(ControllerUtils.setValue(user.getEmail(), userDTO.getEmail()));

		user.setCreationDate(ControllerUtils.setValue(user.getCreationDate(), userDTO.getCreationDate()));



        user = userDao.save(user);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public UserDTO getUserDTOById(Integer userId) {
	
		User user = userDao.getById(userId);
			
		
		UserConvertCriteriaDTO convertCriteria = new UserConvertCriteriaDTO();
		return(this.convertUserToUserDTO(user,convertCriteria));
	}







}
