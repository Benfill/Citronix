package io.github.citronix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.citronix.dto.req.FarmReqDto;
import io.github.citronix.dto.resp.FarmRespDto;
import io.github.citronix.entity.Farm;

@Mapper(componentModel = "spring")
public interface FarmMapper {

	@Mapping(target = "id", source = "entity.id")
	@Mapping(target = "createdAt", source = "entity.createdAt")
	@Mapping(target = "updatedAt", source = "entity.updatedAt")
	FarmRespDto farmToFarmDto(Farm entity);

	Farm farmDtoTofarm(FarmReqDto dto);
}
