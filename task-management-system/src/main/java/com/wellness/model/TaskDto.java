package com.wellness.model;

import java.time.LocalDate;

import com.wellness.enums.Status;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskDto {

	
	private Integer id;

	@NotEmpty(message = "Title cannot be empty.") // Custom error message for empty title
	@Size(min = 5, max = 20, message = "Title must be between 5 and 20 characters.") // Custom error message for title
	private String title;

	@NotEmpty(message = "Description cannot be empty.") // Custom error message for empty description
	@Size(min = 10, max = 75, message = "Description must be between 10 and 75 characters.") // Custom error message
																								// for description size
																								// validation
	private String description;

	@Future(message = "Deadline must be a future date.") // Custom error message for future date validation
	private LocalDate deadline;

	@NotNull(message = "Status cannot be null.") // Custom error message for null status
	private Status status;
}
