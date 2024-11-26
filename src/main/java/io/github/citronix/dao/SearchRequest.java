package io.github.citronix.dao;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {
	private String name;
	private String location;
	private Double totalArea;
	private LocalDate establishmentDate;
}
