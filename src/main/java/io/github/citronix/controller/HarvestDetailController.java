package io.github.citronix.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.citronix.dto.resp.HarvestDetailDto;
import io.github.citronix.service.IHarvestDetailService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/harvestDetails")
@AllArgsConstructor
public class HarvestDetailController {
	private final IHarvestDetailService HarvestDetailService;

	@GetMapping
	public ResponseEntity<List<HarvestDetailDto>> index(@RequestParam(name = "page", defaultValue = "0") Integer page) {
		return ResponseEntity.status(HttpStatus.OK).body(HarvestDetailService.getAllHarvestDetails(page));
	}

	@GetMapping("/{id}")
	public ResponseEntity<HarvestDetailDto> show(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(HarvestDetailService.getHarvestDetailDetails(id));
	}
}
