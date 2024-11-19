package io.github.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.citronix.entity.Sale;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {

}
