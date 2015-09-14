package cl.doman.db.query.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

public class PrepareQueryAdapter<T> implements PrepareQuery<T> {
	private PreparePredicate<T> preparePredicate;
	private PrepareLimit prepareLimit;
	private PrepareOrder<T> prepareOrder;
	private PrepareField<T> prepareField;
	
	public PrepareQueryAdapter(){
	}
	
	public PrepareQueryAdapter(PrepareLimit limit){
		this.prepareLimit = limit;
	}
	
	public PrepareQueryAdapter(PreparePredicate<T> predicate){
		this.preparePredicate = predicate;
	}
	
	public PrepareQueryAdapter(PrepareField<T> prepareField){
		this.prepareField = prepareField;
	}
	
	public PrepareQueryAdapter(PrepareOrder<T> order){
		this.prepareOrder = order;
	}
	
	public PrepareQueryAdapter(PrepareOrder<T> order, PrepareLimit limit){
		this.prepareLimit = limit;
		this.prepareOrder = order;
	}
	
	public PrepareQueryAdapter(PreparePredicate<T> predicate, PrepareLimit limit){
		this.prepareLimit = limit;
		this.preparePredicate = predicate;
	}
	
	public PrepareQueryAdapter(PreparePredicate<T> predicate, PrepareOrder<T> order){
		this.prepareOrder = order;
		this.preparePredicate = predicate;
	}
	
	public PrepareQueryAdapter(PreparePredicate<T> predicate, PrepareOrder<T> order,  PrepareLimit limit){
		this.prepareOrder = order;
		this.preparePredicate = predicate;
		this.prepareLimit = limit;
	}
	
	public Predicate getPredicate(Root<T> root, CriteriaBuilder criteriaBuilder) {
		if(preparePredicate!=null){
			return preparePredicate.getPredicate(root, criteriaBuilder);
		}
		return null;
	}

	public Order[] getOrderArray(Root<T> root,
			CriteriaBuilder criteriaBuilder) {
		if(prepareOrder!=null){
			return prepareOrder.getOrderArray(root, criteriaBuilder);
		}
		return null;
	}

	public Integer getMaxResults() {
		if(prepareLimit!=null){
			return prepareLimit.getMaxResults();
		}
		return null;
	}

	public Integer getFirstResult() {
		if(prepareLimit!=null){
			return prepareLimit.getFirstResult();
		}
		return null;
	}

	public Selection<?>[] getSelection(Root<T> root) {
		if(prepareField!=null){
			return prepareField.getSelection(root);
		}
		return null;
	}

}
