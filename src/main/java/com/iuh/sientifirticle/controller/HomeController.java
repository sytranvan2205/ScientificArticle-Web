package com.iuh.sientifirticle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iuh.sientifirticle.dto.DataResponse;

@RestController
@RequestMapping(value = "/api/member")
public class HomeController {
	@GetMapping("/getAll")
	public DataResponse getScientificArticles() {
		DataResponse dataResponse = new DataResponse();
		return null;
	}
}
