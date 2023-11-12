package com.iuh.sientifirticle.dto;

import lombok.Data;

@Data
public class RandomStuff {
	private String testJWT;

	public RandomStuff(String testJWT) {
		super();
		this.testJWT = testJWT;
	}
	
}
