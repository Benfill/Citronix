package io.github.citronix.service;

import java.util.List;
import java.util.Optional;

import io.github.citronix.dto.FarmDto;
import io.github.citronix.entity.Farm;

public interface IFarmService {

	Optional<Farm> getFarmById(Long id);

	FarmDto getFarmDetails(Long id);

	List<FarmDto> getAllFarms(Integer page);

	FarmDto createFarm(FarmDto dto);

	FarmDto updateFarm(FarmDto dto, Long id);

	void delete(Long id);

}
