package io.github.citronix.dto.resp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.github.citronix.entity.Field;
import io.github.citronix.entity.HarvestDetail;
import io.github.citronix.entity.enums.TreeProductivityStage;
import lombok.Data;

@Data
public class TreeRespDto {
	private Long id;
	private LocalDate plantingDate;

	private Integer age;

	private TreeProductivityStage ProductivityStage;

	private Field field;

	private List<HarvestDetail> harvestDetails = new ArrayList<>();

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
