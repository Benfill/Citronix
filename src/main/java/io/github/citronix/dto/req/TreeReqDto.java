package io.github.citronix.dto.req;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TreeReqDto {
	@NotNull(message = "Planting Date is required")
	private LocalDate plantingDate;

	@NotNull(message = "Field Id is required")
	private Long field_id;
}
