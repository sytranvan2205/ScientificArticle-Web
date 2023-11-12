package com.iuh.sientifirticle.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class DataResponse {
	private String responseMsg;
	private Integer respType;
	private Map<String, Object> valueResponse = new HashMap<>();
	
}
