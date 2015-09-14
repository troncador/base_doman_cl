package cl.doman.resource.validation.foo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.doman.resource.validation.RutCheckDigit;



public class StringValidation extends Validation<String>{
	Pattern whiteSpace = Pattern.compile("^\\s*$");
	static Logger log =LoggerFactory.getLogger(StringValidation.class);

	
	private String field;
	
	public StringValidation(String field){
		this.field = field;
	}
	
	public StringValidation maxlength(final int length){
		Clause clause = new Clause() {
			public boolean hasError() {
				return nullOrWhite()? false:(field.length() > length);
			}

			
			public ParamError getError() {
				return new ParamError("maxlength",new Object[]{length});
			};
		};
		this.addCondition(clause);

		return this;
	}
	
	public StringValidation minlength(final int length){
		Clause clause = new Clause() {
			public boolean hasError() {
				return nullOrWhite()? false:(field.length() < length);
			}

			
			public ParamError getError() {
				return new ParamError("minlength",new Object[]{length});
			};
		};
		this.addCondition(clause);
		
		return this;
	}
	
	public StringValidation regex(final Pattern pattern){
		Clause clause = new Clause() {
			public boolean hasError() {
				Matcher matcher = pattern.matcher(field);
				return nullOrWhite()? false:(!matcher.matches());
			}

			
			public ParamError getError() {
				return new ParamError("regex");
			};
		};
		this.addCondition(clause);
		
		return this;
	}
	

	
	public StringValidation regex(final String patternstr){
		Clause clause = new Clause() {
			public boolean hasError() {
				Pattern pattern = Pattern.compile(patternstr);
				Matcher matcher = pattern.matcher(field);
				return nullOrWhite()? false:(!matcher.matches());
			}

			
			public ParamError getError() {
				return new ParamError("regex");
			};
		};
		this.addCondition(clause);
		
		return this;
	}
	
	public StringValidation email(){
		Clause clause = new Clause() {
			public boolean hasError() {
				EmailValidator validator = EmailValidator.getInstance();
				return nullOrWhite()? false:!validator.isValid(field);
			}

			
			public ParamError getError() {
				return new ParamError("email");
			};
		};
		this.addCondition(clause);
		
		return this;
	}

	public StringValidation rut(){
		Clause clause = new Clause() {
			public boolean hasError() {
				String value  = field.replaceAll("\\.","").replaceAll("\\s","").replaceAll("-","");
				RutCheckDigit rutCheckDigit = new RutCheckDigit();
				return nullOrWhite()? false:!rutCheckDigit.isValid(value);
			}

			
			public ParamError getError() {
				return new ParamError("rut");
			};
		};
		this.addCondition(clause);
		
		return this;
	}
	
	
	
	public StringValidation ifTrueAddError(final boolean condition,final ParamError paramError){
		return (StringValidation) super.ifTrueAddError(condition,paramError);
	}
	
	
	public StringValidation booleanValidation(Clause condition) throws Exception{
		return (StringValidation) super.booleanValidation(condition);
	}
	
	
	public StringValidation required(){
		return (StringValidation) super.required();
	}
	
	
	private Boolean isnullOrWhite;
	
	
	public boolean nullOrWhite(){
		//cache
		if(isnullOrWhite == null){
			isnullOrWhite = (field == null || field.isEmpty() || whiteSpace.matcher(field).matches());
		}
		return isnullOrWhite;
	}

	
	public String convertValue(String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
