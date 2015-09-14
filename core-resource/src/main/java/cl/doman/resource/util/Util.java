package cl.doman.resource.util;

public class Util {
	public static <T> T ifa(T t,T defaultValue){
		if(t==null){
			return defaultValue;
		}
		else{
			return t;
		}
	}
}
