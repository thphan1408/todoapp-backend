package com.thanhdev.todoapp_backend.mapper;

import com.thanhdev.todoapp_backend.dto.request.TaskCreationRequest;
import com.thanhdev.todoapp_backend.dto.request.TaskUpdateRequest;
import com.thanhdev.todoapp_backend.dto.response.TaskResponse;
import com.thanhdev.todoapp_backend.entity.Tasks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {
	@Mapping(target = "id", ignore = true)
	Tasks toTask(TaskCreationRequest request);

	TaskResponse toTaskResponse(Tasks tasks);

	@Mapping(target = "id", ignore = true)
	void updateTask(@MappingTarget Tasks tasks, TaskUpdateRequest request);
}
