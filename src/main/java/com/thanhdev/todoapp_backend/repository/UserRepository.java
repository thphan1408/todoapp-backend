package com.thanhdev.todoapp_backend.repository;

import com.thanhdev.todoapp_backend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
	boolean existsByUsername(String username);
}
