package cl.doman.springmvc.security;

public class AccessControlException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public AccessControlException(){
	}
	
	public AccessControlException(String msg,Throwable e){
		super(msg, e);
	}
	public AccessControlException(Throwable e){
		super( e);

	}
	public AccessControlException(String msg){
		super(msg);
	}
}
