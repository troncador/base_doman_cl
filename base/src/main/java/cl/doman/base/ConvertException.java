package cl.doman.base;

public class ConvertException extends Exception{
	private static final long serialVersionUID = 1L;

	public ConvertException(){
	}
	
	public ConvertException(String msg){
		super(msg);
	}
	
	public ConvertException(Throwable e){
		super(e);
	}

	public ConvertException(String msg,Throwable e){
		super(msg,e);
	}
}
