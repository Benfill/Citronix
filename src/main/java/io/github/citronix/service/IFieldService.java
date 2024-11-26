package io.github.citronix.service;

import java.util.List;
import java.util.Optional;

import io.github.citronix.dto.req.FieldReqDto;
import io.github.citronix.dto.resp.FieldRespDto;
import io.github.citronix.entity.Field;

public interface IFieldService {

	Optional<Field> getFieldById(Long id);

	FieldRespDto getFieldDetails(Long id);

	List<FieldRespDto> getAllFields(Integer page);

	FieldRespDto createField(FieldReqDto dto);

	FieldRespDto updateField(FieldReqDto dto, Long id);

	void delete(Long id);
}
