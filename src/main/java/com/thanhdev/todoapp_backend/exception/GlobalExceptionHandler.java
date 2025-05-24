package com.thanhdev.todoapp_backend.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.thanhdev.todoapp_backend.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResponse<?>> handeJsonParseError(HttpMessageNotReadableException exception) {
		Throwable cause = exception.getCause();
		ApiResponse<?> apiResponse = new ApiResponse<>();

		if (cause instanceof InvalidFormatException formatException && formatException.getTargetType().isEnum()) {
			apiResponse.setCode(ErrorCode.INVALID_TASK_STATUS.getCode());
			apiResponse.setMessage(ErrorCode.INVALID_TASK_STATUS.getMessage());
			return ResponseEntity.badRequest().body(apiResponse);

		}

		apiResponse.setCode(ErrorCode.INVALID_KEY.getCode());
		apiResponse.setMessage(ErrorCode.INVALID_KEY.getMessage());
		return ResponseEntity.badRequest().body(apiResponse);
	}

	@ExceptionHandler(value = Exception.class)
	ResponseEntity<ApiResponse<?>> handlingRuntimeException(RuntimeException exception) {
		ApiResponse<?> apiResponse = new ApiResponse<>();

		apiResponse.setCode(ErrorCode.UNKNOWN_ERROR.getCode());
		apiResponse.setMessage(ErrorCode.UNKNOWN_ERROR.getMessage());

		return ResponseEntity.badRequest().body(apiResponse);
	}

	@ExceptionHandler(value = AppException.class)
	ResponseEntity<ApiResponse<?>> handlingAppException(AppException exception) {
		ErrorCode errorCode = exception.getErrorCode();

		ApiResponse<?> apiResponse = new ApiResponse<>();
		apiResponse.setCode(errorCode.getCode());
		apiResponse.setMessage(errorCode.getMessage());
		return ResponseEntity.badRequest().body(apiResponse);
	}


	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	ResponseEntity<ApiResponse<?>> handlingValidation(MethodArgumentNotValidException exception) {
		String enumKey = exception.getFieldError().getDefaultMessage();
		ErrorCode errorCode = ErrorCode.INVALID_KEY;
		try {
			errorCode = ErrorCode.valueOf(enumKey);
		} catch (IllegalArgumentException e) {

		}

		ApiResponse<?> apiResponse = new ApiResponse<>();
		apiResponse.setCode(errorCode.getCode());
		apiResponse.setMessage(errorCode.getMessage());
		return ResponseEntity.badRequest().body(apiResponse);
	}
}
