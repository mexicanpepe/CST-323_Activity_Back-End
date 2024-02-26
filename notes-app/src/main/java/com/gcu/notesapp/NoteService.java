package com.gcu.notesapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    public Note createNote(Note note, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("No user found with the provided ID"));

// Associate the Note with the User
        note.setUser(user);

        return noteRepository.save(note);
    }

    public void deleteNote(Long userId, Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new IllegalArgumentException("No note found with the provided ID"));

        if (!note.getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("The note does not belong to the user");
        }

        noteRepository.delete(note);
    }
}