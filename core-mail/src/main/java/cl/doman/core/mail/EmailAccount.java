package cl.doman.core.mail;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final public class EmailAccount {
	private static Pattern emailPattern = 
			Pattern.compile(
			"^" +
			"([0-9a-zA-Z](?:[-\\.\\w]*[0-9a-zA-Z]))*" +
			"@" +
			"((?:[0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+" +
			"[a-zA-Z]{2,9})" +
			"$"
			);
	
	private String name;
	private String mail;
	private String local;
	private String domain;
	
	public EmailAccount(String mail){
		this(mail,null);
		
	}
	
	public EmailAccount(String mail,String name){
		this.setMail(mail);
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if(name == null){
			name = "";
		}
		this.name = name;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		Matcher matcher = emailPattern.matcher(mail);
		if(!matcher.matches()){
			String msg = String.format("%s is not a valid mail", mail);
			throw new InvalidParameterException(msg);
		}
		this.local = matcher.group(1);
		this.domain = matcher.group(2);
		this.mail = mail;
	}
	
	public String getLocal(){
		return local;
	}
	
	public String getDomain(){
		return domain;
	}
	public static void main(String [] args){
		EmailAccount a = new EmailAccount("benjamin.almarza@gmail.com");
		System.out.println(a.getDomain());
	}
}
