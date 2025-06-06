package com.thanhdev.todoapp_backend.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
	INVALID_KEY(400, "Invalid message key", HttpStatus.BAD_REQUEST),
	UNKNOWN_ERROR(500, "An unknown error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR),
	INVALID_TASK_STATUS(400, "Invalid task status. Accepted values: TODO, IN_PROGRESS, DONE", HttpStatus.BAD_REQUEST),
	TASK_NOT_FOUND(404, "Task not found", HttpStatus.NOT_FOUND),
	USER_NOT_FOUND(404, "User not found", HttpStatus.NOT_FOUND),
	USER_EXISTED(400, "User already existed", HttpStatus.BAD_REQUEST),
	USER_NOT_EXISTED(404, "User not existed", HttpStatus.NOT_FOUND),

	USERNAME_INVALID(400, "Username must be at least 3 characters long", HttpStatus.BAD_REQUEST),
	PASSWORD_INVALID(400, "Password must be longer than 6 characters", HttpStatus.BAD_REQUEST),

	UNAUTHENTICATED(401, "Unauthenticated", HttpStatus.UNAUTHORIZED),

	EMAIL_REQUIRED(400, "Email is required", HttpStatus.BAD_REQUEST),
	EMAIL_BLANK(400, "Email must not be blank", HttpStatus.BAD_REQUEST),
	EMAIL_INVALID(400, "Email format is invalid", HttpStatus.BAD_REQUEST),

	TASK_STATUS_REQUIRED(400, "Task status is required", HttpStatus.BAD_REQUEST),
	TASK_TITLE_REQUIRED(400, "Task title must not be blank", HttpStatus.BAD_REQUEST),
	GENDER_REQUIRED(400, "Gender selection is required", HttpStatus.BAD_REQUEST),
	UNAUTHORIZED(403, "You do not have permission", HttpStatus.FORBIDDEN);

	int code;
	String message;
	HttpStatusCode statusCode;

	ErrorCode(int code, String message, HttpStatusCode statusCode) {
		this.code = code;
		this.message = message;
		this.statusCode = statusCode;
	}
}
