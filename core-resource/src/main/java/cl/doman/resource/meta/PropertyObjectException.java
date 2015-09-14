package cl.doman.resource.meta;


public class PropertyObjectException extends Exception{
	
	public PropertyObjectException(){
		super();
	}
	
	public PropertyObjectException(String msg){
		super(msg);
	}
	
	public PropertyObjectException(Throwable e){
		super(e);
	}
	
	public PropertyObjectException(String msg,Throwable e){
		super(msg, e);
	}
}
