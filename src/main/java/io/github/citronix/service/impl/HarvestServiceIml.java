package io.github.citronix.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.citronix.dto.req.HarvestReqDto;
import io.github.citronix.dto.resp.HarvestRespDto;
import io.github.citronix.entity.Field;
import io.github.citronix.entity.Harvest;
import io.github.citronix.entity.HarvestDetail;
import io.github.citronix.entity.Tree;
import io.github.citronix.entity.enums.Season;
import io.github.citronix.exception.CustomNotFoundException;
import io.github.citronix.exception.Validation;
import io.github.citronix.mapper.HarvestMapper;
import io.github.citronix.repository.IHarvestRepository;
import io.github.citronix.service.IFieldService;
import io.github.citronix.service.IHarvestDetailService;
import io.github.citronix.service.IHarvestService;
import io.github.citronix.utils.HarvestUtil;
import io.github.citronix.validation.ValidationMessage;
import io.github.citronix.validation.Validator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HarvestServiceIml implements IHarvestService {

	private final IHarvestRepository repository;
	private final IFieldService fieldService;
	private final IHarvestDetailService harvestDetailService;
	private final HarvestMapper mapper;
	private final Validator validator;
	private final HarvestUtil harvestUtil;

	@Override
	public Optional<Harvest> getHarvestById(Long id) {
		return repository.findById(id);
	}

	@Override
	public HarvestRespDto getHarvestDetails(Long id) {
		Harvest harvest = getHarvestById(id)
				.orElseThrow(() -> new CustomNotFoundException("Harvest with id: " + id + " not found"));
		return mapper.entityToDto(harvest);
	}

	@Override
	public List<HarvestRespDto> getAllHarvests(Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page, size);
		List<Harvest> harvests = repository.findAll(pageable).getContent();

		return harvests.stream().map(h -> mapper.entityToDto(h)).collect(Collectors.toList());
	}

	@Override
	public HarvestRespDto createHarvest(HarvestReqDto dto) {
		Harvest harvest = mapper.DtoToentity(dto);
		long fieldId = dto.getFieldId();
		Field field = fieldService.getFieldById(fieldId)
				.orElseThrow(() -> new CustomNotFoundException("Field with id: " + fieldId + " not found"));

		Season season = harvestUtil.getSeasonByMonth(dto.getHarvestDate());

		List<Harvest> harvestsOfTheYear = repository.findByYearAndFieldId(dto.getYear(), dto.getFieldId());
		ValidationMessage validationObj = validator.checkHarvestSeason(harvestsOfTheYear, season);
		if (!validationObj.getChecker()) {
			throw new Validation(validationObj.getMessage());
		}

		validationObj = validator.checkHarvestYear(dto.getHarvestDate(), dto.getYear());
		if (!validationObj.getChecker()) {
			throw new Validation(validationObj.getMessage());
		}

		if (field.getTrees().isEmpty()) {
			throw new Validation("Harvest impossible: no trees in the field.");
		}

		Double quantity = harvestUtil.getTotalQuantity(field);
		harvest.setTotalQuantity(quantity);
		harvest.setField(field);
		harvest.setSeason(season);
		Harvest savedHarvest = repository.save(harvest);
		addMultipleTreeToHarvestDetail(field.getTrees(), savedHarvest);
		return mapper.entityToDto(savedHarvest);
	}

	public void addMultipleTreeToHarvestDetail(List<Tree> trees, Harvest harvest) {
		trees.forEach(t -> {
			HarvestDetail detail = new HarvestDetail(t.getSeasonalProductivity(), harvest, t);
			harvestDetailService.createHarvestDetail(detail);
		});
	}

	@Override
	public HarvestRespDto updateHarvest(HarvestReqDto dto, Long id) {
		Harvest harvest = getHarvestById(id)
				.orElseThrow(() -> new CustomNotFoundException("Harvest with id: " + id + " not found"));
		long fieldId = dto.getFieldId();
		Field field = fieldService.getFieldById(fieldId)
				.orElseThrow(() -> new CustomNotFoundException("Field with id: " + fieldId + " not found"));

		Season season = harvestUtil.getSeasonByMonth(dto.getHarvestDate());

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

		Double quantity = harvestUtil.getTotalQuantity(field);
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
