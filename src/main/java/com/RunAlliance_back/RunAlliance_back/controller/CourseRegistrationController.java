package com.RunAlliance_back.RunAlliance_back.controller;

import com.RunAlliance_back.RunAlliance_back.dto.CourseRegistrationDTO;
import com.RunAlliance_back.RunAlliance_back.service.CourseRegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class CourseRegistrationController {

    private final CourseRegistrationService courseRegistrationService;

    public CourseRegistrationController(CourseRegistrationService courseRegistrationService) {
        this.courseRegistrationService = courseRegistrationService;
    }

    @PostMapping("/register")
    public CourseRegistrationDTO registerForCourse(@RequestBody CourseRegistrationDTO courseRegistrationDTO) {
        return courseRegistrationService.registerForCourse(courseRegistrationDTO);
    }

    @DeleteMapping("/unregister/{registrationId}")
    public void unregisterFromCourse(@PathVariable Long registrationId) {
        courseRegistrationService.unregisterFromCourse(registrationId);
    }

    @GetMapping("/users/{userId}")
    public List<CourseRegistrationDTO> getRegistrationsByUserId(@PathVariable Long userId) {
        return courseRegistrationService.getRegistrationsByUserId(userId);
    }

    @GetMapping
    public List<CourseRegistrationDTO> getAllRegistrations() {
        return courseRegistrationService.getAllRegistrations();
    }
}
