package com.gcu.notesapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/{userId}")
    public List<Note> getNotesByUserId(@PathVariable Long userId) {
        return noteRepository.findByUserUserId(userId);
    }

    @PostMapping("/{userId}")
    public Note createNote(@RequestBody Note note, @PathVariable Long userId) {
        // Call NoteService to create a new note
        return noteService.createNote(note, userId);
    }

    @DeleteMapping("/{userId}/{noteId}")
    public void deleteNote(@PathVariable Long userId, @PathVariable Long noteId) {

        noteService.deleteNote(userId, noteId);
    }
}
