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

import io.github.citronix.dto.TreeDto;
import io.github.citronix.dto.resp.DeleteDto;
import io.github.citronix.service.ITreeService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/trees")
@RestController
@AllArgsConstructor
public class TreeController {
	private final ITreeService service;

	@GetMapping
	public ResponseEntity<?> index(@RequestParam(name = "page", defaultValue = "0") Integer page) {
		List<TreeDto> dtos = service.getAllTrees(page);
		return ResponseEntity.status(HttpStatus.FOUND.value()).body(dtos);

	}

	@GetMapping("{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		TreeDto dto = service.getTreeDetails(id);
		return ResponseEntity.status(HttpStatus.FOUND.value()).body(dto);
	}

	@PostMapping
	public ResponseEntity<?> store(@RequestBody @Valid TreeDto dto) {
		TreeDto savedDto = service.createTree(dto);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(savedDto);

	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@RequestBody TreeDto dto, @PathVariable Long id) {
		TreeDto updatedDto = service.updateTree(dto, id);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(updatedDto);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK.value()).body(new DeleteDto("Field Deleted Successfully"));
	}
}
