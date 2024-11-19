package com.wellness.service;

import java.util.ArrayList;
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
	public List<TaskDto> getAllTasks() throws NoTasksPresentException {

		// getting all the task will be time taken if huge no tasks are available in
		// database
		List<Task> tasks = taskRepo.findAll();

		if (tasks.isEmpty()) {
			throw new NoTasksPresentException();
		}

		List<TaskDto> taskDtoObjects = new ArrayList<>(tasks.size());

		for (Task task : tasks) {

			taskDtoObjects.add(taskMapper.toTaskDto(task));

		}

		return taskDtoObjects;
	}

	@Override

	public TaskDto getTaskById(Integer taskId) throws TaskNotFoundException {

		Optional<Task> task = taskRepo.findById(taskId);
		if (task.isPresent()) {
			Task taskObject = task.get();

			return taskMapper.toTaskDto(taskObject);

		}
		throw new TaskNotFoundException("Task not found with ID: " + taskId);
	}

	@Override
	public TaskDto createTask(TaskDto taskDto) {

		Task task = taskMapper.toEntity(taskDto);

		task.setIsActive(true);

		Task updatedObj = taskRepo.save(task);

		return taskMapper.toTaskDto(updatedObj);

	}

	@Override
	// soft deletion
	public void deleteTask(Integer taskId) throws TaskNotFoundException {

		Task task = retrieveTaskById(taskId);

		task.setIsActive(false);

		taskRepo.save(task);

	}

	@Override
	public void changeTaskState(Integer taskId) throws TaskNotFoundException {

		Task task = retrieveTaskById(taskId);

		task.setStatus(Status.COMPLETED);

		taskRepo.save(task);

	}

	@Override
	public TaskDto updateTask(TaskDto taskDto) throws TaskNotFoundException {

		Task existingTask = retrieveTaskById(taskDto.getId());

		existingTask.setTitle(taskDto.getTitle());

		existingTask.setDescription(taskDto.getDescription());

		existingTask.setDueDate(taskDto.getDeadline());

		existingTask.setStatus(taskDto.getStatus());

		taskRepo.save(existingTask); // Save the updated task

		return taskMapper.toTaskDto(existingTask);

	}

	private Task retrieveTaskById(Integer taskId) throws TaskNotFoundException {
		return taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));
	}

}
