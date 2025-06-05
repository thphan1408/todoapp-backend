package com.thanhdev.todoapp_backend.dto.response;

import com.thanhdev.todoapp_backend.enums.GenderUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
	String id;
	String username;
	String email;
	String firstName;
	String lastName;
	LocalDate dob;
	GenderUser gender;

	// TODO: Refactor roles from Set<String> to Set<Role> enum for type-safety
	Set<String> roles;
}
