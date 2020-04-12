package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.Response.Response;
import com.bridgelabz.dto.NoteDto;
import com.bridgelabz.service.INoteService;

@RestController
public class NotesController {
	@Autowired
	private INoteService noteServices;
	

	@PostMapping("/note/create")
	public ResponseEntity<Response> createNote(@RequestBody NoteDto noteDto, @RequestHeader("token") String token) {
		if(noteServices.createNote(noteDto, token)) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note created"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Unable to create Notes"));
		
	}
	
}
