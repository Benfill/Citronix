package io.github.citronix.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Field extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Min(value = 1000) // Minimum 0.1 hectare (1000 m²)
	private Double area; // in square meters

	@ManyToOne
	@JoinColumn(name = "farm_id", nullable = false)
	private Farm farm;

	@OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
	private List<Tree> trees = new ArrayList<>();

	@OneToMany(mappedBy = "field")
	private List<Harvest> harvests = new ArrayList<>();
}