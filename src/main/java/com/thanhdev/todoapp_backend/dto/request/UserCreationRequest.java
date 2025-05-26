package com.thanhdev.todoapp_backend.dto.request;

import com.thanhdev.todoapp_backend.enums.GenderUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

	@Size(min = 3, message = "USERNAME_INVALID")
	String username;
	@Size(min = 6, message = "PASSWORD_INVALID")
	String password;

	@Email(message = "EMAIL_INVALID")
	@NotNull(message = "EMAIL_REQUIRED")
	@NotBlank(message = "EMAIL_BLANK")
	String email;

	String firstName;
	String lastName;
	LocalDate dob;
	@NotNull(message = "GENDER_REQUIRED")
	GenderUser gender;
}
