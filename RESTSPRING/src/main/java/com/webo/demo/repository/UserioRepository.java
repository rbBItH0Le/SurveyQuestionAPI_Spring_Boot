package com.webo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webo.demo.model.User;
@Repository
public interface UserioRepository extends JpaRepository<User,Long>{
	User findByUsername(String username);
}
