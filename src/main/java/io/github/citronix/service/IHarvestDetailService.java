package io.github.citronix.service;

import java.util.List;
import java.util.Optional;

import io.github.citronix.dto.resp.HarvestDetailDto;
import io.github.citronix.entity.HarvestDetail;

public interface IHarvestDetailService {
	Optional<HarvestDetail> getHarvestDetailById(Long id);

	HarvestDetailDto getHarvestDetailDetails(Long id);

	List<HarvestDetailDto> getAllHarvestDetails(Integer page);

	HarvestDetail createHarvestDetail(HarvestDetail dto);
}
