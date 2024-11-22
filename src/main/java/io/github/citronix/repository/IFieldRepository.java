package io.github.citronix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.citronix.entity.Field;

@Repository
public interface IFieldRepository extends JpaRepository<Field, Long> {
	@Query("SELECT f FROM Field f JOIN FETCH f.farm WHERE f.farm.id = :farmId")
	List<Field> findByFarmId(@Param("farmId") Long farmId);

	@Query("SELECT SUM(f.area) FROM Field f WHERE f.farm.id = :farmId")
	Double sumFieldAreasByFarmId(@Param("farmId") Long farmId);

	long countByFarmId(Long farmId);
}
