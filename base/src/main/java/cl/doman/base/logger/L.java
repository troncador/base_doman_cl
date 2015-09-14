package cl.doman.base.logger;

import java.util.Arrays;

public class L {
	
	static public String a(Iterable<? extends String> args){
		StringBuffer stringBuffer= new StringBuffer();
		for(String str: args){
			stringBuffer.append("'");
			stringBuffer.append(str);
			stringBuffer.append("',");
		}
		return stringBuffer.toString();
	}
	
	
	static public String a(String[] args){
		return a(Arrays.asList(args));
	}
}
