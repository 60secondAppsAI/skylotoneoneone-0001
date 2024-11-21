package com.skylotoneoneone.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class UserDTO {

	private Integer userId;

	private String username;

	private String passwordHash;

	private String email;

	private Date creationDate;






}
