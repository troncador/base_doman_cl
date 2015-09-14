package cl.doman.springmvc;

import org.springframework.http.HttpStatus;

public class HttpStatusErrorException extends Exception{
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	private String message;
	
	public HttpStatusErrorException(HttpStatus httpStatus){
		this.httpStatus = httpStatus;
		this.message = "";
	}
	
	public HttpStatusErrorException(HttpStatus httpStatus,String message){
		this.httpStatus = httpStatus;
		this.message = message;
	}
	
	public HttpStatus getStatus(){
		return httpStatus;
	}

	public String getMessage(){
		return message;
	}
}
