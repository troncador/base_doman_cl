package cl.doman.resource.validation.foo2;


public interface Clause<P> {

	void hasError(P value) throws ValidationException;
}
