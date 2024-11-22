package io.github.citronix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.citronix.dto.TreeDto;
import io.github.citronix.entity.Tree;

@Mapper(componentModel = "spring")
public interface TreeMapper {
	@Mapping(target = "id", source = "entity.id")
	@Mapping(target = "createdAt", source = "entity.createdAt")
	@Mapping(target = "updatedAt", source = "entity.updatedAt")
	TreeDto entityToDto(Tree entity);

	Tree DtoToentity(TreeDto dto);
}
