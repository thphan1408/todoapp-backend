package com.thanhdev.todoapp_backend.mapper;

import com.thanhdev.todoapp_backend.dto.request.TaskCreationRequest;
import com.thanhdev.todoapp_backend.dto.request.TaskUpdateRequest;
import com.thanhdev.todoapp_backend.dto.response.TaskResponse;
import com.thanhdev.todoapp_backend.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {
	Task toTask(TaskCreationRequest request);

	TaskResponse toTaskResponse(Task task);

	void updateTask(@MappingTarget Task task, TaskUpdateRequest request);
}
