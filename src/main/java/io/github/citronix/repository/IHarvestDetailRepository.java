package io.github.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.citronix.entity.HarvestDetail;

@Repository
public interface IHarvestDetailRepository extends JpaRepository<HarvestDetail, Long> {

}
