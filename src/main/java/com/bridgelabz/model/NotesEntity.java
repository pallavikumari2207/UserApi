package com.bridgelabz.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Entity
public class  NotesEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notesId;
    @Column     
	private String title;
    @Column 
	private String description;
    @Column 
	private String color;
    @Column 
	private boolean isArchieved;
    @Column 
	private boolean isPinned;
    @Column 
	private boolean isTrashed;
    @Column 
	private LocalDateTime notesCreatedDate;
	 @Column 
	private LocalDateTime updateDate;
	 @Column 
	private LocalDateTime reminder;
   

	public Long getNotesId() {
		return notesId;
	}

	public void setNotesId(Long notesId) {
		this.notesId = notesId;
	}

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isArchieved() {
		return isArchieved;
	}

	public void setArchieved(boolean isArchieved) {
		this.isArchieved = isArchieved;
	}

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public boolean isTrashed() {
		return isTrashed;
	}

	public void setTrashed(boolean isTrashed) {
		this.isTrashed = isTrashed;
	}

	public LocalDateTime getNotesCreatedDate() {
		return notesCreatedDate;
	}

	public void setNotesCreatedDate(LocalDateTime notesCreatedDate) {
		this.notesCreatedDate = notesCreatedDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public LocalDateTime getReminder() {
		return reminder;
	}

	public void setReminder(LocalDateTime reminder) {
		this.reminder = reminder;
	}


}
