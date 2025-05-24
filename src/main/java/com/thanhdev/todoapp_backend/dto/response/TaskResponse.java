package com.thanhdev.todoapp_backend.dto.response;

import com.thanhdev.todoapp_backend.enums.TaskStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponse {
	String id;
	String title;
	String description;
	LocalDate dueDate;
	TaskStatus status;
}
