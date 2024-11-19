package io.github.citronix.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.citronix.dto.FarmDto;
import io.github.citronix.entity.Farm;
import io.github.citronix.exception.CustomNotFoundException;
import io.github.citronix.mapper.FarmMapper;
import io.github.citronix.repository.IFarmRepository;
import io.github.citronix.service.IFarmService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FarmService implements IFarmService {

	private final IFarmRepository repository;

	private final FarmMapper mapper;

	@Override
	public Optional<Farm> getFarmById(Long id) {
		return repository.findById(id);
	}

	@Override
	public FarmDto getFarmDetails(Long id) {
		Farm farm = getFarmById(id)
				.orElseThrow(() -> new CustomNotFoundException("Farm with id: " + id + " not found"));

		return mapper.farmToFarmDto(farm);
	}

	@Override
	public List<FarmDto> getAllFarms(Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page, size);
		List<Farm> farms = repository.findAll(pageable).getContent();
		return farms.stream().map(farm -> mapper.farmToFarmDto(farm)).collect(Collectors.toList());
	}

	@Override
	public FarmDto createFarm(FarmDto dto) {
		Farm farm = mapper.farmDtoTofarm(dto);
		return mapper.farmToFarmDto(repository.save(farm));
	}

	@Override
	public FarmDto updateFarm(FarmDto dto, Long id) {
		Farm existingFarm = getFarmById(id)
				.orElseThrow(() -> new CustomNotFoundException("Farm with id: " + id + " not found"));

		existingFarm.setName(dto.getName());
		existingFarm.setLocation(dto.getLocation());
		existingFarm.setEstablishmentDate(dto.getEstablishmentDate());
		existingFarm.setTotalArea(dto.getTotalArea());

		return mapper.farmToFarmDto(repository.save(existingFarm));
	}

	@Override
	public void delete(Long id) {
		Farm existingFarm = getFarmById(id)
				.orElseThrow(() -> new CustomNotFoundException("Farm with id: " + id + " not found"));

		repository.delete(existingFarm);
	}

}
