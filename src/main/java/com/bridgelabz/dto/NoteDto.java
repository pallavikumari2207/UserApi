package com.bridgelabz.dto;

import org.springframework.stereotype.Component;

@Component
public class NoteDto {
	
	  private String title;
	   
	   public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String description; 

}
