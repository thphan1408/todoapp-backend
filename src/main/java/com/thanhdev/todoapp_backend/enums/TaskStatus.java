package com.thanhdev.todoapp_backend.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {
	TODO("Cần làm"), IN_PROGRESS("Đang làm"), DONE("Hoàn thành");

	private final String label;

	TaskStatus(String label) {
		this.label = label;
	}
}
