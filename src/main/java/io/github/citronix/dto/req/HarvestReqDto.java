package io.github.citronix.dto.req;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import io.github.citronix.validation.CorrectLocalDate;
import lombok.Data;

@Data
public class HarvestReqDto {

	@NotNull(message = "Harvest year is required")
	private Integer year;

	@NotNull(message = "Harvest date is required")
	@CorrectLocalDate
	private LocalDate harvestDate;

	@NotNull(message = "Field id is required")
	private Long fieldId;
}
