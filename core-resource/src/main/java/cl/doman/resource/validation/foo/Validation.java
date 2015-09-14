package cl.doman.resource.validation.foo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Validation<P> {
	private List<Clause> conditionList = new ArrayList<Clause>();
	private Map<Integer,Element<P>> elementMap = new HashMap<Integer,Element<P>>();
	
	//private Map<Integer,List<ParamError>> paramErrorMap = new HashMap<Integer,List<ParamError>>();
	
	private boolean isRequired = false;
	public abstract boolean nullOrWhite();
	public abstract P convertValue(String value);
	
	public Element<P> getCurrentElement(){
		return elementMap.get(row);
	}
	
	private int row = -1;
	public boolean run(String value){
		
		P object = convertValue(value);
		
		row++;
		if(!isRequired && this.nullOrWhite()){
			return false;
		}
		
		for(Clause clause : conditionList){
			if(clause.hasError()){
				error = true;
				List<ParamError> paramErrorList = getErrorList(row);
				paramErrorList.add(clause.getError());
			}
		}
		return true;
	}
	
	
	private boolean error = false;
	
	public boolean hasError(){
		return error;
	}
	
	public boolean hasError(int row){
		List<ParamError> paramErrorList = getErrorList(row);
		return paramErrorList.isEmpty();
	}
	
	public List<ParamError> getErrorList(int row){
		List<ParamError> paramErrorList =  null;//paramErrorMap.get(row);
		return (paramErrorList!=null)?paramErrorList:new ArrayList<ParamError>();
	}
	
	public void addCondition(Clause condition) {
		conditionList.add(condition);
	}
	
	public List<Clause> getConditionList() {
		return conditionList;
	}
	
	public Validation<P> required(){
		isRequired = true;
		Clause condition = new Clause() {
			public boolean hasError() {
				return nullOrWhite();
			}
			
			public ParamError getError() {
				return new ParamError("required");
			};
		};
		this.addCondition(condition);
		return this;
	}
	
	
	public Validation<P> ifTrueAddError(final boolean condition,final ParamError paramError){
		Clause clause = new Clause() {
			public boolean hasError() {
				return nullOrWhite()? false : condition;
			}
			
			public ParamError getError() {
				return paramError;
			};
		};
		this.addCondition(clause);

		return this;
	}
	
	public Validation<P> booleanValidation(Clause condition) throws Exception{
		this.addCondition(condition);
		
		return this;
	}

}
