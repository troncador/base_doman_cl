package cl.doman.core.mail.template;

public class EmailTemplateException extends Exception{
	
	public EmailTemplateException(){
	}
	
	public EmailTemplateException(String msg,Throwable e){
		super(msg, e);
	}
	public EmailTemplateException(Throwable e){
		super( e);

	}
	public EmailTemplateException(String msg){
		super(msg);
	}
}
