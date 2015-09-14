package cl.doman.springmvc.datatable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Column {
	static Logger log = LoggerFactory.getLogger(Column.class);
	
	private String data;
	private String name;
	private Boolean orderable;
	private Search search = new Search();
	private Boolean searchable;
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getOrderable() {
		return orderable;
	}
	public void setOrderable(Boolean orderable) {
		this.orderable = orderable;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public Boolean isSearchable() {
		return searchable;
	}
	public void setSearchable(Boolean searchable) {
		this.searchable = searchable;
	}
}
