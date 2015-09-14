package cl.doman.springmvc.datatable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Table {
	static Logger log = LoggerFactory.getLogger(Table.class);
	//https://datatables.net/manual/server-side

	
	public Table(){
		
	}
	
	private String id;
	private Column[] columns = new Column[0];
	private Integer draw;
	private Integer length;
	private Order[]  orders = new Order[0];
	private Search search = new Search();
	private Integer start;
	
	
	
	public 	Column[] getColumns() {
		return columns;
	}
	public void setColumns(Column[] columns) {
		this.columns = columns;
	}
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Order[] getOrder() {
		return orders;
	}
	public void setOrder(Order[] order) {
		this.orders = order;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
