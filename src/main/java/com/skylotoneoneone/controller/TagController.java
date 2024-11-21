package com.skylotoneoneone.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skylotoneoneone.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skylotoneoneone.domain.Tag;
import com.skylotoneoneone.dto.TagDTO;
import com.skylotoneoneone.dto.TagSearchDTO;
import com.skylotoneoneone.dto.TagPageDTO;
import com.skylotoneoneone.service.TagService;
import com.skylotoneoneone.dto.common.RequestDTO;
import com.skylotoneoneone.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/tag")
@RestController
public class TagController {

	private final static Logger logger = LoggerFactory.getLogger(TagController.class);

	@Autowired
	TagService tagService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Tag> getAll() {

		List<Tag> tags = tagService.findAll();
		
		return tags;	
	}

	@GetMapping(value = "/{tagId}")
	@ResponseBody
	public TagDTO getTag(@PathVariable Integer tagId) {
		
		return (tagService.getTagDTOById(tagId));
	}

 	@RequestMapping(value = "/addTag", method = RequestMethod.POST)
	public ResponseEntity<?> addTag(@RequestBody TagDTO tagDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = tagService.addTag(tagDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/tags")
	public ResponseEntity<TagPageDTO> getTags(TagSearchDTO tagSearchDTO) {
 
		return tagService.getTags(tagSearchDTO);
	}	

	@RequestMapping(value = "/updateTag", method = RequestMethod.POST)
	public ResponseEntity<?> updateTag(@RequestBody TagDTO tagDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = tagService.updateTag(tagDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
