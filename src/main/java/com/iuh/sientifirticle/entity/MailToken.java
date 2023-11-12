package com.iuh.sientifirticle.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table
@Data
public class MailToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String token;
	
	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp timeStamp;
	
	@Column(updatable = false)
	@Basic(optional = false)
	private LocalDateTime expireAt;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Transient
	private boolean isExpired;
}
