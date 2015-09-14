package cl.doman.base.initializator;

public class SystemInitializatorException extends Exception {
	private static final long serialVersionUID = 1L;

	public SystemInitializatorException(){
	}
	
	public SystemInitializatorException(String msg){
		super(msg);
	}
	
	public SystemInitializatorException(Throwable e){
		super(e);
	}

	public SystemInitializatorException(String msg,Throwable e){
		super(msg,e);
	}
}
