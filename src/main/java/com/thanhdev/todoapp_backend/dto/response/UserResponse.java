package com.thanhdev.todoapp_backend.dto.response;

import com.thanhdev.todoapp_backend.enums.GenderUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
	String id;
	String username;
	String password;
	String email;
	String firstName;
	String lastName;
	LocalDate dob;
	GenderUser gender;
}
