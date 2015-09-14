package cl.doman.resource.validation;

public class SimpleBooleanValidation implements BooleanValidation{

	private boolean check;
	private ParamError paramError;

	public SimpleBooleanValidation(boolean check, String paramErrorStr){
		this.check = check;
		this.paramError = new ParamError(paramErrorStr);
	}
	
	public boolean check(String str) throws Exception {
		return check;
	}

	public ParamError getParamError() {
		return paramError;
	}

}
