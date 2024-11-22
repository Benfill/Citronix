package io.github.citronix.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "sales")
public class Sale extends BaseEntity {
	@Column(nullable = false)
	private LocalDate saleDate;

	@Column(nullable = false)
	private Double quantity; // in kg

	@Column(nullable = false)
	private Double unitPrice; // price per kg

	@Column(nullable = false)
	private String clientName;

	@ManyToOne
	@JoinColumn(name = "harvest_id", nullable = false)
	private Harvest harvest;

	@Transient
	public double getTotalRevenue() {
		return quantity * unitPrice;
	}
}
