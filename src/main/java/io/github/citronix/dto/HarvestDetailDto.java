package io.github.citronix.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HarvestDetailDto {
	private Long id;

	@NotNull(message = "Tree id is required")
	private Long treeId;
	@NotNull(message = "Harvest id is required")
	private Long harvestId;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
