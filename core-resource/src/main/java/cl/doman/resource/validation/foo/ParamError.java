package cl.doman.resource.validation.foo;

public class ParamError {
	private String code;
	private Object[] args;

	public ParamError(String code){
		this.code = code;
	}
	
	public ParamError(String code,Object[] args){
		this.code = code;
		this.args = args;
	}

	public String getCode() {
		return code;
	}

	public Object[] getArgs() {
		return args;
	}

}
