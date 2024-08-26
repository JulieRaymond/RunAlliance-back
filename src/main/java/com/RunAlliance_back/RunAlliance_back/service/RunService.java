package com.RunAlliance_back.RunAlliance_back.service;

import com.RunAlliance_back.RunAlliance_back.dto.RunDTO;
import com.RunAlliance_back.RunAlliance_back.model.Run;
import com.RunAlliance_back.RunAlliance_back.mapper.RunMapper;
import com.RunAlliance_back.RunAlliance_back.repository.RunRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RunService {

    private final RunRepository runRepository;
    private final RunMapper runMapper;

    public RunService(RunRepository runRepository, RunMapper runMapper) {
        this.runRepository = runRepository;
        this.runMapper = runMapper;
    }

    public List<RunDTO> getAllRuns() {
        return runRepository.findAll().stream()
                .map(runMapper::runToRunDTO)
                .collect(Collectors.toList());
    }

    public RunDTO getRunById(Long id) {
        Run run = runRepository.findById(id).orElse(null);
        return runMapper.runToRunDTO(run);
    }

    public RunDTO createRun(RunDTO runDTO) {
        Run run = runMapper.runDTOToRun(runDTO);
        Run savedRun = runRepository.save(run);
        return runMapper.runToRunDTO(savedRun);
    }

    public RunDTO updateRun(Long id, RunDTO runDTO) {
        Run run = runRepository.findById(id).orElse(null);
        if (run != null) {
            runMapper.updateRunFromDTO(runDTO, run);
            Run updatedRun = runRepository.save(run);
            return runMapper.runToRunDTO(updatedRun);
        }
        return null;
    }

    public void deleteRun(Long id) {
        runRepository.deleteById(id);
    }
}
