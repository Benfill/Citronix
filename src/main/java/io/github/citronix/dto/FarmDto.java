package io.github.citronix.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.github.citronix.entity.Field;
import lombok.Data;

@Data
public class FarmDto {

	private Long id;

	@NotBlank(message = "Farm Name is required")
	@Size(min = 2, max = 50)
	private String name;

	@NotBlank(message = "Location is required")
	@Size(min = 2, max = 100)
	private String location;

	@NotNull(message = "Total Area is required")
	@Min(value = 0, message = "A Farm total area must be greater than 0")
	private Double totalArea;

	@NotNull(message = "Establishment Date is required")
	private LocalDate establishmentDate;

	private List<Field> fields;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
