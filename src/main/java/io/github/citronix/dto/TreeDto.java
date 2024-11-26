package io.github.citronix.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.github.citronix.entity.Field;
import io.github.citronix.entity.HarvestDetail;
import io.github.citronix.entity.enums.TreeProductivityStage;
import lombok.Data;

@Data
public class TreeDto {
	private Long id;

	@NotNull(message = "Planting Date is required")
	private LocalDate plantingDate;

	private Integer age;

	private TreeProductivityStage ProductivityStage;

	@NotNull(message = "Field Id is required")
	private Long field_id;

	private Field field;

	private List<HarvestDetail> harvestDetails = new ArrayList<>();

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
