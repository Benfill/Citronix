package io.github.citronix.dto.resp;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.github.citronix.entity.Harvest;
import lombok.Data;

@Data
public class SaleRespDto {
	private Long id;

	@NotNull(message = "Sale date is required")
	private LocalDate saleDate;

	@NotNull(message = "Quantity is required")
	private Double quantity;

	@NotNull(message = "Unit price is required")
	private Double unitPrice;

	@NotBlank(message = "Client Name is required")
	@Size(min = 2, max = 50)
	private String clientName;

	private Harvest harvest;

	private Double totalRevenue;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
