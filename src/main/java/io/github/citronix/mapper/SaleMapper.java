package io.github.citronix.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.citronix.dto.req.SaleReqDto;
import io.github.citronix.dto.resp.SaleRespDto;
import io.github.citronix.entity.Sale;

@Mapper(componentModel = "spring")
public interface SaleMapper {
	@Mapping(target = "id", source = "entity.id")
	@Mapping(target = "createdAt", source = "entity.createdAt")
	@Mapping(target = "updatedAt", source = "entity.updatedAt")
	SaleRespDto entityToDto(Sale entity);

	Sale DtoToentity(SaleReqDto dto);
}
