package com.iuh.sientifirticle.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "user_name", length = 100)
	private String name;
	
	@Column(name = "user_email", length = 50, unique = true, nullable = false)
	private String email;
	
	@Column(name = "user_image")
	private String image;
	
	@Column(name ="is_verified")
	private Integer isVerified;
	
	@Column(name="user_password", nullable = false)
	private String password;
	
	@Column(name = "user_address")
	private String address;
	
	@Column(name ="phone_number")
	private String phoneNumber;
	
	@Column(name="role")
	private String role;
	
	@OneToMany(mappedBy = "user")
	private Set<MailToken> tokens;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<UserFollower> lstFollower = new ArrayList<UserFollower>();
	
	@OneToMany(mappedBy="followed", cascade = CascadeType.ALL)
	private List<UserFollower> lstFollowered = new ArrayList<UserFollower>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ScientificArticle> articles = new ArrayList<ScientificArticle>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Notify> notifies = new ArrayList<Notify>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();
	
	@Column(name="is_expert")
	private Integer isExpert;
	

}
