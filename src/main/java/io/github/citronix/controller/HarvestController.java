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

import io.github.citronix.dto.req.HarvestReqDto;
import io.github.citronix.dto.resp.DeleteDto;
import io.github.citronix.dto.resp.HarvestRespDto;
import io.github.citronix.service.IHarvestService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/harvests")
public class HarvestController {

	private final IHarvestService HarvestService;

	@GetMapping
	public ResponseEntity<List<HarvestRespDto>> index(@RequestParam(name = "page", defaultValue = "0") Integer page) {
		return ResponseEntity.status(HttpStatus.OK).body(HarvestService.getAllHarvests(page));
	}

	@GetMapping("/{id}")
	public ResponseEntity<HarvestRespDto> show(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(HarvestService.getHarvestDetails(id));
	}

	@PostMapping
	public ResponseEntity<?> store(@RequestBody @Valid HarvestReqDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(HarvestService.createHarvest(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid HarvestReqDto dto, @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK.value()).body(HarvestService.updateHarvest(dto, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteDto> delete(@PathVariable Long id) {
		HarvestService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(new DeleteDto("Harvest deleted successfully"));
	}
}
