package io.github.citronix.service;

import java.util.List;
import java.util.Optional;

import io.github.citronix.dto.HarvestDetailDto;
import io.github.citronix.entity.HarvestDetail;

public interface IHarvestDetailService {
	Optional<HarvestDetail> getHarvestDetailById(Long id);

	HarvestDetailDto getHarvestDetailDetails(Long id);

	List<HarvestDetailDto> getAllHarvestDetails(Integer page);

	HarvestDetailDto createHarvestDetail(HarvestDetailDto dto);

	HarvestDetailDto updateHarvestDetail(HarvestDetailDto dto, Long id);

	void delete(Long id);
}
