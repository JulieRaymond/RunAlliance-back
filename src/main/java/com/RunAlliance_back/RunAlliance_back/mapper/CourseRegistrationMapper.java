package com.RunAlliance_back.RunAlliance_back.mapper;

import com.RunAlliance_back.RunAlliance_back.dto.CourseRegistrationDTO;
import com.RunAlliance_back.RunAlliance_back.model.CourseRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseRegistrationMapper {

    CourseRegistrationMapper INSTANCE = Mappers.getMapper(CourseRegistrationMapper.class);

    @Mapping(target = "registrationId", source = "courseRegistration.registrationId")
    @Mapping(source = "run.runId", target = "runId")
    @Mapping(source = "user.id", target = "userId")
        // Assure-toi que 'userId' correspond à 'id' dans User
    CourseRegistrationDTO courseRegistrationToCourseRegistrationDTO(CourseRegistration courseRegistration);

    @Mapping(target = "registrationId", source = "courseRegistrationDTO.registrationId")
    @Mapping(source = "runId", target = "run.runId")
    @Mapping(source = "userId", target = "user.id")
        // Assure-toi que 'userId' correspond à 'id' dans User
    CourseRegistration courseRegistrationDTOToCourseRegistration(CourseRegistrationDTO courseRegistrationDTO);

    void updateCourseRegistrationFromDTO(CourseRegistrationDTO courseRegistrationDTO, @MappingTarget CourseRegistration courseRegistration);

}
