package com.thanhdev.todoapp_backend.mapper;

import com.thanhdev.todoapp_backend.dto.request.UserCreationRequest;
import com.thanhdev.todoapp_backend.dto.request.UserUpdateRequest;
import com.thanhdev.todoapp_backend.dto.response.UserResponse;
import com.thanhdev.todoapp_backend.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(target = "id", ignore = true)
	Users toUser(UserCreationRequest request);

	UserResponse toUserResponse(Users users);

	@Mapping(target = "id", ignore = true)
	void updateUser(@MappingTarget Users users, UserUpdateRequest request);

}
