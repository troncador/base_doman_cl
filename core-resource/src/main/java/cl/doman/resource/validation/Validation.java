package cl.doman.resource.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.doman.base.ConvertException;
import cl.doman.base.Existance;




public class Validation {
	Pattern whiteSpace = Pattern.compile("^\\s*$");
	static Logger log =LoggerFactory.getLogger(Validation.class);
	
	private String field;
	private List<ParamError> paramErrorList = new ArrayList<ParamError>();
	
	public Validation(String field){
		this.field = field;
	}

	public void setField(String field){
		this.field = field;
	}
	
	public Validation required(){
		if(nullOrWhite()){
			paramErrorList.add(new ParamError("required"));
		}
		return this;
	}
	
	public Validation ifTrueAddError(boolean condition,ParamError paramError){
		if(nullOrWhite()){
			return this;
		}
		if(condition){
			paramErrorList.add(paramError);
		}
		return this;
	}
	
	
	public Validation maxlength(int length){
		if(nullOrWhite()){
			return this;
		}
		boolean condition =  field.length() > length;
		return ifTrueAddError(condition, new ParamError("maxlength",new Object[]{length}));

	}
	
	
	
	public Validation minlength(int length){
		if(nullOrWhite()){
			return this;
		}
		boolean condition = field.length() < length;
		return ifTrueAddError(condition, new ParamError("minlength",new Object[]{length}));
	}
	
	public Validation regex(Pattern pattern){
		if(nullOrWhite()){
			return this;
		}
		Matcher matcher = pattern.matcher(field);
		boolean condition = (!matcher.matches());
		return ifTrueAddError(condition,new ParamError("regex"));

	}
	
	public Validation regex(String patternstr){
		if(nullOrWhite()){
			return this;
		}
		Pattern pattern = Pattern.compile(patternstr);
		Matcher matcher = pattern.matcher(field);
		boolean condition = !matcher.matches();
		return ifTrueAddError(condition, new ParamError("regex"));
	}
	
	public Validation email(){
		if(nullOrWhite()){
			return this;
		}
		EmailValidator validator = EmailValidator.getInstance();
		boolean condition = !validator.isValid(field);
		return ifTrueAddError(condition, new ParamError("email"));
	}

	public Validation isDate(String format){
		if(nullOrWhite()){
			return this;
		}
		boolean condition = false;
		try{
	 		DateFormat dateFormat = new SimpleDateFormat(field);
	 		dateFormat.parse(field);
		} catch (ParseException e) {
			condition = true;
		}
		return ifTrueAddError(condition, new ParamError("not-number"));
	}
	
	
	public Validation isNaturalNumber(){
		if(nullOrWhite()){
			return this;
		}
		boolean condition = false;
		try{
			int number = Integer.parseInt(field);
			if (number < 0){
				condition = true;
			}
		} catch ( NumberFormatException e){
			condition = true;
		}
		return ifTrueAddError(condition, new ParamError("not-number"));
	}
	
	public Validation rut(){
		if(nullOrWhite()){
			return this;
		}
		String value  = field.replaceAll("\\.","").replaceAll("\\s","").replaceAll("-","");
		RutCheckDigit rutCheckDigit = new RutCheckDigit();
		boolean condition = !rutCheckDigit.isValid(value);
		return ifTrueAddError(condition,new ParamError("rut"));
	}
	
	public Validation booleanValidation(BooleanValidation booleanValidation) throws Exception{
		if(nullOrWhite()){
			return this;
		}
		boolean fail = !booleanValidation.check(field);
		ParamError paramError = booleanValidation.getParamError();
		return ifTrueAddError(fail,paramError);//ifTrueAddError(condition,new ParamError("rut"));
	}
	
	public Validation noExist(Existance existance) throws Exception{
		if(nullOrWhite()){
			return this;
		}
		Object object = existance.convert(field);
		boolean condition = existance.exist(object);
		return ifTrueAddError(condition,new ParamError("unexist"));
	}
	
	public Validation exist(Existance existance) throws Exception{
		if(nullOrWhite()){
			return this;
		}
		Object object = existance.convert(field);
		boolean condition = !existance.exist(object);
		return ifTrueAddError(condition,new ParamError("unexist"));
	}
	
	public boolean hasError(){
		return !paramErrorList.isEmpty();
	}
	
	public List<ParamError> getErrorList(){
		return paramErrorList;
	}
	
	public String getField(){
		return field;
	}
	
	private boolean nullOrWhite(){
		//TODO: falta cache
		return (field == null || field.isEmpty() || whiteSpace.matcher(field).matches());
	}

	public <E extends Enum<E>> Validation isEnum(Class<E> class1) {
		if(nullOrWhite()){
			return this;
		}
		boolean condition = false;
		try{
			Enum.valueOf(class1, field);
		} catch (IllegalArgumentException e){
			condition = true;
		}
		return ifTrueAddError(condition,new ParamError("enum"));
	}
}
