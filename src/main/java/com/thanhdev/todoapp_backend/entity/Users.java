package com.thanhdev.todoapp_backend.entity;

import com.thanhdev.todoapp_backend.enums.GenderUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String username;
	String password;
	String email;
	String firstName;
	String lastName;
	LocalDate dob;
	@Enumerated(EnumType.STRING)
	GenderUser gender;

	// TODO: Refactor roles from Set<String> to Set<Role> enum for type-safety
	Set<String> roles;
}
