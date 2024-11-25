package io.github.citronix.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.citronix.dto.HarvestDto;
import io.github.citronix.entity.Field;
import io.github.citronix.entity.Harvest;
import io.github.citronix.entity.enums.Season;
import io.github.citronix.exception.CustomNotFoundException;
import io.github.citronix.exception.Validation;
import io.github.citronix.mapper.HarvestMapper;
import io.github.citronix.repository.IHarvestRepository;
import io.github.citronix.service.IFieldService;
import io.github.citronix.service.IHarvestService;
import io.github.citronix.validation.ValidationMessage;
import io.github.citronix.validation.Validator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HarvestServiceIml implements IHarvestService {

	private final IHarvestRepository repository;
	private final IFieldService fieldService;
	private final HarvestMapper mapper;
	private final Validator validator;

	@Override
	public Optional<Harvest> getHarvestById(Long id) {
		return repository.findById(id);
	}

	@Override
	public HarvestDto getHarvestDetails(Long id) {
		Harvest harvest = getHarvestById(id)
				.orElseThrow(() -> new CustomNotFoundException("Harvest with id: " + id + " not found"));
		return mapper.entityToDto(harvest);
	}

	@Override
	public List<HarvestDto> getAllHarvests(Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page, size);
		List<Harvest> harvests = repository.findAll(pageable).getContent();

		return harvests.stream().map(h -> mapper.entityToDto(h)).collect(Collectors.toList());
	}

	@Override
	public HarvestDto createHarvest(HarvestDto dto) {
		Harvest harvest = mapper.DtoToentity(dto);
		long fieldId = dto.getFieldId();
		Field field = fieldService.getFieldById(fieldId)
				.orElseThrow(() -> new CustomNotFoundException("Field with id: " + fieldId + " not found"));

		Season season = getSeasonByMonth(dto.getHarvestDate());

		if (!dto.getHarvestDate().equals(harvest.getHarvestDate())) {
			List<Harvest> harvestsOfTheYear = repository.findByYearAndFieldId(dto.getYear(), dto.getFieldId());
			ValidationMessage validationObj = validator.checkHarvestSeason(harvestsOfTheYear, season);
			if (!validationObj.getChecker()) {
				throw new Validation(validationObj.getMessage());
			}

			validationObj = validator.checkHarvestYear(dto.getHarvestDate(), dto.getYear());
			if (!validationObj.getChecker()) {
				throw new Validation(validationObj.getMessage());
			}
		}

		Double quantity = getTotalQuantity(field);
		harvest.setTotalQuantity(quantity);
		harvest.setField(field);
		harvest.setSeason(season);
		Harvest savedHarvest = repository.save(harvest);
		return mapper.entityToDto(savedHarvest);
	}

	private Season getSeasonByMonth(LocalDate harvestDate) {
		int month = harvestDate.getMonthValue();

		if ((month >= 6 && month < 9)) {
			return Season.SUMMER;
		} else if (month >= 9 && month < 12) {
			return Season.AUTUMN;
		} else if (month == 12 || month < 3) {
			return Season.WINTER;
		} else {
			return Season.SPRING;
		}
	}

	private Double getTotalQuantity(Field field) {
		return field.getTrees().stream().mapToDouble(t -> t.getSeasonalProductivity()).sum();
	}

	@Override
	public HarvestDto updateHarvest(HarvestDto dto, Long id) {
		Harvest harvest = getHarvestById(id)
				.orElseThrow(() -> new CustomNotFoundException("Harvest with id: " + id + " not found"));
		long fieldId = dto.getFieldId();
		Field field = fieldService.getFieldById(fieldId)
				.orElseThrow(() -> new CustomNotFoundException("Field with id: " + fieldId + " not found"));

		Season season = getSeasonByMonth(dto.getHarvestDate());
		List<Harvest> harvestsOfTheYear = repository.findByYearAndFieldId(dto.getYear(), dto.getFieldId());
		ValidationMessage validationObj = validator.checkHarvestSeason(harvestsOfTheYear, season);
		if (!validationObj.getChecker()) {
			throw new Validation(validationObj.getMessage());
		}

		validationObj = validator.checkHarvestYear(dto.getHarvestDate(), dto.getYear());
		if (!validationObj.getChecker()) {
			throw new Validation(validationObj.getMessage());
		}

		Double quantity = getTotalQuantity(field);
		harvest.setTotalQuantity(quantity);
		harvest.setYear(dto.getYear());
		harvest.setHarvestDate(dto.getHarvestDate());
		harvest.setSeason(season);
		harvest.setField(field);

		Harvest updatedHarvest = repository.save(harvest);
		return mapper.entityToDto(updatedHarvest);
	}

	@Override
	public void delete(Long id) {
		Harvest harvest = getHarvestById(id)
				.orElseThrow(() -> new CustomNotFoundException("Harvest with id: " + id + " not found"));
		repository.delete(harvest);
	}

}
