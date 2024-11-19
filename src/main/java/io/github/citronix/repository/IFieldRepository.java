package io.github.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.citronix.entity.Field;

@Repository
public interface IFieldRepository extends JpaRepository<Field, Long> {

}
