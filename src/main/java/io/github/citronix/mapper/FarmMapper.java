package io.github.citronix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.citronix.dto.FarmDto;
import io.github.citronix.entity.Farm;

@Mapper(componentModel = "spring")
public interface FarmMapper {

	@Mapping(target = "id", source = "entity.id")
	@Mapping(target = "createdAt", source = "entity.createdAt")
	@Mapping(target = "updatedAt", source = "entity.updatedAt")
	FarmDto farmToFarmDto(Farm entity);

	Farm farmDtoTofarm(FarmDto dto);
}
