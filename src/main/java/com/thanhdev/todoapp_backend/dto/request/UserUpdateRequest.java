package com.thanhdev.todoapp_backend.dto.request;

import com.thanhdev.todoapp_backend.enums.GenderUser;
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
public class UserUpdateRequest {
	String username;
	String password;
	String firstName;
	String lastName;
	LocalDate dob;
	GenderUser gender;
}
