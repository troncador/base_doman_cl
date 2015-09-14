package cl.doman.resource.crypto;

import java.math.BigInteger;
import java.security.MessageDigest;

public abstract class CryptGenerator {
	private MessageDigestAlgorithm messageDigestAlgorithm;
	private String format = "%032x";
	private String pepper = "94=)sdaS4";
	
	public CryptGenerator(MessageDigestAlgorithm messageDigestAlgorithm,String pepper){
		this.messageDigestAlgorithm = messageDigestAlgorithm;
		this.pepper = pepper;
	}
	
	public CryptGenerator(){
		this.messageDigestAlgorithm = MessageDigestAlgorithm.MD5;
	}
	public String getSalted(String code){
		return code+pepper; 
	}
	
	
	public String getEncode(String str){
		MessageDigest messageDigest = messageDigestAlgorithm.getMessageDigest();
		String toEnc = getSalted(str);
		messageDigest.update(toEnc.getBytes(), 0, toEnc.length());
		BigInteger bigInteger = new BigInteger(1, messageDigest.digest());
		String hexadecimal = String.format(format, bigInteger);
		return hexadecimal.toUpperCase();
	}
	

	
	public boolean check(String str, String cryptedStr){
		String md5 = getEncode(cryptedStr);
		return md5.equals(str);
	} 
}
