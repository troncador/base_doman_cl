package cl.doman.db.query.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface PreparePredicate<T> {
	public Predicate getPredicate(Root<T> root,CriteriaBuilder criteriaBuilder);
}
