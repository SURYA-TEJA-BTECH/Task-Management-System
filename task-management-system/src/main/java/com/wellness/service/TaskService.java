package com.wellness.service;

import java.util.List;

import com.wellness.entity.Task;
import com.wellness.exceptions.NoTasksPresentException;
import com.wellness.exceptions.TaskNotFoundException;
import com.wellness.model.TaskDto;

public interface TaskService {

	public List<Task> getAllTaks() throws NoTasksPresentException;

	public Task getTaskById(Integer taskid) throws TaskNotFoundException;

	public Task createTask(TaskDto taskDto);

	public void delelteTask(Integer taskId) throws TaskNotFoundException;

	public void changeTaskState(Integer taskId) throws TaskNotFoundException;

	public Task updateTask(TaskDto taskDto) throws TaskNotFoundException;

}
