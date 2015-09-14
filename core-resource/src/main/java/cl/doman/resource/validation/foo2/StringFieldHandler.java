package cl.doman.resource.validation.foo2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.doman.resource.validation.RutCheckDigit;



public class StringFieldHandler extends FieldHandler<String>{
	static Logger log =LoggerFactory.getLogger(StringFieldHandler.class);

	
	private String field;
	
	public StringFieldHandler(String field){
		this.field = field;
	}

	
	protected String convert(String p) throws Exception {
		return p;
	}

	
	public StringFieldHandler maxlength(final int length){
		Clause<String> clause = new Clause<String>(){
			
			public void hasError(String value) throws ValidationException {
				if(value.length() > length){
					throw new ValidationException("maxlength");
				}
			}
		};
		this.addCondition(clause);

		return this;
	}
	
	public StringFieldHandler minlength(final int length){
		Clause<String> clause = new Clause<String>(){
			
			public void hasError(String value) throws ValidationException {
				if(value.length() < length){
					throw new ValidationException("minlength");
				}
			}
		};
		this.addCondition(clause);

		return this;
	}
	

	
	public StringFieldHandler regex(final Pattern pattern){
		Clause<String> clause = new Clause<String>(){
			
			public void hasError(String value) throws ValidationException {
				Matcher matcher = pattern.matcher(field);
				if((!matcher.matches())){
					throw new ValidationException("regex");
				}
			}
		};
		this.addCondition(clause);
		
		return this;
	}
	
	public StringFieldHandler regex(final String patternstr){
		Clause<String> clause = new Clause<String>(){
			
			public void hasError(String value) throws ValidationException {
				Pattern pattern = Pattern.compile(patternstr);
				Matcher matcher = pattern.matcher(field);
				if((!matcher.matches())){
					throw new ValidationException("regex");
				}
			}
		};
		this.addCondition(clause);
		
		return this;
	}
	
	
	public StringFieldHandler email(){
		Clause<String> clause = new Clause<String>(){
			
			public void hasError(String value) throws ValidationException {
				EmailValidator validator = EmailValidator.getInstance();
				if((!validator.isValid(field))){
					throw new ValidationException("email");
				}
			}
		};
		this.addCondition(clause);
		
		return this;
	}
	
	public StringFieldHandler rut(){
		Clause<String> clause = new Clause<String>(){
			
			public void hasError(String value) throws ValidationException {
				String value_  = value.replaceAll("\\.","").replaceAll("\\s","").replaceAll("-","");
				RutCheckDigit rutCheckDigit = new RutCheckDigit();
				if(!rutCheckDigit.isValid(value_)){
					throw new ValidationException("rut");
				}
			}
		};
		this.addCondition(clause);
		
		return this;
	}


}
