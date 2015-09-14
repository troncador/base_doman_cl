package cl.doman.resource.validation.foo;

import java.util.ArrayList;
import java.util.List;

public class Element<P> {
	private List<ParamError> paramErrorList = new ArrayList<ParamError>();
	private P value;
	
	
	public List<ParamError> getParamErrorList() {
		return paramErrorList;
	}
	public void setParamErrorList(List<ParamError> paramErrorList) {
		this.paramErrorList = paramErrorList;
	}
	public P getValue() {
		return value;
	}
	public void setValue(P value) {
		this.value = value;
	}
	
	
}
