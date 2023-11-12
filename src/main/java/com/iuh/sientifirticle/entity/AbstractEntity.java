package com.iuh.sientifirticle.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "status")
	private Integer status;

	@Column(name = "create_date")
	private LocalDate createDate;

	@Column(name = "update_date")
	private LocalDate updateDate;

	@Column(name = "create_user")
	private String createUser;

	@Column(name = "create_update")
	private String createUpdate;

	
	
}
