package com.thanhdev.todoapp_backend.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum GenderUser {
	MALE("Nam"), FEMALE("Nữ"), OTHER("Khác");
	String label;

	GenderUser(String label) {
		this.label = label;
	}
}
