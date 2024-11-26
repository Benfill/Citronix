package io.github.citronix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.citronix.dto.req.FieldReqDto;
import io.github.citronix.dto.resp.FieldRespDto;
import io.github.citronix.entity.Field;

@Mapper(componentModel = "spring")
public interface FieldMapper {

	@Mapping(target = "id", source = "entity.id")
	@Mapping(target = "createdAt", source = "entity.createdAt")
	@Mapping(target = "updatedAt", source = "entity.updatedAt")
	FieldRespDto entityToDto(Field entity);

	Field DtoToentity(FieldReqDto dto);
}
