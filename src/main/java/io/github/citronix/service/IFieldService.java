package io.github.citronix.service;

import java.util.List;
import java.util.Optional;

import io.github.citronix.dto.FieldDto;
import io.github.citronix.entity.Field;

public interface IFieldService {

	Optional<Field> getFieldById(Long id);

	FieldDto getFieldDetails(Long id);

	List<FieldDto> getAllFields(Integer page);

	FieldDto createField(FieldDto dto);

	FieldDto updateField(FieldDto dto, Long id);

	void delete(Long id);
}
