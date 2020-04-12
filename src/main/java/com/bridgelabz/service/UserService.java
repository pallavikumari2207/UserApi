package com.bridgelabz.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.UpdatePassWordDto;
import com.bridgelabz.model.User;

public interface UserService {
	public boolean register(User u);

	public List<User> getUsers();

	public ResponseEntity<User> updateUser(User u,long id);
	
	public boolean deleteUser(long id);

	public ResponseEntity<User> login(LoginDto loginDto);
	
	boolean isVerifiedUser(String token);
	
	 ResponseEntity<User> updatePassWord(UpdatePassWordDto updatePassWordDto, String token);
}
