package cl.doman.resource.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ParamHandler {
	static Logger log = LoggerFactory.getLogger(ParamHandler.class);
	private Map<String, String[]> parameterMap;
	
	private Map<String,Validation> validationList = new HashMap<String,Validation>();

	public ParamHandler(Map<String, String[]> parameterMap){
		this.parameterMap = parameterMap;
	}
	
//	private Map<String, String[]> getParameterMap(ServletRequest servletRequest) {
//		@SuppressWarnings("unchecked")
//		Map<String, String[]> result = servletRequest.getParameterMap();
//		return result;
//	}
	
	
	public String get(String paramName){
		String[] stringArray = parameterMap.get(paramName);
		return stringArray[0];
	}
	
	public boolean hasField(String paramName){
		return parameterMap.containsKey(paramName);
	}
	
	public Validation getValidation(String paramName){
		if(validationList.containsKey(paramName)){
			return validationList.get(paramName);
		} else {
			String[] rawParameter = parameterMap.get(paramName);
			String parameter = (rawParameter != null && rawParameter.length > 0)? rawParameter[0]: null;
			Validation validation = new Validation(parameter);
			validationList.put(paramName, validation);
			return validation;
		}
	}
	
	public List<ParamError> getFieldErrors(String paramName){
		if(!validationList.containsKey(paramName)){
			return new ArrayList<ParamError>();
		} else {
			Validation validation = validationList.get(paramName);
			return validation.getErrorList();
		}
	}
	
	public boolean hasFieldErrors(String paramName){
		if(!validationList.containsKey(paramName)){
			return false;
		} else {
			Validation validation = validationList.get(paramName);
			return validation.hasError();
		}
	}
	
	public boolean hasError(){
		for(Validation validation: validationList.values()){
			if(validation.hasError()){
				return true;
			}
		}
		return false;
	}
}
