package io.github.citronix.service;

import java.util.List;
import java.util.Optional;

import io.github.citronix.dao.SearchRequest;
import io.github.citronix.dto.req.FarmReqDto;
import io.github.citronix.dto.resp.FarmRespDto;
import io.github.citronix.entity.Farm;

public interface IFarmService {

	Optional<Farm> getFarmById(Long id);

	FarmRespDto getFarmDetails(Long id);

	List<FarmRespDto> getAllFarms(Integer page);

	FarmRespDto createFarm(FarmReqDto dto);

	FarmRespDto updateFarm(FarmReqDto dto, Long id);

	void delete(Long id);

	List<FarmRespDto> searchForFarms(SearchRequest request);

}
