package com.thanhdev.todoapp_backend.controller;

import com.nimbusds.jose.JOSEException;
import com.thanhdev.todoapp_backend.dto.request.AuthenticationRequest;
import com.thanhdev.todoapp_backend.dto.request.IntrospectRequest;
import com.thanhdev.todoapp_backend.dto.response.ApiResponse;
import com.thanhdev.todoapp_backend.dto.response.AuthenticationResponse;
import com.thanhdev.todoapp_backend.dto.response.IntrospectResponse;
import com.thanhdev.todoapp_backend.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@Controller
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

	AuthenticationService authenticationService;

	@PostMapping("/login")
	ApiResponse<AuthenticationResponse> authenticated(@RequestBody AuthenticationRequest request) {
		var result = authenticationService.authenticated(request);

		return ApiResponse.<AuthenticationResponse>builder()
		                  .result(result)
		                  .build();
	}

	@PostMapping("/introspect")
	ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
			throws ParseException, JOSEException {
		var result = authenticationService.introspect(request);

		return ApiResponse.<IntrospectResponse>builder()
		                  .result(result)
		                  .build();
	}

}
