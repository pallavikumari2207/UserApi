package com.bridgelabz.dto;

import org.springframework.stereotype.Component;

@Component
public class UpdatePassWordDto {

	private String passWord;

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}
