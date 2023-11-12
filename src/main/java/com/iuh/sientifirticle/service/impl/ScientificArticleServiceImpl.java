package com.iuh.sientifirticle.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.iuh.sientifirticle.constant.Constant;
import com.iuh.sientifirticle.dto.DataResponse;
import com.iuh.sientifirticle.dto.ScientificArticleDTO;
import com.iuh.sientifirticle.entity.ScientificArticle;
import com.iuh.sientifirticle.entity.User;
import com.iuh.sientifirticle.repository.ScientificArticleRepository;
import com.iuh.sientifirticle.repository.UserRepository;
import com.iuh.sientifirticle.service.ScientificArticleService;

public class ScientificArticleServiceImpl implements ScientificArticleService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScientificArticleRepository articleRepository;
	
	@Override
	public DataResponse findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResponse createScientificArticle(ScientificArticleDTO scientificArticleDTO,Authentication authentication) {
		DataResponse dataResponse = new DataResponse();
		try {
			if(scientificArticleDTO.getSummary() == null) {
				dataResponse.setResponseMsg("Summary is not null");
				dataResponse.setRespType(Constant.SUMMARY_NOT_NULL);
				return dataResponse;
			}
			if(scientificArticleDTO.getTitle()== null) {
				dataResponse.setResponseMsg("Subject is not null");
				dataResponse.setRespType(Constant.SUBJEB_NOT_NULL);
				return dataResponse;
			}
			ScientificArticle article = new ScientificArticle();
			User user = userRepository.findUserActiveByEmail(scientificArticleDTO.getUserEmail());
			if(user == null) {
				dataResponse.setResponseMsg("User is not exist");
				dataResponse.setRespType(Constant.USER_NOT_EXIST);
				return dataResponse;
			}
			article.setSummary(scientificArticleDTO.getSummary());
			article.setTitle(scientificArticleDTO.getTitle());
			article.setUser(user);
			article.setCreateDate(LocalDate.now());
			article.setCreateUser(user.getName());
			article.setStatus(1);
			articleRepository.save(article);
			dataResponse.setRespType(Constant.HTTP_SUCCESS);
			dataResponse.setResponseMsg("Create successful !!!");
			Map<String, Object> articleMap = new HashMap<>();
			articleMap.put("article", article);
			dataResponse.setValueResponse(articleMap);
			return dataResponse;
		} catch (Exception e) {
			dataResponse.setRespType(Constant.SYSTEM_ERROR_CODE);
			dataResponse.setResponseMsg("System error");
			return dataResponse;
		}
		
	}
	
}
