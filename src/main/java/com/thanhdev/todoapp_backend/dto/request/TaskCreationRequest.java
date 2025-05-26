package com.thanhdev.todoapp_backend.dto.request;

import com.thanhdev.todoapp_backend.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskCreationRequest {
	@NotBlank(message = "TASK_TITLE_REQUIRED")
	String title;
	String description;
	LocalDate dueDate;

	@NotNull(message = "TASK_STATUS_REQUIRED")
	TaskStatus status;
}
