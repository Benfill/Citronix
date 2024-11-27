package io.github.citronix.dto.req;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.github.citronix.validation.CorrectLocalDate;
import lombok.Data;

@Data
public class FarmReqDto {

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
	@CorrectLocalDate
	private LocalDate establishmentDate;

}
