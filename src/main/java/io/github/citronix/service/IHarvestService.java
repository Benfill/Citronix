package io.github.citronix.service;

import java.util.List;
import java.util.Optional;

import io.github.citronix.dto.HarvestDto;
import io.github.citronix.entity.Harvest;

public interface IHarvestService {
	Optional<Harvest> getHarvestById(Long id);

	HarvestDto getHarvestDetails(Long id);

	List<HarvestDto> getAllHarvests(Integer page);

	HarvestDto createHarvest(HarvestDto dto);

	HarvestDto updateHarvest(HarvestDto dto, Long id);

	void delete(Long id);
}
