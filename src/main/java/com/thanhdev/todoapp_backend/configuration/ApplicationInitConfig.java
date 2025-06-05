package com.thanhdev.todoapp_backend.configuration;

import com.thanhdev.todoapp_backend.entity.Users;
import com.thanhdev.todoapp_backend.enums.RoleUser;
import com.thanhdev.todoapp_backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {

	PasswordEncoder passwordEncoder;

	// Hàm khởi tạo username: admin và password: admin ban đầu khi start application lần đầu
	@Bean
	ApplicationRunner applicationRunner(UserRepository userRepository) {
		return args -> {
			if (userRepository.findByUsername("admin")
			                  .isEmpty()) {
				var roles = new HashSet<String>();
				roles.add(RoleUser.ADMIN.name());
				Users users = Users.builder()
				                   .username("admin")
				                   .password(passwordEncoder.encode("admin"))
				                   .roles(roles)
				                   .build();

				userRepository.save(users);
				log.warn("Admin user has been created with default password: admin, please change it!");
			}
		};
	}

}
