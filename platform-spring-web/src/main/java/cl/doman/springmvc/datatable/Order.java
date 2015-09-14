package cl.doman.springmvc.datatable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Order {
	static Logger log = LoggerFactory.getLogger(Order.class);
	public enum Dir{asc,desc}
	
	private Integer column;
	private Dir dir = Dir.asc;
	
	public Integer getColumn() {
		return column;
	}
	public void setColumn(Integer column) {
		this.column = column;
	}
	public Dir getDir() {
		return dir;
	}
	public void setDir(Dir dir) {
		this.dir = dir;
	}
}
