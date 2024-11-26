package io.github.citronix.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.citronix.dto.resp.HarvestDetailDto;
import io.github.citronix.entity.HarvestDetail;
import io.github.citronix.exception.CustomNotFoundException;
import io.github.citronix.mapper.HarvestDetailMapper;
import io.github.citronix.repository.IHarvestDetailRepository;
import io.github.citronix.service.IHarvestDetailService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HarvestDetailService implements IHarvestDetailService {

	private final IHarvestDetailRepository repository;
	private final HarvestDetailMapper mapper;

	@Override
	public Optional<HarvestDetail> getHarvestDetailById(Long id) {
		return repository.findById(id);
	}

	@Override
	public HarvestDetailDto getHarvestDetailDetails(Long id) {
		HarvestDetail harvest = getHarvestDetailById(id)
				.orElseThrow(() -> new CustomNotFoundException("Harvest Detail with id: " + id + " not found"));
		return mapper.entityToDto(harvest);
	}

	@Override
	public List<HarvestDetailDto> getAllHarvestDetails(Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page, size);
		List<HarvestDetail> harvests = repository.findAll(pageable).getContent();
		return harvests.stream().map(h -> mapper.entityToDto(h)).collect(Collectors.toList());
	}

	@Override
	public HarvestDetail createHarvestDetail(HarvestDetail detail) {
		return repository.save(detail);
	}

}
