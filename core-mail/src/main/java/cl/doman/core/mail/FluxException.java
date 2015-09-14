package cl.doman.core.mail;

@SuppressWarnings("serial")
public class FluxException extends Exception{
	public enum FluxExceptionKind {
		INTERNAL_ERROR,
		AUTORIZATION,
		UNSPECTED
	}
	
	
	private FluxExceptionKind exceptionKind = FluxExceptionKind.INTERNAL_ERROR; 

	public FluxException(FluxExceptionKind exceptionKind,String msg, Throwable e){
		super(msg,e);
		this.exceptionKind = exceptionKind;
	}
	
	public FluxExceptionKind getExceptionKind(){
		return this.exceptionKind;
	}

}
