    package com.videoc.example.videoc.DAO;

    import lombok.*;
    import org.springframework.data.mongodb.core.mapping.Document;
    import org.springframework.data.mongodb.core.mapping.Field;
    import org.springframework.data.mongodb.core.mapping.MongoId;

    import java.time.LocalDateTime;
    import java.util.List;

    @Setter

    @Getter

    @AllArgsConstructor

    @NoArgsConstructor

    @ToString
    @Data
    @Builder


    @Document(collection = "meetings")
    public class Meeting {
        @MongoId
        private String id;

        @Field("title")
        private String title;

        @Field("description")
        private String description;

        @Field("scheduledDateTime")
        private LocalDateTime scheduledDateTime;

        @Field("SessionURL")
        private String SessionURL;
        @Field("participants")
        private List<String> participants;

        // Constructors, getters, and setters
    }
