package io.github.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.citronix.entity.Farm;

@Repository
public interface IFarmRepository extends JpaRepository<Farm, Long> {

}
