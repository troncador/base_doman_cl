package cl.doman.resource.validation.foo2;

import cl.doman.base.Existance;



public class EntityFieldHandler<OBJECT,ID> extends FieldHandler<OBJECT>{
	private Existance<OBJECT,ID> existance;

	public EntityFieldHandler(Existance<OBJECT,ID> existance){
		this.existance = existance;
	}

	@Override
	protected OBJECT convert(String p) throws Exception {
		ID id = existance.convert(p);
		/*
		 * 1) Qué hago si no existe?
		 * 2) Toda la idea de la existencia de un elemento pasa por reglas más complejas, por
		 * ejemplo si está status "deleted", no lo contemplo
		 */
		
		boolean exist = existance.exist(id);
		return existance.get(id);
	}

	
}