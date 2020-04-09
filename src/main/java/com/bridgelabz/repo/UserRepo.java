package com.bridgelabz.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String id);
}
