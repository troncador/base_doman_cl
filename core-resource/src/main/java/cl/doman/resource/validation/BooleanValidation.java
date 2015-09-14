package cl.doman.resource.validation;

public interface BooleanValidation {

	boolean check(String field) throws Exception;
	ParamError getParamError();
	
}
