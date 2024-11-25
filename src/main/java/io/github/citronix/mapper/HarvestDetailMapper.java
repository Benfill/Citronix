package io.github.citronix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.citronix.dto.HarvestDetailDto;
import io.github.citronix.entity.HarvestDetail;

@Mapper(componentModel = "spring")
public interface HarvestDetailMapper {
	@Mapping(target = "id", source = "entity.id")
	@Mapping(target = "createdAt", source = "entity.createdAt")
	@Mapping(target = "updatedAt", source = "entity.updatedAt")
	HarvestDetailDto entityToDto(HarvestDetail entity);

	HarvestDetail DtoToentity(HarvestDetailDto dto);
}
