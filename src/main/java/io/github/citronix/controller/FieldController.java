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

import io.github.citronix.dto.DeleteDto;
import io.github.citronix.dto.FieldDto;
import io.github.citronix.service.IFieldService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/fields")
public class FieldController {

	private final IFieldService fieldService;

	@GetMapping
	public ResponseEntity<?> index(@RequestParam(name = "page", defaultValue = "0") Integer page) {
		List<FieldDto> dtos = fieldService.getAllFields(page);
		return ResponseEntity.status(HttpStatus.FOUND.value()).body(dtos);

	}

	@GetMapping("{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		FieldDto dto = fieldService.getFieldDetails(id);
		return ResponseEntity.status(HttpStatus.FOUND.value()).body(dto);
	}

	@PostMapping
	public ResponseEntity<?> store(@RequestBody @Valid FieldDto dto) {
		FieldDto savedDto = fieldService.createField(dto);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(savedDto);

	}

	@PutMapping("{id}")
	public ResponseEntity<?> update(@RequestBody FieldDto dto, @PathVariable Long id) {
		FieldDto updatedDto = fieldService.updateField(dto, id);
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(updatedDto);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		fieldService.delete(id);
		return ResponseEntity.status(HttpStatus.OK.value()).body(new DeleteDto("Field Deleted Successfully"));
	}

}
