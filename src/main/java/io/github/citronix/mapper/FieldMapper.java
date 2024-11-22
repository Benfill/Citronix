package io.github.citronix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.citronix.dto.FieldDto;
import io.github.citronix.entity.Field;

@Mapper(componentModel = "spring")
public interface FieldMapper {

	@Mapping(target = "id", source = "entity.id")
	@Mapping(target = "createdAt", source = "entity.createdAt")
	@Mapping(target = "updatedAt", source = "entity.updatedAt")
	FieldDto entityToDto(Field entity);

	Field DtoToentity(FieldDto dto);
}
