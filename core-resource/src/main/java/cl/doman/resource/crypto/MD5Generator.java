package cl.doman.resource.crypto;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Generar hash md5
 * 
 * @author angel
 *
 */
public class MD5Generator {
	static Logger log =LoggerFactory.getLogger(MD5Generator.class);
	private String code = "94=)sdaS4";
	
	public MD5Generator(String code){
		this.code = code;
	}
	
	public MD5Generator(){}
	/**
	 * obtiene el md5 de un string y retorna su md5 en formato hexadecimal
	 * 
	 * @param seed string a convertir
	 * @return md5 del string pasado como parámetro en formato hexadecimal
	 * @throws NoSuchAlgorithmException
	 */
	public String stringToMd5Hex(String seed){
		String toEnc = (seed+code); // Value to encrypt
		MessageDigest messageDigest = MessageDigestAlgorithm.MD5.getMessageDigest();
		
		//
		messageDigest.update(toEnc.getBytes(), 0, toEnc.length());
		BigInteger bigInteger = new BigInteger(1, messageDigest.digest());
		String md5Hexadecimal = String.format("%032x", bigInteger);
		return md5Hexadecimal.toUpperCase();
	}
	
	/**
	 * Verifica que el md5 generado sea correcto.
	 * 
	 * Nota: el md5 debe hacer sido generado previamente por esta clase
	 * 
	 * @param msg mensaje a verificar 
	 * @param md5hex el md5 del mensaje en formato hexadecimal.
	 * @return Verdadero en caso que el md5 sea el correcto y Falso en caso contrario
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean checkMd5(String msg, String md5hex){
		String md5 = stringToMd5Hex(msg);
		if(md5.equals(md5hex)){
			return true;
		}else{
			return false;
		}
	}

}
