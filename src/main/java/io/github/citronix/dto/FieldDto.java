package io.github.citronix.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.github.citronix.entity.Farm;
import io.github.citronix.entity.Harvest;
import io.github.citronix.entity.Tree;
import lombok.Data;

@Data
public class FieldDto {

	private Long id;

	@NotBlank(message = "Fild Name is required")
	@Size(min = 2, max = 50)
	private String name;

	@NotNull(message = "Fild Area is required")
	@Min(value = 1000, message = "A field must be at least 0.1 hectares (1,000 m²)")
	private Double area;

	@NotNull(message = "Fild Id is required")
	private Long farm_id;

	private Farm farm;
	private List<Tree> trees;
	private List<Harvest> harvests;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
