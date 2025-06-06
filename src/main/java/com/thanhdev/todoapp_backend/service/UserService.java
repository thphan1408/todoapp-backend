package com.thanhdev.todoapp_backend.service;

import com.thanhdev.todoapp_backend.dto.request.UserCreationRequest;
import com.thanhdev.todoapp_backend.dto.request.UserUpdateRequest;
import com.thanhdev.todoapp_backend.dto.response.UserResponse;
import com.thanhdev.todoapp_backend.entity.Users;
import com.thanhdev.todoapp_backend.enums.RoleUser;
import com.thanhdev.todoapp_backend.exception.AppException;
import com.thanhdev.todoapp_backend.exception.ErrorCode;
import com.thanhdev.todoapp_backend.mapper.UserMapper;
import com.thanhdev.todoapp_backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
	UserRepository userRepository;
	UserMapper userMapper;
	PasswordEncoder passwordEncoder;

	public UserResponse createUser(UserCreationRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new AppException(ErrorCode.USER_EXISTED);
		}

		Users users = userMapper.toUser(request);

		users.setPassword(passwordEncoder.encode(request.getPassword()));

		HashSet<String> roles = new HashSet<String>();
		roles.add(RoleUser.USER.name());

		users.setRoles(roles);

		return userMapper.toUserResponse(userRepository.save(users));
	}

	public UserResponse getMyInfo() {
		var context = SecurityContextHolder.getContext();

		String name = context.getAuthentication()
		                     .getName();

		Users users = userRepository.findByUsername(name)
		                            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

		return userMapper.toUserResponse(users);
	}

	@PreAuthorize("hasRole('ADMIN')")
	public List<UserResponse> getAllUser() {

		log.info("In method get Users");
		return userRepository.findAll()
		                     .stream()
		                     .map(userMapper::toUserResponse)
		                     .toList();

	}

	//	@PreAuthorize("hasRole('ADMIN')")
	@PostAuthorize("returnObject.username == authentication.name")
	public UserResponse getUserById(String userId) {
		log.info("In method get Users by ID");
		return userMapper.toUserResponse(userRepository.findById(userId)
		                                               .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
	}

	public UserResponse updateUser(String userId, UserUpdateRequest request) {
		Users users = userRepository.findById(userId)
		                            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

		userMapper.updateUser(users, request);

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

		users.setPassword(passwordEncoder.encode(request.getPassword()));

		return userMapper.toUserResponse(userRepository.save(users));
	}

	public void deleteUser(String userId) {
		Users users = userRepository.findById(userId)
		                            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

		userRepository.delete(users);
	}

}
