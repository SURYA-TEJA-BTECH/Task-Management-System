package com.wellness.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.wellness.entity.Task;
import com.wellness.enums.Status;
import com.wellness.exceptions.NoTasksPresentException;
import com.wellness.exceptions.TaskNotFoundException;
import com.wellness.mapper.TaskMapper;
import com.wellness.model.TaskDto;
import com.wellness.repo.TaskRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

	private TaskRepo taskRepo;

	private TaskMapper taskMapper;

	@Override
	public List<Task> getAllTaks() throws NoTasksPresentException {

		List<Task> tasks = taskRepo.findAll();

		if (tasks.isEmpty()) {
			throw new NoTasksPresentException();
		}

		return tasks;
	}

	@Override

	public Task getTaskById(Integer taskId) throws TaskNotFoundException {

		Optional<Task> task = taskRepo.findById(taskId);
		if (task.isPresent()) {
			return task.get();

		}
		throw new TaskNotFoundException("Task not found with ID: " + taskId);
	}

	@Override
	public Task createTask(TaskDto taskDto) {

		Task task = taskMapper.toEntity(taskDto);

		task.setIsActive(true);

		return taskRepo.save(task);

	}

	@Override
	// soft deletion
	public void delelteTask(Integer taskId) throws TaskNotFoundException {

		Task task = getTaskById(taskId);

		task.setIsActive(false);

		taskRepo.save(task);

	}

	@Override
	public void changeTaskState(Integer taskId) throws TaskNotFoundException {

		Optional<Task> task = taskRepo.findById(taskId);

		if (task.isEmpty()) {
			throw new TaskNotFoundException("Task not found with ID: " + taskId);

		}

		Task taskObj = task.get();

		taskObj.setStatus(Status.COMPLETED);
		taskRepo.save(taskObj);

	}

	@Override
	public Task updateTask(TaskDto taskDto) throws TaskNotFoundException {

		Task existingTask = getTaskById(taskDto.getId());

		existingTask.setTitle(taskDto.getTitle());

		existingTask.setDescription(taskDto.getDescription());

		existingTask.setDueDate(taskDto.getDeadline());

		existingTask.setStatus(taskDto.getStatus());

		taskRepo.save(existingTask); // Save the updated task

		return existingTask; // Return the updated task
	}

}
