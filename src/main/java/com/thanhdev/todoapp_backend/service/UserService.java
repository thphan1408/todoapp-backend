package com.thanhdev.todoapp_backend.service;

import com.thanhdev.todoapp_backend.dto.request.UserCreationRequest;
import com.thanhdev.todoapp_backend.dto.request.UserUpdateRequest;
import com.thanhdev.todoapp_backend.dto.response.UserResponse;
import com.thanhdev.todoapp_backend.entity.Users;
import com.thanhdev.todoapp_backend.exception.AppException;
import com.thanhdev.todoapp_backend.exception.ErrorCode;
import com.thanhdev.todoapp_backend.mapper.UserMapper;
import com.thanhdev.todoapp_backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
	UserRepository userRepository;
	UserMapper userMapper;

	public Users createUser(UserCreationRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new AppException(ErrorCode.USER_EXISTED);
		}

		Users users = userMapper.toUser(request);

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

		users.setPassword(passwordEncoder.encode(request.getPassword()));

		return userRepository.save(users);
	}

	public List<Users> getAllUser() {
		return userRepository.findAll();
	}

	public UserResponse getUserById(String userId) {
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
