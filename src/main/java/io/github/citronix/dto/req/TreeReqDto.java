package io.github.citronix.dto.req;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import io.github.citronix.validation.CorrectLocalDate;
import lombok.Data;

@Data
public class TreeReqDto {
	@NotNull(message = "Planting Date is required")
	@CorrectLocalDate
	private LocalDate plantingDate;

	@NotNull(message = "Field Id is required")
	private Long field_id;
}
