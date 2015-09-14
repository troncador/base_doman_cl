package cl.doman.db.query.dao.iterate;

import java.util.Iterator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import cl.doman.db.QueryException;
import cl.doman.db.query.dao.EntityDao;
import cl.doman.db.query.dao.PreparePredicate;
import cl.doman.db.query.dao.PrepareQueryAdapter;

public class TableIterable<T> implements Iterable<T>{

	public enum Mode {FOR,WHILE}
	
	private int pageSize;
	private EntityDao<T, ?> entityDao;
	private PreparePredicate<T> preparePredicate;
	private Mode mode;

	
	public TableIterable(
			EntityDao<T, ?> entityDao,
			int pageSize,
			Mode mode
			){
		this(entityDao,new PrepareQueryAdapter<T>(),pageSize,mode);
	}
	
	

	public TableIterable(
			EntityDao<T, ?> entityDao,
			PreparePredicate<T> preparePredicate,
			int pageSize,
			Mode mode){
		
		this.pageSize = pageSize;
		this.entityDao = entityDao;
		this.preparePredicate = preparePredicate;
		this.mode = mode;
	}

	public Iterator<T> iterator() {
		try {
			switch (mode) {
			case FOR:
				return new TableIteratorFor<T>(entityDao, preparePredicate, pageSize);
			case WHILE:
				return new TableIteratorWhile<T>(entityDao, preparePredicate, pageSize);
			default:
				return new TableIteratorFor<T>(entityDao, preparePredicate, pageSize);
			}
		
		} catch (QueryException e) {
			throw new TableIterationException(e);
		}
		
	}
	
	
}
