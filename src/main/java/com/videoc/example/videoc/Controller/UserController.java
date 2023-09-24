package com.videoc.example.videoc.Controller;

import com.videoc.example.videoc.DAO.Meeting;
import com.videoc.example.videoc.DAO.User;
import com.videoc.example.videoc.Repository.MeetingRepository;
import com.videoc.example.videoc.Service.MeetingService;
import com.videoc.example.videoc.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

    private  UserService service;
    @Autowired
    private MeetingService meetingService;
    private MeetingRepository meetingRepository;
    @Autowired
    public UserController(UserService service, MeetingRepository meetingRepository) {
        this.service = service;
        this.meetingRepository = meetingRepository;
    }


    @PostMapping
    public void register(
            @RequestBody User user
    ) {
        service.register(user);
    }

        @PostMapping("/saveMeeting")
        public void saveMeeting(@RequestBody Meeting meeting) {
            meetingService.scheduleMeeting(meeting);
        }


    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return service.login(user);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody User email) {
        service.logout(email.getEmail());
    }


    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }
    @GetMapping("/meetings")
    public List<Meeting> findAllMeetings() {
        return meetingRepository.findAll();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }

}
