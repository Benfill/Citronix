package io.github.citronix.dto.req;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class FieldReqDto {

	@NotBlank(message = "Fild Name is required")
	@Size(min = 2, max = 50)
	private String name;

	@NotNull(message = "Fild Area is required")
	@Min(value = 1000, message = "A field must be at least 0.1 hectares (1,000 m²)")
	private Double area;

	@NotNull(message = "Fild Id is required")
	private Long farm_id;
}
