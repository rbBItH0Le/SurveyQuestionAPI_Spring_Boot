package com.webo.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webo.demo.model.Users;

public interface UserRepository extends JpaRepository<Users,Long> {
	List<Users> findByRole(String Role);
}
