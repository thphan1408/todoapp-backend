package com.thanhdev.todoapp_backend.service;

import com.thanhdev.todoapp_backend.dto.request.AuthenticationRequest;
import com.thanhdev.todoapp_backend.exception.AppException;
import com.thanhdev.todoapp_backend.exception.ErrorCode;
import com.thanhdev.todoapp_backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

	UserRepository userRepository;

	public boolean authenticated(AuthenticationRequest request) {
		var user = userRepository.findByUsername(request.getUsername())
		                         .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

		return passwordEncoder.matches(request.getPassword(), user.getPassword());
	}
}
