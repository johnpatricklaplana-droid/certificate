package john.patrick.laplana.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import john.patrick.laplana.dto.SchoolDto;
import john.patrick.laplana.entities.School;

@Mapper(componentModel = "spring")
public interface SchoolMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "verified", ignore = true)
    @Mapping(target = "verifiedAt", ignore = true)
    School toSchool(SchoolDto schoolDto);
    
} 
