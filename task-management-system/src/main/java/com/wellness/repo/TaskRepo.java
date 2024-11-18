package com.wellness.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellness.entity.Task;

public interface TaskRepo extends JpaRepository<Task, Integer> {

}
