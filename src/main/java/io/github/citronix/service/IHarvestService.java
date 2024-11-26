package io.github.citronix.service;

import java.util.List;
import java.util.Optional;

import io.github.citronix.dto.req.HarvestReqDto;
import io.github.citronix.dto.resp.HarvestRespDto;
import io.github.citronix.entity.Harvest;

public interface IHarvestService {
	Optional<Harvest> getHarvestById(Long id);

	HarvestRespDto getHarvestDetails(Long id);

	List<HarvestRespDto> getAllHarvests(Integer page);

	HarvestRespDto createHarvest(HarvestReqDto dto);

	HarvestRespDto updateHarvest(HarvestReqDto dto, Long id);

	void delete(Long id);
}
