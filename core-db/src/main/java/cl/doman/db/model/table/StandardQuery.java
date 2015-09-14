package cl.doman.db.model.table;

import javax.persistence.metamodel.SingularAttribute;

import cl.doman.base.ConvertException;
import cl.doman.base.Existance;
import cl.doman.db.query.dao.EntityDaoCriteria;


public class StandardQuery <T extends StandardTable>
	extends EntityDaoCriteria<T, Integer> 
	implements Existance<T,Integer> {

	public StandardQuery(Class<T> tclass) throws ClassCastException {
		super(tclass);
	}

	public Integer convert(String id) throws ConvertException {
		try{
			return Integer.parseInt(id);
		} catch(NumberFormatException e){
			throw new ConvertException();
		}
	}

	@Override
	public SingularAttribute<? super StandardTable, Integer> getPK() {
		return StandardTable_.id;
	}

}
