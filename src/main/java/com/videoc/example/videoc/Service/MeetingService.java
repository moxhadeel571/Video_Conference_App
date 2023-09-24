package com.videoc.example.videoc.Service;

import com.videoc.example.videoc.DAO.Meeting;
import com.videoc.example.videoc.DAO.User;
import com.videoc.example.videoc.Repository.MeetingRepository;
import com.videoc.example.videoc.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingService {
    private  UserRepository userRepository;

    private final MeetingRepository meetingRepository;


    @Autowired
    public MeetingService(UserRepository userRepository, MeetingRepository meetingRepository) {
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;

    }

    public void scheduleMeeting(Meeting meeting) {
        Meeting scheduled = new Meeting();
        scheduled.setTitle(meeting.getTitle());
        scheduled.setDescription(meeting.getDescription());
        scheduled.setScheduledDateTime(meeting.getScheduledDateTime());
        scheduled.setSessionURL(meeting.getSessionURL());
        scheduled.setId(meeting.getId());

        List<User> usersWithEmails = userRepository.findAll();

// Extract the email addresses from the user objects
        List<String> participantEmails = usersWithEmails.stream()
                .map(User::getEmail)
                .collect(Collectors.toList());

// Set the participants' email addresses in your 'scheduled' object
        scheduled.setParticipants(participantEmails);

        meetingRepository.save(scheduled);
    }




    public List<Meeting> getScheduledMeetingsAfter(LocalDateTime scheduledDateTime) {
        return meetingRepository.findByScheduledDateTimeAfter(scheduledDateTime);
    }


    // Other methods for updating, canceling, and managing meetings
}
