package io.github.citronix.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import io.github.citronix.entity.Farm;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class FarmSearchDao {

	private final EntityManager em;

	public List<Farm> searchByCriteria(SearchRequest request) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Farm> criteriaQuery = criteriaBuilder.createQuery(Farm.class);
		List<Predicate> predicates = new ArrayList<>();

		Root<Farm> root = criteriaQuery.from(Farm.class);

		if (request.getName() != null) {
			Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + request.getName() + "%");
			predicates.add(namePredicate);
		} else if (request.getLocation() != null) {
			Predicate locationPredicate = criteriaBuilder.like(root.get("location"), "%" + request.getLocation() + "%");
			predicates.add(locationPredicate);
		} else if (request.getEstablishmentDate() != null) {
			Predicate locationPredicate = criteriaBuilder.like(root.get("establishmentDate"),
					"%" + request.getEstablishmentDate() + "%");
			predicates.add(locationPredicate);
		}

		criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));

		TypedQuery<Farm> query = em.createQuery(criteriaQuery);

		return query.getResultList();

	}
}
