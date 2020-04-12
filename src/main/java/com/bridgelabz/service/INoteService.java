package com.bridgelabz.service;

import java.util.List;

import com.bridgelabz.dto.NoteDto;
import com.bridgelabz.model.NotesEntity;

public interface INoteService {

	boolean createNote(NoteDto noteDto, String token);

	
}
