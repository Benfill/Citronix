package io.github.citronix.dto.resp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.github.citronix.entity.Field;
import io.github.citronix.entity.HarvestDetail;
import io.github.citronix.entity.Sale;
import lombok.Data;

@Data
public class HarvestRespDto {
	private Long id;

	private Integer year;

	private LocalDate harvestDate;

	private Field field;

	private List<HarvestDetail> harvestDetails = new ArrayList<>();

	private List<Sale> sales = new ArrayList<>();

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
