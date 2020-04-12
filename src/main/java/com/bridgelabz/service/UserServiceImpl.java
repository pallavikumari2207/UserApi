package com.bridgelabz.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.Util.EmailService;
import com.bridgelabz.Util.TokenUtil;
import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.UpdatePassWordDto;
import com.bridgelabz.exception.UserAuthenticationException;
import com.bridgelabz.exception.UserCredentialsException;
import com.bridgelabz.model.Mail;
import com.bridgelabz.model.User;
import com.bridgelabz.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private TokenUtil jwt;

	@Autowired
	private Mail mail;

	@Autowired
	private EmailService email;

	public static final String SENDER_EMAIL_ID = System.getenv("email");
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private String 	varificationLink;
	
	private String subject;
	
	
	

	@Override
	public boolean register(User u) {
	    repo.save(u);
		varificationLink="http://localhost:8080/verification/"+ jwt.createToken(u.getId());
		System.out.println("link is"+varificationLink);
		
		subject="Fundoo verification link";
		email.sendMail(varificationLink,subject,u.getEmail());
		return true;
		
	}
	
	@Override
	public boolean isVerifiedUser(String token) {
		Long verifactionUserId = jwt.decodeToken(token);
		boolean status=false;
		System.out.println("id is"+ verifactionUserId);
		if (verifactionUserId > 0) {
			System.out.println(" inside else varification "+ verifactionUserId);
			Optional<User> dbuser = repo.findById(verifactionUserId);
			if(dbuser.isPresent()) {
				User u=dbuser.get();
				u.setVerified(true);
				repo.save(u);
				System.out.println("isVrified status");
				status=true;
			}
		
		}
		return status;
	
	}

	@Override
	public List<User> getUsers() {
		return repo.findAll();
	}
	
	@Override
	public ResponseEntity<User> login(LoginDto logindto)
	{
		Optional<User> dbUser=repo.findByEmail(logindto.getEmailId());
		if(dbUser.isPresent()) {
		User user=dbUser.get();
		System.out.println(logindto.getPassWord());
		System.out.println( user.getPassword());
		System.out.println("varification"+user.isVerified());
			if (logindto.getPassWord().equals(user.getPassword())) {
				if (user.isVerified()) {
					System.out.println("varification"+user.isVerified());
					return ResponseEntity.ok().body(user);}}
		
			else {
				System.out.println("in else");
				String verificationLink = "http://localhost:8080/user/verification/"
						+ jwt.createToken(user.getId());

				mail.setContent(verificationLink);
				mail.setFrom(SENDER_EMAIL_ID);
				mail.setSubject("Fundoo Verification mail");
				mail.setTo(user.getEmail());
				mail.setSentDate(new Date(0));
				email.sendSimpleMessage(mail);
			}
	}
		throw new UserCredentialsException("User Credentials are Wrong");
	}
	

	
//	@Modifying
//	@Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
//	void setUserInfoById(String firstname, String lastname, Integer userId);
    @Override
	public ResponseEntity<User> updateUser(User u,long id) {
    	Optional<User> dbCustomer = repo.findById(id);
	    if(dbCustomer.isPresent()) {
	        User existingUser = dbCustomer.get();
	        existingUser=u;
	 
    return ResponseEntity.ok().body(existingUser); 
	}
	return ResponseEntity.notFound().build();   
    }

@Override
public boolean deleteUser(long id) {
	Optional<User> dbCustomer = repo.findById(id);
	 if(dbCustomer.isPresent()) {
		 User existingUser = dbCustomer.get();
		 repo.delete(existingUser);
		 return true;
	 }
	 else
	 {return false;}
	
}

@Override
public ResponseEntity<User> updatePassWord(UpdatePassWordDto updatePassWordDto, String token) {

	Long userId = jwt.decodeToken(token);
	System.out.println("id is:"+userId);
	if(userId>0) {
		Optional<User> dbUser = repo.findById(userId);
		if(dbUser.isPresent())
		{
			System.out.println("in if block of updatepassword");
		 User user=dbUser.get();
		String updatedPassWord = bCryptPasswordEncoder.encode(updatePassWordDto.getPassWord());
		System.out.println("bcrypted pass"+updatedPassWord );
		 user.setPassword(updatedPassWord);
		 repo.save(user);
		 return ResponseEntity.ok().body(user); 
		}	
}
	return ResponseEntity.notFound().build();
}}
 
 
