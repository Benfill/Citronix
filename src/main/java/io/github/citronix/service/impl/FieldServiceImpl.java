package io.github.citronix.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.citronix.dto.FieldDto;
import io.github.citronix.entity.Farm;
import io.github.citronix.entity.Field;
import io.github.citronix.exception.CustomNotFoundException;
import io.github.citronix.exception.Validation;
import io.github.citronix.mapper.FieldMapper;
import io.github.citronix.repository.IFieldRepository;
import io.github.citronix.service.IFarmService;
import io.github.citronix.service.IFieldService;
import io.github.citronix.validation.ValidationMessage;
import io.github.citronix.validation.Validator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FieldServiceImpl implements IFieldService {

	private final IFieldRepository repository;
	private final IFarmService farmService;
	private final FieldMapper mapper;
	private final Validator validator;

	@Override
	public Optional<Field> getFieldById(Long id) {
		return repository.findById(id);
	}

	@Override
	public FieldDto getFieldDetails(Long id) {
		Field field = getFieldById(id)
				.orElseThrow(() -> new CustomNotFoundException("Field with id: " + id + " not found"));

		return mapper.entityToDto(field);
	}

	@Override
	public List<FieldDto> getAllFields(Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page, size);
		List<Field> fields = repository.findAll(pageable).getContent();
		return fields.stream().map(f -> mapper.entityToDto(f)).collect(Collectors.toList());
	}

	@Override
	public FieldDto createField(FieldDto dto) {
		Long id = dto.getFarm_id();
		Farm farm = farmService.getFarmById(id)
				.orElseThrow(() -> new CustomNotFoundException("Farm with id: " + id + " not found"));
		Double fieldsTotalArea = repository.sumFieldAreasByFarmId(id);
		fieldsTotalArea = fieldsTotalArea != null ? fieldsTotalArea + dto.getArea() : dto.getArea();

		ValidationMessage validationObj = validator.checkTotalFarmArea(fieldsTotalArea, farm.getTotalArea(),
				dto.getArea());
		if (!validationObj.getChecker()) {
			throw new Validation(validationObj.getMessage());
		}

		long totalFields = repository.countByFarmId(id);

		if (totalFields > 9) {
			throw new Validation("A farm can have a maximum of 10 fields.");
		}

		Field field = mapper.DtoToentity(dto);
		field.setFarm(farm);
		Field savedField = repository.save(field);
		return mapper.entityToDto(savedField);
	}

	@Override
	public FieldDto updateField(FieldDto dto, Long id) {
		Field field = getFieldById(id)
				.orElseThrow(() -> new CustomNotFoundException("Farm with id: " + id + " not found"));
		Long farmId = dto.getFarm_id();
		Farm farm = farmService.getFarmById(farmId)
				.orElseThrow(() -> new CustomNotFoundException("Farm with id: " + farmId + " not found"));
		field.setName(dto.getName());
		field.setArea(dto.getArea());
		field.setFarm(farm);
		Double fieldsTotalArea = repository.sumFieldAreasByFarmId(farmId);
		fieldsTotalArea = fieldsTotalArea != null ? fieldsTotalArea + dto.getArea() : dto.getArea();

		ValidationMessage validationObj = validator.checkTotalFarmArea(fieldsTotalArea, farm.getTotalArea(),
				dto.getArea());
		if (!validationObj.getChecker()) {
			throw new Validation(validationObj.getMessage());
		}

		long totalFields = repository.countByFarmId(farmId);

		if (totalFields > 9) {
			throw new Validation("A farm can have a maximum of 10 fields.");
		}

		Field updatedField = repository.save(field);
		return mapper.entityToDto(updatedField);
	}

	@Override
	public void delete(Long id) {
		Field field = getFieldById(id)
				.orElseThrow(() -> new CustomNotFoundException("Field with id: " + id + " not found"));
		repository.delete(field);

	}

}
