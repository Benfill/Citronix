package io.github.citronix.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.citronix.dto.req.SaleReqDto;
import io.github.citronix.dto.resp.SaleRespDto;
import io.github.citronix.entity.Harvest;
import io.github.citronix.entity.Sale;
import io.github.citronix.exception.CustomNotFoundException;
import io.github.citronix.exception.Validation;
import io.github.citronix.mapper.SaleMapper;
import io.github.citronix.repository.ISaleRepository;
import io.github.citronix.service.IHarvestService;
import io.github.citronix.service.ISaleService;
import io.github.citronix.validation.ValidationMessage;
import io.github.citronix.validation.Validator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements ISaleService {

	private final ISaleRepository repository;
	private final SaleMapper mapper;
	private final IHarvestService harvestService;
	private final Validator validator;

	@Override
	public Optional<Sale> getSaleById(Long id) {
		return repository.findById(id);
	}

	@Override
	public SaleRespDto getSaleDetails(Long id) {
		Sale sale = getSaleById(id)
				.orElseThrow(() -> new CustomNotFoundException("Sale with id: " + id + " not found"));
		return mapper.entityToDto(sale);
	}

	@Override
	public List<SaleRespDto> getAllSales(Integer page) {
		int size = 3;
		Pageable pageable = PageRequest.of(page, size);
		List<Sale> sales = repository.findAll(pageable).getContent();
		return sales.stream().map(s -> mapper.entityToDto(s)).collect(Collectors.toList());
	}

	@Override
	public SaleRespDto createSale(SaleReqDto dto) {
		Harvest harvest = harvestService.getHarvestById(dto.getHarvestId()).orElseThrow(
				() -> new CustomNotFoundException("Harvest with id: " + dto.getHarvestId() + " not found"));
		ValidationMessage validationObj = validator.validateDate(dto.getSaleDate());
		if (!validationObj.getChecker()) {
			throw new Validation(validationObj.getMessage());
		}
		Sale sale = mapper.DtoToentity(dto);
		sale.setHarvest(harvest);

		Sale savedSale = repository.save(sale);
		return mapper.entityToDto(savedSale);
	}

	@Override
	public SaleRespDto updateSale(SaleReqDto dto, Long id) {
		Sale sale = getSaleById(id)
				.orElseThrow(() -> new CustomNotFoundException("Sale with id: " + id + " not found"));
		Harvest harvest = harvestService.getHarvestById(dto.getHarvestId()).orElseThrow(
				() -> new CustomNotFoundException("Harvest with id: " + dto.getHarvestId() + " not found"));

		ValidationMessage validationObj = validator.validateDate(dto.getSaleDate());
		if (!validationObj.getChecker()) {
			throw new Validation(validationObj.getMessage());
		}
		sale.setClientName(dto.getClientName());
		sale.setHarvest(harvest);
		sale.setQuantity(dto.getQuantity());
		sale.setUnitPrice(dto.getUnitPrice());
		sale.setSaleDate(dto.getSaleDate());

		Sale updatedSale = repository.save(sale);
		return mapper.entityToDto(updatedSale);
	}

	@Override
	public void delete(Long id) {
		Sale sale = getSaleById(id)
				.orElseThrow(() -> new CustomNotFoundException("Sale with id: " + id + " not found"));
		repository.delete(sale);
	}

}
