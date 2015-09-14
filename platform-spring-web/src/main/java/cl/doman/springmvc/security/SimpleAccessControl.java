package cl.doman.springmvc.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public abstract class SimpleAccessControl implements AccessControl{
	private String login = "LOGIN";
	private HttpServletRequest request;
	
	
	public SimpleAccessControl(){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		request = attributes.getRequest();
		
	}
	
	
	public UserSession getLoginUser() throws AccessControlException {
		HttpSession httpSession = request.getSession(false);
		if(httpSession == null){
			throw new AccessControlException("User not logued");
		}
		Object userSession = httpSession.getAttribute(login);

		if((userSession != null) && ( UserSession.class.isInstance(userSession) )) {
			UserSession userSession_ = (UserSession)userSession;
			return userSession_;
		}
		throw new AccessControlException();
	}

	
	public boolean isLogin() {
		try {
			getLoginUser();
			return true;
		} catch (AccessControlException e) {
			return false;
		}

	}

	
	public void login(String user) throws AccessControlException {
		try {
			HttpSession httpSession = request.getSession(true);
			UserSession userSession = getUserSession(user);
			httpSession.setAttribute(login,userSession);
		} catch (Exception e) {
			throw new AccessControlException(e.getMessage(),e);
		}
		
	}

	public List<String> getPermission(String key) throws AccessControlException{
		UserSession userSession = getLoginUser();
		return userSession.getPermission(key);
	}
	
	public boolean hasPermission(String key){
		try {
			UserSession userSession = getLoginUser();
			return userSession.hasPermission(key);
		} catch (AccessControlException e) {
			return false;
		}
	}
	
	public abstract UserSession getUserSession(String user) throws Exception;
	
	
	public void logout() {
		HttpSession httpSession = request.getSession(false);
		if(httpSession != null){
			httpSession.invalidate();
		}
	}

}
