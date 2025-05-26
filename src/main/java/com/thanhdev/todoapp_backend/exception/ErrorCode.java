package com.thanhdev.todoapp_backend.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
	INVALID_KEY(400, "Invalid message key"),
	UNKNOWN_ERROR(500, "An unknown error occurred. Please try again later."),
	INVALID_TASK_STATUS(400, "Invalid task status. Accepted values: TODO, IN_PROGRESS, DONE"),
	TASK_NOT_FOUND(404, "Task not found"),
	USER_NOT_FOUND(404, "User not found"),
	USER_EXISTED(400, "User already existed"),
	USERNAME_INVALID(400, "Username is invalid or missing"),
	PASSWORD_INVALID(400, "Password is invalid or missing"),

	EMAIL_REQUIRED(400, "Email is required"),
	EMAIL_BLANK(400, "Email must not be blank"),
	EMAIL_INVALID(400, "Email format is invalid"),

	TASK_STATUS_REQUIRED(400, "Task status is required"),
	TASK_TITLE_REQUIRED(400, "Task title must not be blank"),
	GENDER_REQUIRED(400, "Gender selection is required"),
	;


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
