package com.iuh.sientifirticle.service;

import org.springframework.security.core.Authentication;

import com.iuh.sientifirticle.dto.DataResponse;
import com.iuh.sientifirticle.dto.ScientificArticleDTO;

public interface ScientificArticleService {
	DataResponse findAll();
	DataResponse createScientificArticle(ScientificArticleDTO scientificArticleDTO,Authentication authentication);
}
