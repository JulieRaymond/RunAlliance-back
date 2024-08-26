package com.RunAlliance_back.RunAlliance_back.repository;

import com.RunAlliance_back.RunAlliance_back.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunRepository extends JpaRepository<Run, Long> {
}
