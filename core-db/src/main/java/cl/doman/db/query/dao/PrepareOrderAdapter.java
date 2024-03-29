package cl.doman.db.query.dao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;


public  class PrepareOrderAdapter<T> implements PrepareOrder<T> {
	public enum Direction{ASC,DES}
	private List<Pair<SingularAttribute<T, ?>, Direction>> directionedColumnList =
			new ArrayList<Pair<SingularAttribute<T, ?>, Direction>>();
	
	public PrepareOrderAdapter(){}
	
	public PrepareOrderAdapter(SingularAttribute<T,?> column){
		add(column);
	}
	
	public PrepareOrderAdapter(SingularAttribute<T,?> column, Direction order){
		add(column,order);
	}
	
	public void add(SingularAttribute<T,?> column, Direction direction){
		Pair<SingularAttribute<T, ?>, Direction> pair = 
				new ImmutablePair<SingularAttribute<T, ?>, Direction>(column,direction);
		directionedColumnList.add(pair);
	}
	public void add(SingularAttribute<T,?> column){
		Pair<SingularAttribute<T, ?>, Direction> pair = 
				new ImmutablePair<SingularAttribute<T, ?>, Direction>(column,Direction.ASC);
		directionedColumnList.add(pair);
	}
	
	public Order[] getOrderArray(Root<T> root,CriteriaBuilder criteriaBuilder){
		List<Order> orderList = new ArrayList<Order>();
		
		for(Pair<SingularAttribute<T, ?>, Direction> directionedColumn : directionedColumnList){
			Direction direction = directionedColumn.getRight();
			SingularAttribute<T, ?> column = directionedColumn.getLeft();
			Path<?> path = root.get(column);
			Order order = null;
			switch (direction){
				case ASC:
					order = criteriaBuilder.asc(path);
					break;
				case DES:
					order = criteriaBuilder.desc(path);
					break;
			}
			orderList.add(order);
		}
		return orderList.toArray(new Order[orderList.size()]);
	}
}
