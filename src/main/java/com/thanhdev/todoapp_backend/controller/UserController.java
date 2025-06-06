package com.thanhdev.todoapp_backend.controller;

import com.thanhdev.todoapp_backend.dto.request.UserCreationRequest;
import com.thanhdev.todoapp_backend.dto.request.UserUpdateRequest;
import com.thanhdev.todoapp_backend.dto.response.ApiResponse;
import com.thanhdev.todoapp_backend.dto.response.UserResponse;
import com.thanhdev.todoapp_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
	UserService userService;

	@PostMapping()
	ApiResponse<UserResponse> createUser(@Valid @RequestBody UserCreationRequest request) {
		return ApiResponse.<UserResponse>builder()
		                  .message("Create new user success")
		                  .result(userService.createUser(request))
		                  .build();
	}

	@GetMapping()
	ApiResponse<List<UserResponse>> getUsers() {

		//		var authentication = SecurityContextHolder.getContext()
		//		                                          .getAuthentication();
		//
		//		log.info("Username: {}", authentication.getName());
		//		authentication.getAuthorities()
		//		              .forEach(grantedAuthority -> log.info("Role: {}", grantedAuthority.getAuthority()));


		return ApiResponse.<List<UserResponse>>builder()
		                  .message("Get user by id success")
		                  .result(userService.getAllUser())
		                  .build();
	}

	@GetMapping("/{userId}")
	ApiResponse<UserResponse> getUserById(@PathVariable("userId") String userId) {
		return ApiResponse.<UserResponse>builder()
		                  .message("Get user by id success")
		                  .result(userService.getUserById(userId))
		                  .build();
	}

	@GetMapping("/myInfo")
	ApiResponse<UserResponse> getMyInfo() {
		return ApiResponse.<UserResponse>builder()
		                  .message("Get my info success")
		                  .result(userService.getMyInfo())
		                  .build();
	}

	@PutMapping("/{userId}")
	ApiResponse<UserResponse> updateUser(@PathVariable("userId") String userId,
	                                     @RequestBody UserUpdateRequest request) {
		var result = userService.updateUser(userId, request);

		return ApiResponse.<UserResponse>builder()
		                  .message("Update user success")
		                  .result(result)
		                  .build();
	}

	@DeleteMapping("/{userId}")
	ApiResponse<?> deleteUser(@PathVariable("userId") String userId) {
		userService.deleteUser(userId);
		return ApiResponse.builder()
		                  .result(null)
		                  .message("Delete user success")
		                  .build();
	}

}
