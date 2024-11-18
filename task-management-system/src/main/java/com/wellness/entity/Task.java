package com.wellness.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellness.enums.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
@SQLRestriction("IS_ACTIVE <> false")
@Schema(hidden = true)
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;

	private String description;

	private LocalDate dueDate;

	@Enumerated(EnumType.STRING)
	private Status status;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalTime createdAt;

	@UpdateTimestamp
	private LocalTime updatedAt;

	@JsonIgnore
	private Boolean isActive;

}
