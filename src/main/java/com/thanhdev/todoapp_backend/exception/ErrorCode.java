package com.thanhdev.todoapp_backend.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
	INVALID_KEY(400, "Invalid message key"),
	UNKNOWN_ERROR(500, "Đã xảy ra lỗi không xác định, vui lòng thử lại sau"),
	INVALID_TASK_STATUS(400, "Trạng thái công việc không hợp lệ. Chỉ chấp nhận: TODO, IN_PROGRESS, DONE"),
	TASK_NOT_FOUND(404, "Task not found");

	int code;
	String message;

	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
