package com.gcu.notesapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws DataIntegrityViolationException {
        // Check if the user already exists by username or email
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent() ||
                             userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {

            throw new DataIntegrityViolationException("User with given username or email already exists");
        }

        // Save the new user to the database
        return userRepository.save(user);
    }

    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                                   .orElseThrow(() -> new RuntimeException("User is not found"));

        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

}
