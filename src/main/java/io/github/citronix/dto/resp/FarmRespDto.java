package io.github.citronix.dto.resp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import io.github.citronix.entity.Field;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FarmRespDto {

	private Long id;

	private String name;

	private String location;

	private Double totalArea;

	private LocalDate establishmentDate;

	private List<Field> fields;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
