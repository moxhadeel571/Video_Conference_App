package com.videoc.example.videoc.Repository;

import com.videoc.example.videoc.DAO.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeetingRepository extends MongoRepository<Meeting, String> {
    List<Meeting> findByScheduledDateTimeAfter(LocalDateTime scheduledDateTime);
    @Query("{'_id': ?0}")
    List<Meeting> findAllByMeeting();
}
