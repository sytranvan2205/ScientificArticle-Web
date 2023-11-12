package com.iuh.sientifirticle.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notify")
@Getter
@Setter
public class Notify extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="notify_id")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name = "type")
	private Integer type;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
