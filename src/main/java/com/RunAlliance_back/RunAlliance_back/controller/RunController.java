package com.RunAlliance_back.RunAlliance_back.controller;

import com.RunAlliance_back.RunAlliance_back.dto.RunDTO;
import com.RunAlliance_back.RunAlliance_back.service.RunService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunService runService;

    public RunController(RunService runService) {
        this.runService = runService;
    }

    @GetMapping
    public List<RunDTO> getAllRuns() {
        return runService.getAllRuns();
    }

    @GetMapping("/{runId}")
    public RunDTO getRunById(@PathVariable Long runId) {
        return runService.getRunById(runId);
    }

    @PostMapping
    public RunDTO createRun(@RequestBody RunDTO runDTO) {
        return runService.createRun(runDTO);
    }

    @PutMapping("/{runId}")
    public RunDTO updateRun(@PathVariable Long runId, @RequestBody RunDTO runDTO) {
        return runService.updateRun(runId, runDTO);
    }

    @DeleteMapping("/{runId}")
    public void deleteRun(@PathVariable Long runId) {
        runService.deleteRun(runId);
    }
}
