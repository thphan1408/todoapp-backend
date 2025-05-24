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
	@NotBlank(message = "Tiêu đề không được để trống")
	String title;
	String description;
	LocalDate dueDate;

	@NotNull(message = "Trạng thái task là bắt buộc")
	TaskStatus status;
}
