package io.github.citronix.dto.resp;

import java.time.LocalDateTime;

import io.github.citronix.entity.Harvest;
import io.github.citronix.entity.Tree;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HarvestDetailDto {
	private Long id;

	private Tree tree;

	private Harvest harvest;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
