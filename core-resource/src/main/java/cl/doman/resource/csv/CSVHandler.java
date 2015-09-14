package cl.doman.resource.csv;

import java.util.List;
import java.util.Map;

public class CSVHandler {
	private List<Map<String, Object>> in;

	public CSVHandler(){
		this(',','"');
	}
	public CSVHandler(char delimiter, char quotechar){
		
	}
	
	public void process(CSVProcess process){
		
	}
	
	public void setData(List<Map<String,Object>> in){
		this.in = in;
	}
	
	
	public List<Map<String,Object>> getData(){
		return null;
	}
	
	public void read(String filepath){
		
	}
	
	public void write(String filepath){
		
	}
	
	public String toString(){
		return "";
	}
}
