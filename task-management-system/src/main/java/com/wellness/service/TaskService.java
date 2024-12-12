package com.wellness.service;

import java.util.List;

import com.wellness.exceptions.NoTasksPresentException;
import com.wellness.exceptions.TaskNotFoundException;
import com.wellness.model.TaskDto;

public interface TaskService {

	public List<TaskDto> getAllTasks() ;

	public TaskDto getTaskById(Integer taskid) ;

	public TaskDto createTask(TaskDto taskDto);

	public void deleteTask(Integer taskId) ;

	public void changeTaskState(Integer taskId)  ;

	public TaskDto updateTask(TaskDto taskDto) ;

}
