package com.bridgelabz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.Response.Response;
import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.model.User;
import com.bridgelabz.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		boolean maybeUser = service.register(user);
		 if (maybeUser)
			return ResponseEntity.ok().body(user);
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return service.getUsers();
	}
	
	@GetMapping("/verification/{token}")
	public ResponseEntity<Response> verification(@PathVariable("token") String token) {
		System.out.println("not in if");
		if (service.isVerifiedUser(token)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("User Verification Successfull", 202));
			
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Response("User Verification failed", 406));
	}

	
	@PutMapping("/Update/{id}")
	public  ResponseEntity<User> Updateuserlist(@RequestBody User user,@PathVariable long id) {
		return service.updateUser(user,id);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginDto loginDto) {
		return service.login(loginDto);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable long id){
		if(service.deleteUser(id))
		{
			return "deleted";
		}
		return "ID NOT FOUND";
		
	}
}
