package io.github.citronix.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.github.citronix.entity.Field;
import io.github.citronix.entity.HarvestDetail;
import io.github.citronix.entity.Sale;
import lombok.Data;

@Data
public class HarvestDto {
	private Long id;

	@NotNull(message = "Harvest year is required")
	private Integer year;

	@NotNull(message = "Harvest date is required")
	private LocalDate harvestDate;

	@NotNull(message = "Field id is required")
	private Long fieldId;

	private Field field;

	private List<HarvestDetail> harvestDetails = new ArrayList<>();

	private List<Sale> sales = new ArrayList<>();

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
