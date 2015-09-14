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

public class TableIteratorFor<T> implements Iterator<T> {
	static Logger log = LoggerFactory.getLogger(TableIteratorFor.class);
	private int page = 0;
	
	
	private Iterator<T> iteratorElement;
	private int pageSize;
	private EntityDao<T, ?> entityDao;

	private PreparePredicate<T> preparePredicate;

	private long totalNumberPage;
	
	public TableIteratorFor(
			EntityDao<T, ?> entityDao,
			PreparePredicate<T> preparePredicate,
			int pageSize
			) throws QueryException{
		long length = entityDao.count(preparePredicate);
		this.totalNumberPage = length / pageSize+1;
		
		this.entityDao = entityDao;
		this.pageSize = pageSize;
		this.preparePredicate = preparePredicate;
		
		updateIterator();
	}

	private boolean hasOtherPage(){
		return page < totalNumberPage;
	}
	
	
	public boolean hasNext() {
		return iteratorElement.hasNext() || hasOtherPage();
	}
	
	public T next() {
		if(iteratorElement.hasNext()){
			return iteratorElement.next();
		}
		updateIterator();
		return iteratorElement.next();
	}

	private Date date;
	private void updateIterator() {
		if(date!=null){
			Date newDate = new Date();
			log.info(String.format("%04d/%04d  %04d[mS]",page,
					totalNumberPage,newDate.getTime()-date.getTime()));
			date = newDate;
		} else {
			date = new Date();
			log.info(String.format(" %d/%d",page,totalNumberPage));
		}
		try {
			PrepareLimit prepareLimit = new PrepareLimitPagination(pageSize, page);
			PrepareQuery<T> prepareQuery = new PrepareQueryAdapter<T>(preparePredicate,prepareLimit);
			List<T> elementList = entityDao.find(prepareQuery);
			iteratorElement = elementList.iterator();
			page++;
		} catch (QueryException e) {
			throw new TableIterationException(e);
		}
		
	}

	public void remove() {
		throw new UnsupportedOperationException();
		
	}
}
