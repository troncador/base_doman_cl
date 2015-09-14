package cl.doman.db.query.dao;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;


public interface PrepareOrder<T> {
	public Order[] getOrderArray(Root<T> root,CriteriaBuilder criteriaBuilder);

}
