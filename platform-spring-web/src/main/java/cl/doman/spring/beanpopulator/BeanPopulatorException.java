package cl.doman.spring.beanpopulator;

public class BeanPopulatorException extends Exception{
	private static final long serialVersionUID = 1L;
	
	private String path;

	public String getPath(){
		return path;
	}
	
	public BeanPopulatorException(String path){
		super();
		this.path = path;
	}
	
	public BeanPopulatorException(String path,String msg){
		super(msg);
		this.path = path;
	}
	
	public BeanPopulatorException(String path,Throwable e){
		super(e);
		this.path = path;
	}
	
	public BeanPopulatorException(String path,String msg,Throwable e){
		super(msg, e);
		this.path = path;
	}
}
