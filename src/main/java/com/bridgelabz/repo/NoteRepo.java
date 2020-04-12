package com.bridgelabz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.model.NotesEntity;

public interface NoteRepo extends JpaRepository<NotesEntity, Long> {

}
