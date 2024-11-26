package io.github.citronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.citronix.entity.Tree;

@Repository
public interface ITreeRepository extends JpaRepository<Tree, Long> {
}
