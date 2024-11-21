package com.skylotoneoneone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skylotoneoneone.domain.Tag;
import com.skylotoneoneone.dto.TagDTO;
import com.skylotoneoneone.dto.TagSearchDTO;
import com.skylotoneoneone.dto.TagPageDTO;
import com.skylotoneoneone.dto.TagConvertCriteriaDTO;
import com.skylotoneoneone.service.GenericService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TagService extends GenericService<Tag, Integer> {

	List<Tag> findAll();

	ResultDTO addTag(TagDTO tagDTO, RequestDTO requestDTO);

	ResultDTO updateTag(TagDTO tagDTO, RequestDTO requestDTO);

    Page<Tag> getAllTags(Pageable pageable);

    Page<Tag> getAllTags(Specification<Tag> spec, Pageable pageable);

	ResponseEntity<TagPageDTO> getTags(TagSearchDTO tagSearchDTO);
	
	List<TagDTO> convertTagsToTagDTOs(List<Tag> tags, TagConvertCriteriaDTO convertCriteria);

	TagDTO getTagDTOById(Integer tagId);







}





