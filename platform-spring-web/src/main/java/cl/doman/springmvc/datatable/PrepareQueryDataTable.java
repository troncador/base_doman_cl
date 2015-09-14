package cl.doman.springmvc.datatable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.SingularAttribute;

import cl.doman.db.query.dao.PrepareField;
import cl.doman.db.query.dao.PrepareQueryAdapter;
import cl.doman.spring.beanpopulator.BeanPopulator;
import cl.doman.spring.beanpopulator.BeanPopulatorDataTable;
import cl.doman.spring.beanpopulator.BeanPopulatorException;

public  class PrepareQueryDataTable<T> extends PrepareQueryAdapter<T> implements PrepareField<T> {
	private Table table;
//	private Map<String,Column> columnMap; 
//	private Map<String,cl.doman.spring.datatable.Order> orderMap;
//	private Map<String,SingularAttribute<T,?>> field = 
//			new HashMap<String,SingularAttribute<T,?>>();
//	
	
//	public void addField(String name,SingularAttribute<T,?> attribute){
//		field.put(name,attribute);
//	}
//	
//	public Order[] getOrderArray(Root<T> root, CriteriaBuilder criteriaBuilder) {
//		for(String name : orderMap.keySet()){
//			cl.doman.spring.datatable.Order order = orderMap.get(name);
//			field.get(name);
//		}
//		return null;
//	}
//	
	
	
	public Integer getDraw(){
		return table.getDraw();
	}
	
	public PrepareQueryDataTable(Map<String,String[]> parameterMap) throws BeanPopulatorException{
		table = new Table();
		
		Map<String,String> filteredMap = new HashMap<String, String>();
		for(String key : parameterMap.keySet()){
			filteredMap.put(key, parameterMap.get(key)[0]);
		}
		
		try{
			BeanPopulator convert = new BeanPopulatorDataTable();
			for(String key : filteredMap.keySet()){
				String value = filteredMap.get(key);
				convert.populate(key, table, value);
			}
		}catch(BeanPopulatorException e){
			//si falla un método, no importa, que haga su mejor esfuerzo
		}
		
//		List<Column> columnList = Arrays.asList(table.getColumns());
//		columnMap = new HashMap<String,Column>();
//		for(Column column:columnList){
//			String name = column.getName();
//			columnMap.put(name,column);
//			columnList.add(column);
//		}
//		orderMap = new HashMap<String,cl.doman.spring.datatable.Order>();
//		for(cl.doman.spring.datatable.Order order : table.getOrder()){
//			int id = order.getColumn();
//			Column column = columnList.get(id);
//			String columnname = column.getName();
//			orderMap.put(columnname, order);
//		}
	}
	
	@Override
	public Integer getMaxResults() {
		return table.getLength();
	}

	@Override
	public Integer getFirstResult() {
		return table.getStart();
	}
	
	
	@Override
	public Selection<?>[] getSelection(Root<T> root) {
		return null;
	}

}
