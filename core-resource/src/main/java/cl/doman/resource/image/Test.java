package cl.doman.resource.image;


import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static final transient Logger log =LoggerFactory.getLogger(Test.class);
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		ResourceSuplier resourceSuplier = new ResourceSuplier("/home/troncador/","jpg");
		for(String a: resourceSuplier.getids()){
			log.info(a);
		}
	}

}
