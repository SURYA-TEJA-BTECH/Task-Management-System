package com.wellness.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.wellness.entity.Task;
import com.wellness.model.TaskDto;

@Mapper(componentModel = "spring")
public interface TaskMapper {

	@Mapping(source = "deadline", target = "dueDate")
	Task toEntity(TaskDto taskDto);

	@Mapping(source = "dueDate", target = "deadline")
	TaskDto toTaskDto(Task task);
}
