package com.thanhdev.todoapp_backend.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TaskStatus {
	TODO("Cần làm"), IN_PROGRESS("Đang làm"), DONE("Hoàn thành");

	String label;

	TaskStatus(String label) {
		this.label = label;
	}
}
