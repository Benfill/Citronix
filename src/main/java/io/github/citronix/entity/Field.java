package io.github.citronix.entity;

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
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "fields")
public class Field extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Min(value = 1000, message = "A field must be at least 0.1 hectares (1,000 m²)")
	private Double area;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "farm_id", nullable = false)
	private Farm farm;

	@JsonManagedReference
	@OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Tree> trees = new ArrayList<>();

	@JsonManagedReference
	@OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Harvest> harvests = new ArrayList<>();
}