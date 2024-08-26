package com.RunAlliance_back.RunAlliance_back.repository;

import com.RunAlliance_back.RunAlliance_back.model.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
}
