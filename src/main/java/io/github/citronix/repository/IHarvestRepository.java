package io.github.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.citronix.entity.Harvest;

@Repository
public interface IHarvestRepository extends JpaRepository<Harvest, Long> {

}