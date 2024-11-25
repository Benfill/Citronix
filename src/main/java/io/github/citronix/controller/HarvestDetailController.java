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

import io.github.citronix.dto.HarvestDetailDto;
import io.github.citronix.dto.resp.DeleteDto;
import io.github.citronix.service.IHarvestDetailService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/HarvestDetails")
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

	@PostMapping
	public ResponseEntity<?> store(@RequestBody @Valid HarvestDetailDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(HarvestDetailService.createHarvestDetail(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid HarvestDetailDto dto, @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK.value()).body(HarvestDetailService.updateHarvestDetail(dto, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteDto> delete(@PathVariable Long id) {
		HarvestDetailService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(new DeleteDto("Harvest Detail deleted successfully"));
	}
}
