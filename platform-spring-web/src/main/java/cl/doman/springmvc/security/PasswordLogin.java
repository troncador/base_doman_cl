package cl.doman.springmvc.security;

import java.security.NoSuchAlgorithmException;


public class PasswordLogin { //implements CustomLogin {
//	private String key;
//	private CoreUser user;
//	
//	public PasswordLogin(String mail, String password) throws QueryException{
//		this.key = password;
//		
//		CoreUserQuery query = new CoreUserQuery();
//		user = query.getByMail(mail);
//	}
//	
//	@Override
//	public CoreUser getUser() {
//		return user;
//	}
//
//	@Override
//	public boolean check() {
//		MD5Generator md5Generator = new MD5Generator();
//		return md5Generator.checkMd5(user.getPassword(), key);
//
//	}
}
