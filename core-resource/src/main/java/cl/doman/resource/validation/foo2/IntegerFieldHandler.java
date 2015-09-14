package cl.doman.resource.validation.foo2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IntegerFieldHandler extends FieldHandler<Integer>{
	static Logger log =LoggerFactory.getLogger(IntegerFieldHandler.class);
	
	public IntegerFieldHandler(){
	}

	
	protected Integer convert(String p) throws Exception {
		return Integer.parseInt(p);
	}

	
	public IntegerFieldHandler maxlength(final int length){
		Clause<Integer> clause = new Clause<Integer>(){
			
			public void hasError(Integer value) throws ValidationException {
				if(value > length){
					throw new ValidationException("maxlength");
				}
			}
		};
		this.addCondition(clause);

		return this;
	}
	
	
	public IntegerFieldHandler minlength(final int length){
		Clause<Integer > clause = new Clause<Integer>(){
			public void hasError(Integer value) throws ValidationException {
				if(value<length){
					throw new ValidationException("minlength");
				}
			}
		};
		this.addCondition(clause);

		return this;
	}
}
