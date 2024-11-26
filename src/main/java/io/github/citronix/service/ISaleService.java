package io.github.citronix.service;

import java.util.List;
import java.util.Optional;

import io.github.citronix.dto.req.SaleReqDto;
import io.github.citronix.dto.resp.SaleRespDto;
import io.github.citronix.entity.Sale;

public interface ISaleService {
	Optional<Sale> getSaleById(Long id);

	SaleRespDto getSaleDetails(Long id);

	List<SaleRespDto> getAllSales(Integer page);

	SaleRespDto createSale(SaleReqDto dto);

	SaleRespDto updateSale(SaleReqDto dto, Long id);

	void delete(Long id);
}
