package cl.doman.springmvc.security;

public interface AccessControl {
	UserSession getLoginUser() throws AccessControlException;
	boolean isLogin();
	//permission getLoginKind() throws AccessControlException;
	void login(String user) throws AccessControlException;
	void logout();
	
	
//	Object getPrincipal();
//	Object getCredentials();
//	void setAuthenticated(boolean isAuthenticated); 
//	void eraseCredentials();
	
	
//	Authentication – Stores information about User Credentials and Authorities (role which user has).
//
//	Principal – User in consideration is called as Principal in context of Spring Security
	
}
