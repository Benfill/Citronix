package io.github.citronix.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

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
@Table(name = "farms")
public class Farm extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	@Min(value = 0, message = "A Farm total area must be greater than 0")
	private Double totalArea;

	@Column(nullable = false)
	private LocalDate establishmentDate;

	@JsonManagedReference
	@OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Field> fields = new ArrayList<>();
}
