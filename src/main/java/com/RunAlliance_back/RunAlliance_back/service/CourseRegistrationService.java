package com.RunAlliance_back.RunAlliance_back.service;

import com.RunAlliance_back.RunAlliance_back.dto.CourseRegistrationDTO;
import com.RunAlliance_back.RunAlliance_back.mapper.CourseRegistrationMapper;
import com.RunAlliance_back.RunAlliance_back.repository.CourseRegistrationRepository;
import com.RunAlliance_back.RunAlliance_back.repository.RunRepository;
import com.RunAlliance_back.RunAlliance_back.repository.UserRepository;
import com.RunAlliance_back.RunAlliance_back.model.CourseRegistration;
import com.RunAlliance_back.RunAlliance_back.model.Run;
import com.RunAlliance_back.RunAlliance_back.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseRegistrationService {

    private final CourseRegistrationRepository courseRegistrationRepository;
    private final CourseRegistrationMapper courseRegistrationMapper;
    private final RunRepository runRepository;
    private final UserRepository userRepository;

    public CourseRegistrationService(CourseRegistrationRepository courseRegistrationRepository, CourseRegistrationMapper courseRegistrationMapper, RunRepository runRepository, UserRepository userRepository) {
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.courseRegistrationMapper = courseRegistrationMapper;
        this.runRepository = runRepository;
        this.userRepository = userRepository;
    }

    public List<CourseRegistrationDTO> getAllRegistrations() {
        return courseRegistrationRepository.findAll().stream()
                .map(courseRegistrationMapper::courseRegistrationToCourseRegistrationDTO)
                .collect(Collectors.toList());
    }

    public CourseRegistrationDTO registerForCourse(CourseRegistrationDTO courseRegistrationDTO) {
        Run run = runRepository.findById(courseRegistrationDTO.getRunId()).orElse(null);
        User user = userRepository.findById(courseRegistrationDTO.getUserId()).orElse(null);
        if (run != null && user != null) {
            CourseRegistration courseRegistration = courseRegistrationMapper.courseRegistrationDTOToCourseRegistration(courseRegistrationDTO);
            courseRegistration.setRun(run);
            courseRegistration.setUser(user);
            CourseRegistration savedRegistration = courseRegistrationRepository.save(courseRegistration);
            return courseRegistrationMapper.courseRegistrationToCourseRegistrationDTO(savedRegistration);
        }
        return null;
    }

    public void unregisterFromCourse(Long registrationId) {
        courseRegistrationRepository.deleteById(registrationId);
    }

    public CourseRegistrationDTO getRegistrationById(Long id) {
        CourseRegistration registration = courseRegistrationRepository.findById(id).orElse(null);
        return courseRegistrationMapper.courseRegistrationToCourseRegistrationDTO(registration);
    }

    public List<CourseRegistrationDTO> getRegistrationsByUserId(Long userId) {
        return courseRegistrationRepository.findAll().stream()
                .filter(registration -> registration.getUser().getId().equals(userId))  // Changement ici
                .map(courseRegistrationMapper::courseRegistrationToCourseRegistrationDTO)
                .collect(Collectors.toList());
    }

}
