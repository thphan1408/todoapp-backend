package com.thanhdev.todoapp_backend.entity;

import com.thanhdev.todoapp_backend.enums.GenderUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
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
}
