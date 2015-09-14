package cl.doman.resource.validation.foo2;



public class EnumFieldHandler<E extends Enum<E>> extends FieldHandler<Enum<E>>{
	private Class<E> enumClass;

	public EnumFieldHandler(Class<E> enumClass){
		this.enumClass = enumClass;
	}

	@Override
	protected Enum<E> convert(String p) throws Exception {
		return Enum.valueOf(enumClass, p);
	}

	
}