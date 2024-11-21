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
import com.skylotoneoneone.dao.TagDAO;
import com.skylotoneoneone.domain.Tag;
import com.skylotoneoneone.dto.TagDTO;
import com.skylotoneoneone.dto.TagSearchDTO;
import com.skylotoneoneone.dto.TagPageDTO;
import com.skylotoneoneone.dto.TagConvertCriteriaDTO;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import com.skylotoneoneone.service.TagService;
import com.skylotoneoneone.util.ControllerUtils;





@Service
public class TagServiceImpl extends GenericServiceImpl<Tag, Integer> implements TagService {

    private final static Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

	@Autowired
	TagDAO tagDao;

	


	@Override
	public GenericDAO<Tag, Integer> getDAO() {
		return (GenericDAO<Tag, Integer>) tagDao;
	}
	
	public List<Tag> findAll () {
		List<Tag> tags = tagDao.findAll();
		
		return tags;	
		
	}

	public ResultDTO addTag(TagDTO tagDTO, RequestDTO requestDTO) {

		Tag tag = new Tag();

		tag.setTagId(tagDTO.getTagId());


		tag.setName(tagDTO.getName());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		tag = tagDao.save(tag);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Tag> getAllTags(Pageable pageable) {
		return tagDao.findAll(pageable);
	}

	public Page<Tag> getAllTags(Specification<Tag> spec, Pageable pageable) {
		return tagDao.findAll(spec, pageable);
	}

	public ResponseEntity<TagPageDTO> getTags(TagSearchDTO tagSearchDTO) {
	
			Integer tagId = tagSearchDTO.getTagId(); 
 			String name = tagSearchDTO.getName(); 
 			String sortBy = tagSearchDTO.getSortBy();
			String sortOrder = tagSearchDTO.getSortOrder();
			String searchQuery = tagSearchDTO.getSearchQuery();
			Integer page = tagSearchDTO.getPage();
			Integer size = tagSearchDTO.getSize();

	        Specification<Tag> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, tagId, "tagId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Tag> tags = this.getAllTags(spec, pageable);
		
		//System.out.println(String.valueOf(tags.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(tags.getTotalPages()));
		
		List<Tag> tagsList = tags.getContent();
		
		TagConvertCriteriaDTO convertCriteria = new TagConvertCriteriaDTO();
		List<TagDTO> tagDTOs = this.convertTagsToTagDTOs(tagsList,convertCriteria);
		
		TagPageDTO tagPageDTO = new TagPageDTO();
		tagPageDTO.setTags(tagDTOs);
		tagPageDTO.setTotalElements(tags.getTotalElements());
		return ResponseEntity.ok(tagPageDTO);
	}

	public List<TagDTO> convertTagsToTagDTOs(List<Tag> tags, TagConvertCriteriaDTO convertCriteria) {
		
		List<TagDTO> tagDTOs = new ArrayList<TagDTO>();
		
		for (Tag tag : tags) {
			tagDTOs.add(convertTagToTagDTO(tag,convertCriteria));
		}
		
		return tagDTOs;

	}
	
	public TagDTO convertTagToTagDTO(Tag tag, TagConvertCriteriaDTO convertCriteria) {
		
		TagDTO tagDTO = new TagDTO();
		
		tagDTO.setTagId(tag.getTagId());

	
		tagDTO.setName(tag.getName());

	

		
		return tagDTO;
	}

	public ResultDTO updateTag(TagDTO tagDTO, RequestDTO requestDTO) {
		
		Tag tag = tagDao.getById(tagDTO.getTagId());

		tag.setTagId(ControllerUtils.setValue(tag.getTagId(), tagDTO.getTagId()));

		tag.setName(ControllerUtils.setValue(tag.getName(), tagDTO.getName()));



        tag = tagDao.save(tag);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TagDTO getTagDTOById(Integer tagId) {
	
		Tag tag = tagDao.getById(tagId);
			
		
		TagConvertCriteriaDTO convertCriteria = new TagConvertCriteriaDTO();
		return(this.convertTagToTagDTO(tag,convertCriteria));
	}







}
