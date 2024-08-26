package com.RunAlliance_back.RunAlliance_back.mapper;

import com.RunAlliance_back.RunAlliance_back.dto.RunDTO;
import com.RunAlliance_back.RunAlliance_back.model.Run;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RunMapper {

    RunMapper INSTANCE = Mappers.getMapper(RunMapper.class);

    @Mapping(target = "runId", source = "run.runId")
    @Mapping(target = "title", source = "run.title")
    @Mapping(target = "description", source = "run.description")
    @Mapping(target = "date", source = "run.date")
    @Mapping(target = "time", source = "run.time")
    @Mapping(target = "location", source = "run.location")
    @Mapping(target = "difficultyLevel", source = "run.difficultyLevel")
    @Mapping(target = "distanceKm", source = "run.distanceKm")
    @Mapping(target = "durationMinutes", source = "run.durationMinutes")
    RunDTO runToRunDTO(Run run);

    @Mapping(target = "runId", source = "runDTO.runId")
    @Mapping(target = "title", source = "runDTO.title")
    @Mapping(target = "description", source = "runDTO.description")
    @Mapping(target = "date", source = "runDTO.date")
    @Mapping(target = "time", source = "runDTO.time")
    @Mapping(target = "location", source = "runDTO.location")
    @Mapping(target = "difficultyLevel", source = "runDTO.difficultyLevel")
    @Mapping(target = "distanceKm", source = "runDTO.distanceKm")
    @Mapping(target = "durationMinutes", source = "runDTO.durationMinutes")
    Run runDTOToRun(RunDTO runDTO);

    void updateRunFromDTO(RunDTO runDTO, @MappingTarget Run run);
}
