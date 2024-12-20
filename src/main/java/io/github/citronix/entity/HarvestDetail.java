package io.github.citronix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "harvest_details")
public class HarvestDetail extends BaseEntity {
	@Column(nullable = false)
	private Double quantity; // in kg

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "harvest_id", nullable = false)
	private Harvest harvest;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "tree_id", nullable = false)
	private Tree tree;
}
