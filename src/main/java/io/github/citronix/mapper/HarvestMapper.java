package io.github.citronix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.citronix.dto.HarvestDto;
import io.github.citronix.entity.Harvest;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
	@Mapping(target = "id", source = "entity.id")
	@Mapping(target = "createdAt", source = "entity.createdAt")
	@Mapping(target = "updatedAt", source = "entity.updatedAt")
	HarvestDto entityToDto(Harvest entity);

	Harvest DtoToentity(HarvestDto dto);
}
