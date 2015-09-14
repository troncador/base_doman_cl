package cl.doman.springmvc.security;

import java.lang.reflect.Method;

import org.springframework.web.method.HandlerMethod;

public class Access {
	private AccessControl accessControl;
	private HandlerMethod handlerMethod;


	public Access(AccessControl accessControl,HandlerMethod handlerMethod){
		this.accessControl = accessControl;
		this.handlerMethod = handlerMethod;
	}
	
	
	
	public void setMethod(HandlerMethod handlerMethod){
    	Method method = handlerMethod.getMethod();
    	if (method.isAnnotationPresent(Permission.class) && accessControl.isLogin()){
    		Permission permission = method.getAnnotation(Permission.class);
    		String[] value = permission.value();
    		switch(permission.type()){
    		case ALL:
    			
    			break;
    		case NONE:
    			break;
    		}
    	}
	}
}
