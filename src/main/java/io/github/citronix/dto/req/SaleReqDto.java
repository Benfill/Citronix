package io.github.citronix.dto.req;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.github.citronix.validation.CorrectLocalDate;
import lombok.Data;

@Data
public class SaleReqDto {
	@NotNull(message = "Sale date is required")
	@CorrectLocalDate
	private LocalDate saleDate;

	@NotNull(message = "Quantity is required")
	private Double quantity;

	@NotNull(message = "Unit price is required")
	private Double unitPrice;

	@NotBlank(message = "Client Name is required")
	@Size(min = 2, max = 50)
	private String clientName;

	@NotNull(message = "Harvest id is required")
	private Long harvestId;

}
