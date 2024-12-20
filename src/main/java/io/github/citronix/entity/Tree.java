package io.github.citronix.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.github.citronix.entity.enums.TreeProductivityStage;
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
@Table(name = "trees")
public class Tree extends BaseEntity {
	@Column(nullable = false)
	private LocalDate plantingDate;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "field_id", nullable = false)
	private Field field;

	@JsonManagedReference
	@OneToMany(mappedBy = "tree", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HarvestDetail> harvestDetails = new ArrayList<>();

	@Transient
	public int getAge() {
		return Period.between(plantingDate, LocalDate.now()).getYears();
	}

	@Transient
	public TreeProductivityStage getProductivityStage() {
		int age = getAge();
		if (age > 20) {
			return TreeProductivityStage.UNPRODUCTIVE;
		}
		if (age > 10) {
			return TreeProductivityStage.OLD;
		}
		if (age >= 3) {
			return TreeProductivityStage.MATURE;
		}
		return TreeProductivityStage.YOUNG;
	}

	@Transient
	public double getSeasonalProductivity() {
		switch (getProductivityStage()) {
		case YOUNG:
			return 2.5;
		case MATURE:
			return 12.0;
		case OLD:
			return 20.0;
		case UNPRODUCTIVE:
			return 0.0;
		default:
			throw new IllegalStateException("Unexpected value: " + getProductivityStage());
		}
	}
}