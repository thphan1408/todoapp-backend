package com.thanhdev.todoapp_backend.repository;

import com.thanhdev.todoapp_backend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {
	boolean existsByUsername(String username);

	Optional<Users> findByUsername(String username);
}
