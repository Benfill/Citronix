package io.github.citronix.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.citronix.dto.HarvestDetailDto;
import io.github.citronix.entity.Harvest;
import io.github.citronix.entity.HarvestDetail;
import io.github.citronix.entity.Tree;
import io.github.citronix.exception.CustomNotFoundException;
import io.github.citronix.mapper.HarvestDetailMapper;
import io.github.citronix.repository.IHarvestDetailRepository;
import io.github.citronix.service.IHarvestDetailService;
import io.github.citronix.service.IHarvestService;
import io.github.citronix.service.ITreeService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HarvestDetailService implements IHarvestDetailService {

	private final IHarvestDetailRepository repository;
	private final IHarvestService harvestService;
	private final ITreeService treeService;
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
	public HarvestDetailDto createHarvestDetail(HarvestDetailDto dto) {
		Tree tree = treeService.getTreeById(dto.getTreeId())
				.orElseThrow(() -> new CustomNotFoundException("Tree with id: " + dto.getTreeId() + " not found"));

		Harvest harvest = harvestService.getHarvestById(dto.getHarvestId()).orElseThrow(
				() -> new CustomNotFoundException("Harvest with id: " + dto.getHarvestId() + " not found"));

		HarvestDetail detail = mapper.DtoToentity(dto);
		detail.setHarvest(harvest);
		detail.setTree(tree);
		detail.setQuantity(tree.getSeasonalProductivity());
		HarvestDetail saved = repository.save(detail);
		return mapper.entityToDto(saved);
	}

	@Override
	public HarvestDetailDto updateHarvestDetail(HarvestDetailDto dto, Long id) {
		HarvestDetail detail = getHarvestDetailById(id)
				.orElseThrow(() -> new CustomNotFoundException("Harvest Detail with id: " + id + " not found"));

		Tree tree = treeService.getTreeById(dto.getTreeId())
				.orElseThrow(() -> new CustomNotFoundException("Tree with id: " + dto.getTreeId() + " not found"));

		Harvest harvest = harvestService.getHarvestById(dto.getHarvestId()).orElseThrow(
				() -> new CustomNotFoundException("Harvest with id: " + dto.getHarvestId() + " not found"));

		detail.setHarvest(harvest);
		detail.setTree(tree);
		detail.setQuantity(tree.getSeasonalProductivity());
		HarvestDetail updated = repository.save(detail);
		return mapper.entityToDto(updated);
	}

	@Override
	public void delete(Long id) {
		HarvestDetail detail = getHarvestDetailById(id)
				.orElseThrow(() -> new CustomNotFoundException("Harvest Detail with id: " + id + " not found"));
		repository.delete(detail);

	}

}
