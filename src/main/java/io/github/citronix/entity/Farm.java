package io.github.citronix.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

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
class Farm extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private Double totalArea;

	@Column(nullable = false)
	private LocalDate establishmentDate;

	@OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
	private List<Field> fields = new ArrayList<>();
}
