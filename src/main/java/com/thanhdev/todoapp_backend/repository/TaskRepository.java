package com.thanhdev.todoapp_backend.repository;

import com.thanhdev.todoapp_backend.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, String> {

}
