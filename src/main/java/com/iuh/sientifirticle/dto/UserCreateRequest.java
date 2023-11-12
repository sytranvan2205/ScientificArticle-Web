package com.iuh.sientifirticle.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreateRequest {

	private String name;

	private String email;

	private String password;
	
}
