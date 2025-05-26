package com.thanhdev.todoapp_backend.entity;

import com.thanhdev.todoapp_backend.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tasks {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String title;
	String description;
	LocalDate dueDate;

	@Enumerated(EnumType.STRING)
	TaskStatus status;
}
