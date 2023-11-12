package com.iuh.sientifirticle.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.iuh.sientifirticle.entity.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScientificArticleDTO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String summary;
	
	private String title;
	

	private Integer view;

	private String userEmail;
	
	private List<Comment> comments = new ArrayList<Comment>();
	
 

}
