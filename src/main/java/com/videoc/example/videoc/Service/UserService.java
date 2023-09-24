package com.videoc.example.videoc.Service;

import com.videoc.example.videoc.DAO.User;
import com.videoc.example.videoc.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        User userDocument = new User();

        userDocument.setEmail(user.getEmail());
        userDocument.setUsername(user.getUsername());
        userDocument.setPassword(user.getPassword());
        userDocument.setStatus("online");
        userRepository.save(userDocument);
    }

    public User login(User user) {
        User userDocument = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!userDocument.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Password incorrect");
        }

        userDocument.setStatus("online");
        userRepository.save(userDocument);

        User loggedInUser = new User();
        loggedInUser.setEmail(userDocument.getEmail());
        loggedInUser.setUsername(userDocument.getUsername());
        loggedInUser.setPassword(userDocument.getPassword());
        loggedInUser.setStatus(userDocument.getStatus());

        return loggedInUser;
    }

    public void logout(String email) {
        User userDocument = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userDocument.setStatus("offline");
        userRepository.save(userDocument);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
