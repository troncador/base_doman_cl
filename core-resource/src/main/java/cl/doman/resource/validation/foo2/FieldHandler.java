package cl.doman.resource.validation.foo2;

import java.util.List;
import java.util.regex.Pattern;


public abstract class FieldHandler <P>{
	public List<Clause<P>> clauseList;
	boolean required = false;
	
	private Pattern whiteSpace = Pattern.compile("^\\s*$");
	
	
	protected abstract P convert(String p) throws Exception;
	
	private boolean nullOrWhite(String field){
		return (field == null || field.isEmpty() || whiteSpace.matcher(field).matches());
	}
	
	
	
	protected void addCondition(Clause<P> clause){
		clauseList.add(clause);
	}
	
	public FieldHandler<P> required(){
		required = true;
		return this;
	}
	
	public FieldHandler<P> required(boolean required){
		this.required = required;
		return this;
	} 
	
	public P validate(String pstr) throws ValidationException{
		if(!required && nullOrWhite(pstr)){
			return null;
		}
		if(required && nullOrWhite(pstr)){
			throw new ValidationException("required");
		}	
		P p;
		try {
			p = convert(pstr);
		} catch (Exception e) {
			throw new ValidationException("convert");
		}
		for(Clause<P> clause : clauseList){
			clause.hasError(p);
		}
		return p;
	}
}
