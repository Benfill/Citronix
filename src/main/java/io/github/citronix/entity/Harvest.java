package io.github.citronix.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.citronix.entity.enums.Season;
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
@Table(name = "harvests")
public class Harvest extends BaseEntity {
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Season season;

	@Column(name = "`year`", nullable = false)
	private Integer year;

	@Column(nullable = false)
	private LocalDate harvestDate;

	@Column(nullable = false)
	private Double totalQuantity; // in kg

	@JsonIgnoreProperties("field")
	@ManyToOne
	@JoinColumn(name = "field_id", nullable = false)
	private Field field;

	@JsonIgnoreProperties({ "harvest" })
	@OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HarvestDetail> harvestDetails = new ArrayList<>();

	@OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Sale> sales = new ArrayList<>();
}
