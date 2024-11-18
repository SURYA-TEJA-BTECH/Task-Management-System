package com.wellness.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellness.entity.Task;
import com.wellness.exceptions.NoTasksPresentException;
import com.wellness.exceptions.TaskNotFoundException;
import com.wellness.model.TaskDto;
import com.wellness.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Tag(name = "Task API", description = "This API consists of endpoints for managing tasks, including creating, retrieving, updating, and deleting tasks.")
public class TaskOperationsController {

	private TaskService taskService;

	@Operation(summary = "Get all tasks", description = "Fetches all tasks from the system.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "No tasks found") })
	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getAllTasks() throws NoTasksPresentException {

		return new ResponseEntity<>(taskService.getAllTaks(), HttpStatus.OK);

	}

	@Operation(summary = "Get task by ID", description = "Fetches a task by its unique identifier.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Task retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Task not found") })

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Integer id) throws TaskNotFoundException {

		return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);

	}

	@Operation(summary = "Create a new task", description = "Creates a new task with the provided details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Task created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input: Validation failed for the provided task details") })
	@PostMapping("/tasks")
	public ResponseEntity<Task> createTask(@Valid @RequestBody TaskDto taskDto) {

		return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);

	}

	@Operation(summary = "Update an existing task", description = "Updates the details of an existing task.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Task updated successfully"),
			@ApiResponse(responseCode = "404", description = "Task not found") })
	@PutMapping("/tasks/{id}") // if u want to update more than one field then put
	public ResponseEntity<Task> updateTask(@PathVariable Integer id, @Valid @RequestBody TaskDto taskDto)
			throws TaskNotFoundException {

		taskDto.setId(id);

		return new ResponseEntity<>(taskService.updateTask(taskDto), HttpStatus.OK);
	}

	@Operation(summary = "Mark a task as complete", description = "Changes the status of a task to 'completed'.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Task status updated successfully"),
			@ApiResponse(responseCode = "404", description = "Task not found") })
	@PatchMapping("/tasks/{id}/complete") // if u want to update one field then patch mapping
	public ResponseEntity<Void> changeStatus(@PathVariable Integer id) throws TaskNotFoundException {
		System.out.println("TaskOperationsController.changeStatus()");
		taskService.changeTaskState(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "Delete a task", description = "Deletes a task by its unique identifier.")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Task not found") })
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Integer id) throws TaskNotFoundException {

		taskService.delelteTask(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}