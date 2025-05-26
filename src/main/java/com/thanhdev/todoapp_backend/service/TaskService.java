package com.thanhdev.todoapp_backend.service;

import com.thanhdev.todoapp_backend.dto.request.TaskCreationRequest;
import com.thanhdev.todoapp_backend.dto.request.TaskUpdateRequest;
import com.thanhdev.todoapp_backend.dto.response.TaskResponse;
import com.thanhdev.todoapp_backend.entity.Tasks;
import com.thanhdev.todoapp_backend.exception.AppException;
import com.thanhdev.todoapp_backend.exception.ErrorCode;
import com.thanhdev.todoapp_backend.mapper.TaskMapper;
import com.thanhdev.todoapp_backend.repository.TaskRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskService {

	TaskRepository taskRepository;

	TaskMapper taskMapper;


	public Tasks createTask(TaskCreationRequest request) {
		if (request.getStatus() == null) {
			throw new AppException(ErrorCode.INVALID_TASK_STATUS);
		}

		// hoặc nếu bạn muốn giới hạn chỉ cho phép một số trạng thái tạo ban đầu
		//		if (request.getStatus() != TaskStatus.TODO) {
		//			throw new AppException(ErrorCode.INVALID_TASK_STATUS);
		//		}

		Tasks tasks = taskMapper.toTask(request);

		return taskRepository.save(tasks);
	}

	public List<Tasks> getTask() {return taskRepository.findAll();}

	public TaskResponse getTaskById(String taskId) {
		return taskMapper.toTaskResponse(taskRepository.findById(taskId)
		                                               .orElseThrow(() -> new AppException(ErrorCode.TASK_NOT_FOUND)));
	}

	public TaskResponse updateTask(String taskId, TaskUpdateRequest request) {
		Tasks tasks = taskRepository.findById(taskId)
		                            .orElseThrow(() -> new AppException(ErrorCode.TASK_NOT_FOUND));

		taskMapper.updateTask(tasks, request);

		return taskMapper.toTaskResponse(taskRepository.save(tasks));
	}

	public void deleteTask(String taskId) {
		Tasks tasks = taskRepository.findById(taskId)
		                            .orElseThrow(() -> new AppException(ErrorCode.TASK_NOT_FOUND));

		taskRepository.delete(tasks);
	}
}
