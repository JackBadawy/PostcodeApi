package com.jackbadawy.postcodeApi.posts;

import java.util.Date;

import org.springframework.lang.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "employees")
public class PostCodeItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int postCode;
	
	@Column
	private String suburb;
	
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column (nullable = false, updatable = false)
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date updatedAt;
	
	@PrePersist
	public void onCreate() {
		Date timestamp = new Date();
		createdAt = timestamp;
		updatedAt = timestamp;
	}
	
	@PreUpdate
	public void onUpdate() {
		updatedAt = new Date();
	}

	

}
