package cl.doman.db.model.convert;

public interface Convertable<E,T> {
	T convert();

	E getFromValue(T value);
}