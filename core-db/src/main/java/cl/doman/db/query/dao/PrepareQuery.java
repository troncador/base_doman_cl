package cl.doman.db.query.dao;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public interface PrepareQuery<T> extends PreparePredicate<T>, PrepareOrder<T>,PrepareField<T>, PrepareLimit {
//	public Order[] getOrderArray(Root<T> root,CriteriaBuilder criteriaBuilder);
//	public Integer getPageStart();
//	public Integer getPageSize();
	//findAll(int start,int pageSize,String sortField, SortOrder sortOrder, Map<String, String> filters);
}
