package cl.doman.db.query.dao.iterate;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.doman.db.QueryException;
import cl.doman.db.query.dao.EntityDao;
import cl.doman.db.query.dao.EntityDaoCriteria;
import cl.doman.db.query.dao.PrepareLimit;
import cl.doman.db.query.dao.PrepareLimitPagination;
import cl.doman.db.query.dao.PreparePredicate;
import cl.doman.db.query.dao.PrepareQuery;
import cl.doman.db.query.dao.PrepareQueryAdapter;

public class TableIteratorWhile<T> implements Iterator<T> {
	static Logger log = LoggerFactory.getLogger(TableIteratorWhile.class);
	private int cicle = 0;
	
	
	private Iterator<T> iteratorElement;
	private int pageSize;
	private EntityDao<T, ?> entityDao;
	private PreparePredicate<T> preparePredicate;
	private long totalNumberPage;
	
	public TableIteratorWhile(
			EntityDao<T, ?> entityDao,
			PreparePredicate<T> preparePredicate,
			int pageSize
			) throws QueryException{
		long length = entityDao.count(preparePredicate);
		log.info(String.format("length: %d", length));
		this.totalNumberPage = length / pageSize+1;
		
		this.entityDao = entityDao;
		this.pageSize = pageSize;
		this.preparePredicate = preparePredicate;
		
		updateIterator();
	}

	
	public boolean hasNext() {
		if(iteratorElement.hasNext()){
			return true;
		}
		updateIterator();
		return iteratorElement.hasNext();
	}
	
	public T next() {
		if(iteratorElement.hasNext()){
			return iteratorElement.next();
		}
		return iteratorElement.next();
	}

	private Date date;
	
	private void logRecord(){
		if(date!=null){
			Date newDate = new Date();
			log.info(String.format("%04d/%04d  %04d[mS]",cicle,
					totalNumberPage,newDate.getTime()-date.getTime()));
			date = newDate;
		} else {
			date = new Date();
			log.info(String.format(" %d/%d",cicle,totalNumberPage));
		}
	}
	
	
	private void updateIterator() {
		logRecord();
		try {
			PrepareLimit prepareLimit = new PrepareLimitPagination(pageSize, 0);
			PrepareQuery<T> prepareQuery = new PrepareQueryAdapter<T>(preparePredicate,prepareLimit);
			List<T> elementList = entityDao.find(prepareQuery);
			iteratorElement = elementList.iterator();
			cicle++;
		} catch (QueryException e) {
			throw new TableIterationException(e);
		}
		
	}

	public void remove() {
		throw new UnsupportedOperationException();
		
	}
}
