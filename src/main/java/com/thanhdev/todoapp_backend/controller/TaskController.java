package com.thanhdev.todoapp_backend.controller;

import com.thanhdev.todoapp_backend.dto.request.TaskCreationRequest;
import com.thanhdev.todoapp_backend.dto.request.TaskUpdateRequest;
import com.thanhdev.todoapp_backend.dto.response.ApiResponse;
import com.thanhdev.todoapp_backend.dto.response.TaskResponse;
import com.thanhdev.todoapp_backend.entity.Task;
import com.thanhdev.todoapp_backend.service.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {

	TaskService taskService;

	@PostMapping()
	ApiResponse<Task> createTask(@RequestBody TaskCreationRequest request) {
		return ApiResponse.<Task>builder()
		                  .message("Create new task success")
		                  .result(taskService.createTask(request))
		                  .build();
	}

	@GetMapping()
	ApiResponse<List<Task>> getTasks() {
		return ApiResponse.<List<Task>>builder()
		                  .message("Get task by id success")
		                  .result(taskService.getTask())
		                  .build();
	}

	@GetMapping("/{taskId}")
	ApiResponse<TaskResponse> getTaskById(@PathVariable("taskId") String taskId) {
		return ApiResponse.<TaskResponse>builder()
		                  .message("Get task by id success")
		                  .result(taskService.getTaskById(taskId))
		                  .build();
	}

	@PutMapping("/{taskId}")
	ApiResponse<TaskResponse> updateTask(@PathVariable("taskId") String taskId,
	                                     @RequestBody TaskUpdateRequest request) {
		var result = taskService.updateTask(taskId, request);

		return ApiResponse.<TaskResponse>builder()
		                  .message("Update task success")
		                  .result(result)
		                  .build();
	}

	@DeleteMapping("/{taskId}")
	ApiResponse<?> deleteTask(@PathVariable("taskId") String taskId) {
		taskService.deleteTask(taskId);
		return ApiResponse.builder()
		                  .result(null)
		                  .message("Delete task success")
		                  .build();
	}
}
