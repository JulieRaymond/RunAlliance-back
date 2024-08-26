// src/main/java/com/RunApp/RunApp/entity/CourseRegistration.java
package com.RunAlliance_back.RunAlliance_back.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Course_registration")
public class CourseRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private Long registrationId;

    @ManyToOne
    @JoinColumn(name = "run_id", nullable = false)
    private Run run;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
