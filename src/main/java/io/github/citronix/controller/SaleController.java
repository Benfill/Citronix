package io.github.citronix.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.citronix.dto.req.SaleReqDto;
import io.github.citronix.dto.resp.DeleteDto;
import io.github.citronix.dto.resp.SaleRespDto;
import io.github.citronix.service.ISaleService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/sales")
@AllArgsConstructor
public class SaleController {
	private final ISaleService saleService;

	@GetMapping
	public ResponseEntity<?> index(@RequestParam(name = "page", defaultValue = "0") Integer page) {
		List<SaleRespDto> dtos = saleService.getAllSales(page);
		return ResponseEntity.status(HttpStatus.FOUND.value()).body(dtos);

	}

	@GetMapping("{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		SaleRespDto dto = saleService.getSaleDetails(id);
		return ResponseEntity.status(HttpStatus.FOUND.value()).body(dto);
	}

	@PostMapping
	public ResponseEntity<?> store(@RequestBody @Valid SaleReqDto dto) {
		SaleRespDto savedDto = saleService.createSale(dto);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(savedDto);

	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@RequestBody SaleReqDto dto, @PathVariable Long id) {
		SaleRespDto updatedDto = saleService.updateSale(dto, id);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(updatedDto);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		saleService.delete(id);
		return ResponseEntity.status(HttpStatus.OK.value()).body(new DeleteDto("Sale Deleted Successfully"));
	}
}
