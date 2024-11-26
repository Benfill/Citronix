package io.github.citronix.dto.resp;

import java.time.LocalDateTime;
import java.util.List;

import io.github.citronix.entity.Farm;
import io.github.citronix.entity.Harvest;
import io.github.citronix.entity.Tree;
import lombok.Data;

@Data
public class FieldRespDto {

	private Long id;

	private String name;
	private Double area;

	private Farm farm;
	private List<Tree> trees;
	private List<Harvest> harvests;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
