package cl.doman.base;


public interface Existance<OBJECT,ID> {
	OBJECT get(ID id) throws Exception;
	boolean exist(ID id) throws Exception;
	ID convert(String id) throws ConvertException;
}
