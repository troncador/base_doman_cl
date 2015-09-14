package cl.doman.springmvc.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class UserSession {
	static Logger log =LoggerFactory.getLogger(UserSession.class);
	
	private String username;
	private Map<String,List<String>> permission;
	

	public List<String> getPermission(String key){
		return (permission!=null)? permission.get(key) : new ArrayList<String>();
	}
	
	public boolean hasPermission(String key){
		return permission!=null && permission.containsKey(key);
	}
	
	
	public UserSession(){
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Map<String,List<String>> getPermission() {
		return permission;
	}
	public void setPermission(Map<String,List<String>> permission) {
		this.permission = permission;
	}
	
}
