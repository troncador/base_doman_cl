package cl.doman.springmvc.datatable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Search {
	static Logger log = LoggerFactory.getLogger(Search.class);
	
	private String regex;
	private String value;
	
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
