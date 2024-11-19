package com.wellness.service;

import java.util.List;

import com.wellness.exceptions.NoTasksPresentException;
import com.wellness.exceptions.TaskNotFoundException;
import com.wellness.model.TaskDto;

public interface TaskService {

	public List<TaskDto> getAllTasks() throws NoTasksPresentException;

	public TaskDto getTaskById(Integer taskid) throws TaskNotFoundException;

	public TaskDto createTask(TaskDto taskDto);

	public void deleteTask(Integer taskId) throws TaskNotFoundException;

	public void changeTaskState(Integer taskId) throws TaskNotFoundException;

	public TaskDto updateTask(TaskDto taskDto) throws TaskNotFoundException;

}
