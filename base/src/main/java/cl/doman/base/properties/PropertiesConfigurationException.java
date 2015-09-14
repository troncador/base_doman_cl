package cl.doman.base.properties;

public class PropertiesConfigurationException  extends Exception {
	private static final long serialVersionUID = 1L;

	public PropertiesConfigurationException(){
	}
	
	public PropertiesConfigurationException(String msg){
		super(msg);
	}
	
	public PropertiesConfigurationException(Throwable e){
		super(e);
	}

	public PropertiesConfigurationException(String msg,Throwable e){
		super(msg,e);
	}
}
