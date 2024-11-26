package io.github.citronix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.citronix.dto.req.HarvestReqDto;
import io.github.citronix.dto.resp.HarvestRespDto;
import io.github.citronix.entity.Harvest;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
	@Mapping(target = "id", source = "entity.id")
	@Mapping(target = "createdAt", source = "entity.createdAt")
	@Mapping(target = "updatedAt", source = "entity.updatedAt")
	HarvestRespDto entityToDto(Harvest entity);

	Harvest DtoToentity(HarvestReqDto dto);
}
