package com.bridgelabz.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.Util.TokenUtil;
import com.bridgelabz.dto.NoteDto;
import com.bridgelabz.model.NotesEntity;
import com.bridgelabz.model.User;
import com.bridgelabz.repo.NoteRepo;
import com.bridgelabz.repo.UserRepo;
@Service
public class NoteService implements INoteService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private TokenUtil jwt;
	
	@Autowired
	NoteRepo repos;
	

	private NotesEntity notes = new NotesEntity();

	@Override
	public boolean createNote(NoteDto noteDto, String token) {
	Long Uid=jwt.decodeToken(token);
	Optional<User> user=repo.findById(Uid);
	boolean status=false;
	if(user.isPresent()) {
		BeanUtils.copyProperties(noteDto, notes);
		notes.setArchieved(false);
		notes.setColor("white");
		notes.setNotesCreatedDate(LocalDateTime.now());
		notes.setPinned(false);
		notes.setTrashed(false);
		user.get().getNote().add(notes);
		repos.save(notes);
		status=true;
	}
	return status;
		
	}


}
