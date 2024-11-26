package io.github.citronix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.citronix.dto.req.TreeReqDto;
import io.github.citronix.dto.resp.TreeRespDto;
import io.github.citronix.entity.Tree;

@Mapper(componentModel = "spring")
public interface TreeMapper {
	@Mapping(target = "id", source = "entity.id")
	@Mapping(target = "createdAt", source = "entity.createdAt")
	@Mapping(target = "updatedAt", source = "entity.updatedAt")
	TreeRespDto entityToDto(Tree entity);

	Tree DtoToentity(TreeReqDto dto);
}
