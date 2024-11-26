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

import io.github.citronix.dao.SearchRequest;
import io.github.citronix.dto.req.FarmReqDto;
import io.github.citronix.dto.resp.DeleteDto;
import io.github.citronix.dto.resp.FarmRespDto;
import io.github.citronix.service.IFarmService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/farms")
public class FarmController {

	private final IFarmService farmService;

	@GetMapping
	public ResponseEntity<List<FarmRespDto>> index(@RequestParam(name = "page", defaultValue = "0") Integer page) {
		return ResponseEntity.status(HttpStatus.OK).body(farmService.getAllFarms(page));
	}

	@GetMapping("/{id}")
	public ResponseEntity<FarmRespDto> show(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(farmService.getFarmDetails(id));
	}

	@PostMapping
	public ResponseEntity<?> store(@RequestBody @Valid FarmReqDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(farmService.createFarm(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody @Valid FarmReqDto dto, @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK.value()).body(farmService.updateFarm(dto, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteDto> delete(@PathVariable Long id) {
		farmService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(new DeleteDto("Farm deleted successfully"));
	}

	@GetMapping("/search")
	public ResponseEntity<List<FarmRespDto>> search(@RequestBody SearchRequest request) {
		List<FarmRespDto> dtos = farmService.searchForFarms(request);
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
}
