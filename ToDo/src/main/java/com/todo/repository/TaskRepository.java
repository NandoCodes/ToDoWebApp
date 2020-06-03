package com.todo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.domain.Task;
import com.todo.domain.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findAllByUser(User user);


}
