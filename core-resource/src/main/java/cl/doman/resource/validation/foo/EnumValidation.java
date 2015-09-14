package cl.doman.resource.validation.foo;

import java.io.File;
import java.util.List;


public class EnumValidation<E extends Enum<E>> extends Validation<Enum<E>>{
	private Class<E> enumClass;
	private String field;

	public EnumValidation(Class<E> enumClass,String field){
		this.enumClass = enumClass;
		this.field = field;
	}


	@Override
	public boolean nullOrWhite() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Enum<E> convertValue(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}