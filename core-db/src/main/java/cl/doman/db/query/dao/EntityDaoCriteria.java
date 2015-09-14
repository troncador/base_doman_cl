package cl.doman.db.query.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.SingularAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.doman.db.QueryException;
import cl.doman.db.QueryExceptionKind;
import cl.doman.db.model.table.BaseTable;
import cl.doman.db.server.PersistenceUnitFactory;


public abstract class EntityDaoCriteria<T extends BaseTable<PK>,PK extends Serializable> implements EntityDao<T, PK>{
	static Logger log = LoggerFactory.getLogger(EntityDaoCriteria.class);
	protected Class< T > tclass ;
	
	
	
	//[start] OTHER
	abstract public SingularAttribute<? super T, PK> getPK();

	public EntityManager getEntityManager(){
		PersistenceUnitFactory server = PersistenceUnitFactory.getInstance();
		return server.getEntityManager();
	}
	
	public EntityDaoCriteria(Class<T> tclass) throws ClassCastException{
		this.tclass = tclass;
	}
	/**
	 * PUBLIC METHOD
	 */
	/*
	 * GET
	 */
	
	public T get(final PK id) throws QueryException {
		PreparePredicate<T> preparePredicate = getPreparePredicateByPK(id);
		return this.get(preparePredicate);
	}

	public T get(PreparePredicate<T> preparePredicate) throws QueryException{
		EntityManager entityManager = getEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tclass);
		Root<T> root = criteriaQuery.from(tclass);
		return getCore(preparePredicate,root,criteriaQuery,criteriaBuilder,entityManager);
	}
	
	public <O> O getColumn(final PK id, SingularAttribute<? super T , O> column, Class<O> oclass) throws QueryException {
		PreparePredicate<T> preparePredicate = getPreparePredicateByPK(id);
		return getColumn(preparePredicate,column,oclass);	

	}
	
	public <O> O getColumn(PreparePredicate<T> preparePredicate, SingularAttribute<? super T, O> column,Class<O> oclass) 
			throws QueryException {
		EntityManager entityManager = getEntityManager();
	
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<O> criteriaQuery = criteriaBuilder.createQuery(oclass);
		Root<T> root = criteriaQuery.from(tclass);
		
		Path<O> columnPath = root.get(column);
		criteriaQuery.select(columnPath);
		return getCore(preparePredicate,root,criteriaQuery,criteriaBuilder,entityManager);
	}
	
	/*
	 * EXIST
	 */
	
	public boolean exist(final PK id) throws QueryException{
		PreparePredicate<T> preparePredicate = getPreparePredicateByPK(id);
		return this.exist(preparePredicate);
	}
	
	
	public <P> boolean exist(final P value,final SingularAttribute<T,P> field) throws QueryException{
		PreparePredicate<T> preparePredicate = getPreparePredicateByField(value,field);
		return this.exist(preparePredicate);
	}
	
	
	public boolean exist(PreparePredicate<T> prepareQuery) throws QueryException{
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

			criteriaQuery.from(tclass);
			
			Subquery<T> subquery = criteriaQuery.subquery(tclass);
			Root<T> subroot = subquery.from(tclass);
			subquery.select(subroot);
			
			Predicate predicate = prepareQuery.getPredicate(subroot, criteriaBuilder);
			if(predicate != null){
				subquery.where(predicate);
			}
	
			Predicate predicateExists = criteriaBuilder.exists(subquery);
			
			Case<Long> booleancase = criteriaBuilder.<Long>selectCase();
			Expression<Long> booleanExpression = 
					booleancase.when(predicateExists,1l)
								.otherwise(0l);

			criteriaQuery.select(booleanExpression);
			TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery).setMaxResults(1);
			return typedQuery.getSingleResult()!=0;
		} catch (Exception e) {
			//Curiosamente cuando no hay filas en la tabla exist falla
			if(count()==0){
				return false;
			}
			throw new QueryException(e.getMessage(), e);
		} finally {
			entityManager.close();
		}
	}
	
	/*
	 * FIND
	 */
	
	public List<T> find() throws QueryException {
		PrepareQuery<T> prepareQuery = new PrepareQueryAdapter<T>();
		return find(prepareQuery);
	}
	

	
	public List<T> find(PrepareQuery<T> prepareQuery) throws QueryException{
		EntityManager entityManager = getEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tclass);
		Root<T> root = criteriaQuery.from(tclass);
		boolean cache;
		
		Selection<?>[] selectionArray = prepareQuery.getSelection(root);
		if(selectionArray == null){
			cache = true;
			criteriaQuery.select(root);
		} else {
			cache = false;
			criteriaQuery.multiselect(selectionArray);
		}
		return findCore(cache,prepareQuery,root,criteriaQuery,criteriaBuilder,entityManager);
	}

	
	/*
	 * findColumn
	 */
	
	public <O> List<O> findColumn(SingularAttribute<? super T, O> column,Class<O> oclass) throws QueryException {
		PrepareQuery<T> prepareQuery = new PrepareQueryAdapter<T>();
		return findColumn(prepareQuery,column,oclass);
	}
	 
	
	public <O> List<O> findColumn(PrepareQuery<T> prepareQuery, SingularAttribute<? super T, O> column,Class<O> oclass)
			throws QueryException {
		EntityManager entityManager = getEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<O> criteriaQuery = criteriaBuilder.createQuery(oclass);
		Root<T> root = criteriaQuery.from(tclass);
		Path<O> columnPath = root.get(column);
		criteriaQuery.select(columnPath);
		return findCore(false,prepareQuery,root,criteriaQuery,criteriaBuilder,entityManager);
	}
	/*
	 * findMap
	 */
	
	public <O> Map<PK,O> findMap(SingularAttribute<? super T, O> column,Class<O> oclass) throws QueryException{
		return 	findMap(new PrepareQueryAdapter<T>(),column,oclass);
	}
	
	
	public <O> Map<PK, O> findMap(PrepareQuery<T> prepareQuery, SingularAttribute<? super T, O> column,Class<O> oclass) throws QueryException{
		EntityManager entityManager = getEntityManager();
		
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
			Root<T> root = criteriaQuery.from(tclass);
			
			Path<O> columnPath = root.get(column);
			SingularAttribute<? super T, PK> pk = getPK();
			Path<PK> pkPath = root.get(pk);
			
			criteriaQuery.multiselect(pkPath,columnPath);
			
			List<Tuple> tupleList = findCoreTuple(prepareQuery,root,criteriaQuery,criteriaBuilder,entityManager);
			
			Map<PK,O> map = new HashMap<PK,O>(tupleList.size());
			for (Tuple tuple: tupleList){
				PK pkValue = (PK) tuple.get(pkPath);
				O columnValue = (O) tuple.get(columnPath);
				map.put(pkValue, columnValue);
			}
			return map;
	}  catch (Exception e) {
			throw new QueryException(e.getMessage(), e);
		}	 finally {
			entityManager.close();
		}
	}

	/**
	 * ********************************
	 *		 		TUPLAS
	 * ********************************
	 * TODO: revisar
	 * @throws QueryException 
	 */
//	
//	public <O> List<Tuple> findTuple(SingularAttribute<? super T, O> column,Class<O> oclass) throws QueryException {
//		return findTuple(new PrepareQueryAdapter<T>(),column, oclass);
//	}
//	
//	
//	public <O> List<Tuple> findTuple(PrepareQuery<T> prepareQuery, SingularAttribute<? super T, O> column,Class<O> oclass) throws QueryException {
//		EntityManager entityManager = getEntityManager();
//		
//		try {
//			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//			CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
//			Root<T> root = criteriaQuery.from(tclass);
//			
//			Path<O> columnPath = root.get(column);
//			SingularAttribute<? super T, PK> pk = getPK();
//			Path<PK> pkPath = root.get(pk);
//			
//			criteriaQuery.multiselect(pkPath,columnPath);
//			
//			return findCoreTuple(prepareQuery,root,criteriaQuery,criteriaBuilder,entityManager);
//	}  catch (Exception e) {
//			throw new QueryException(e.getMessage(), e);
//		}	 finally {
//			entityManager.close();
//		}
//	}

	
	
	


	private List<Tuple> findCoreTuple(PrepareQuery<T> prepareQuery,Root<T> root,
			CriteriaQuery<Tuple> criteriaQuery,CriteriaBuilder criteriaBuilder,EntityManager entityManager){
		if(prepareQuery!=null){
			
			Order[] orderArray = prepareQuery.getOrderArray(root, criteriaBuilder);
			if(orderArray != null){
				criteriaQuery.orderBy(orderArray);
			}
			
			Predicate predicate = prepareQuery.getPredicate(root, criteriaBuilder);
			
			if(predicate != null){
				criteriaQuery.where(predicate);
			}
			
			TypedQuery<Tuple> query = entityManager.createQuery(criteriaQuery);
			setLimits(prepareQuery, query);
			return query.getResultList();
		}else {
			TypedQuery<Tuple> query = entityManager.createQuery(criteriaQuery);
			return query.getResultList();
		}
	}
	
	
	/*
	 * COUNT
	 */
	
	/*
	 * CORE
	 */
	
	private <O> O getCore(PreparePredicate<T> prepareQuery,Root<T> root,
			CriteriaQuery<O> criteriaQuery,CriteriaBuilder criteriaBuilder,EntityManager entityManager) 
					throws QueryException{
		try {
			if(entityManager==null){
				throw new QueryException("entityManager==null");
			}
			TypedQuery<O> query;
			if(prepareQuery!=null){
				
				Predicate predicate = prepareQuery.getPredicate(root, criteriaBuilder);
				
				if(predicate != null){
					criteriaQuery.where(predicate);
				}
				query = entityManager.createQuery(criteriaQuery);
			}else {
				query = entityManager.createQuery(criteriaQuery);
				
			}
			if(query==null){
				throw new QueryException("TypedQuery==null");
			}
			return query.getSingleResult();
		} catch (NoResultException e) {
			String msg = String.format("Can't  find in database: %s", tclass.getName());
			throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
		} catch (NonUniqueResultException e) {
			String msg = String.format("More than %s entity in database", tclass.getName());
			throw new QueryException(QueryExceptionKind.NON_UNIQUE_RESULT, msg);
		} catch (Exception e) {
			throw new QueryException(e.getMessage(), e);
		} finally {
			entityManager.close();
		}
	}
	
	
	private PreparePredicate<T> getPreparePredicateByPK(final PK id){
		return  new PreparePredicate<T>(){
			public Predicate getPredicate(Root<T> root, CriteriaBuilder criteriaBuilder){
				SingularAttribute<? super T, PK> idAttribute = getPK();
				if(idAttribute == null){
					throw new RuntimeException("getPK == null");
				}
				Path<PK> path = root.get(idAttribute);
				return criteriaBuilder.equal(path,id);
			}
		};
	}
	private <O> PreparePredicate<T> getPreparePredicateByField(final O value,final SingularAttribute<T,O> field){
		return   new PreparePredicate<T>(){
			public Predicate getPredicate(Root<T> root, CriteriaBuilder criteriaBuilder){
				return criteriaBuilder.equal(root.get(field),value);
			}
		};
	}


	//[end] 

	//[start] SAVE
		
	public T save(T entity) throws QueryException{
		EntityManager entityManager = getEntityManager();
		try {
			EntityTransaction entityTransaction =  entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(entity);
			entityTransaction.commit();	
			return entity;
		} catch (PersistenceException e) {
			log.info(e.getMessage(),e);
			throw new QueryException(e.getMessage(), e);
		} catch (Exception e) {
			throw new QueryException(e.getMessage(), e);
		} finally {
			entityManager.close();
		}
	}
	
	public List<T> save(List<T> entityList) throws QueryException{
		EntityManager entityManager = getEntityManager();
		try {
			EntityTransaction entityTransaction =  entityManager.getTransaction();
			entityTransaction.begin();
			for(T entity: entityList){
				entityManager.persist(entity);
			}
			entityTransaction.commit();
			return entityList;
		} catch (PersistenceException e) {
			log.info(e.getMessage(),e);
			throw new QueryException(e.getMessage(), e);
		} catch (Exception e) {
			throw new QueryException(e.getMessage(), e);
		} finally {
			entityManager.close();
		}
	}
	//[end]

	//[start] EXIST
	
	//[end]
	//[start] UPDATE
	
	public List<T>  update(List<T> entityList) throws QueryException{
		List<T>  entityCommitedList = new ArrayList<T>();
		
		EntityManager entityManager = getEntityManager();
		try{
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			for( T entity: entityList){
				T entityCommited = entityManager.merge(entity);
				entityCommitedList.add(entityCommited);
			}
			entityTransaction.commit();
			return entityCommitedList;
		} catch (NoResultException e) {
			String msg = String.format("Can't  find in database: %s", tclass.getName());
			throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
		} catch (Exception e) {
			throw new QueryException(e.getMessage(), e);
		} finally{
			entityManager.close();
		}
	}
	
	
	public T update(T entity) throws QueryException{
		EntityManager entityManager = getEntityManager();
		try{
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			T entityCommited = entityManager.merge(entity);
			entityTransaction.commit();
			return entityCommited;  
		} catch (NoResultException e) {
			String msg = String.format("Can't  find in database: %s", tclass.getName());
			throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
		} catch (Exception e) {
			throw new QueryException(e.getMessage(), e);
		} finally{
			entityManager.close();
		}
	}
	//[end]
	//[start] DELETE
	
	public void delete(T entity) throws QueryException {
		EntityManager entityManager = getEntityManager();
		try{
			entityManager.getTransaction().begin();
			T entityAttached = entityManager.merge(entity);
			entityManager.remove(entityAttached);
			entityManager.getTransaction().commit();
		} catch (NoResultException e) {
			String msg = String.format("Can't  find in database: %s", tclass.getName());
			throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
		} catch (Exception e) {
			throw new QueryException(e.getMessage(),e);
		}  finally{
			entityManager.close();
		}
		
	}

	
	public void delete(List<T> entityList) throws QueryException {
		EntityManager entityManager = getEntityManager();
		try{
			entityManager.getTransaction().begin();
			for(T entity: entityList){
				T entityAttached = entityManager.merge(entity);
				entityManager.remove(entityAttached);
			}
			entityManager.getTransaction().commit();
		} catch (NoResultException e) {
			String msg = String.format("Can't  find in database: %s", tclass.getName());
			throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
		} catch (Exception e) {
			throw new QueryException(e.getMessage(),e);
		}  finally{
			entityManager.close();
		}
		
	}
	
	public void deleteByPK(PK id) throws QueryException {
		EntityManager entityManager = getEntityManager();
		try {
			T entity = entityManager.find(tclass,id);
			if(entity != null){
				entityManager.getTransaction().begin();
				entityManager.remove(entity);
				entityManager.getTransaction().commit();	
			}else{
				String msg = String.format("Can't  find in database: %s", tclass.getName());
				throw new QueryException(QueryExceptionKind.NO_RESULT, msg);
			}
		} catch (IllegalStateException e) {
			throw new QueryException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new QueryException(e.getMessage(), e);
		}	 finally{
			entityManager.close();
		}
	}
	
	
	public void deleteAll() throws QueryException {
		for(T t: this.find()){
			this.delete(t);
		}
		
	}
	//[end]
	//[start] FIND
	
//  IllegalStateException - if called for a Java Persistence query language UPDATE or DELETE statement 
//  QueryTimeoutException - if the query execution exceeds the query timeout value set and only the statement is rolled back 
//  TransactionRequiredException - if a lock mode has been set and there is no transaction 
//  PessimisticLockException - if pessimistic locking fails and the transaction is rolled back 
//  LockTimeoutException - if pessimistic locking fails and only the statement is rolled back 
//  PersistenceException - if the query execution exceeds the query timeout value set and the transaction is rolled back
	private <O> List<O> findCore(
			boolean maintainCache,
			PrepareQuery<T> prepareQuery,Root<T> root,
			CriteriaQuery<O> criteriaQuery,CriteriaBuilder criteriaBuilder,EntityManager entityManager) throws QueryException{
		try {
			if(prepareQuery!=null){
				
				Order[] orderArray = prepareQuery.getOrderArray(root, criteriaBuilder);
				if(orderArray != null){
					criteriaQuery.orderBy(orderArray);
				}
				
				Predicate predicate = prepareQuery.getPredicate(root, criteriaBuilder);
				
				if(predicate != null){
					criteriaQuery.where(predicate);
				}
				
				TypedQuery<O> query = entityManager.createQuery(criteriaQuery);
				if(!maintainCache){
					/**
					 * TODO: hacer más estandar y no dependiente de eclipse
					 */
					((org.eclipse.persistence.jpa.JpaQuery)query).getDatabaseQuery().dontMaintainCache();
				}
		
				setLimits(prepareQuery, query);
				return query.getResultList();
			}else {
				TypedQuery<O> query = entityManager.createQuery(criteriaQuery);
				return query.getResultList();
			}
		} catch (Exception e) {
			throw new QueryException(e.getMessage(), e);
		} finally {
			entityManager.close();
		}
	}
	
	 private <O> void setLimits(PrepareLimit limit,TypedQuery<O> query){
			Integer maxResults = limit.getMaxResults();
			if(maxResults != null){
				query.setMaxResults(limit.getMaxResults());
			}
			Integer firstResults = limit.getFirstResult();
			if(firstResults != null){
				query.setFirstResult(firstResults);
			}
	}
	

	


	
	public List<PK> findPK(PrepareQuery<T> prepareQuery,Class<PK> classPK) throws QueryException {
		SingularAttribute<? super T, PK> pk = getPK();
		return findColumn(prepareQuery, pk, classPK);
	}
	
	public List<PK> findPK(Class<PK> classPK) throws QueryException {
		SingularAttribute<? super T, PK> pk = getPK();
		return findColumn( pk, classPK);
	}
	//[end]
	//[start] COUNT
	/**
	 * :::::::::	COUNT		:::::::::
	 */
	
	public long count() throws QueryException {
		PrepareQuery<T> prepareQuery=null;
		return count(prepareQuery);
	}
	
	public long count(PreparePredicate<T> prepareQuery) throws QueryException {
		EntityManager entityManager = getEntityManager();
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tclass);
			Root<T> root = criteriaQuery.from(tclass);
			Expression<Long> expression = criteriaBuilder.count(root);
			CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
			cq.select( expression);
			if(prepareQuery!=null){
				Predicate predicate = prepareQuery.getPredicate(root, criteriaBuilder);
				if(predicate!=null){
					cq.where(predicate);
				}
			}
			TypedQuery<Long> typedQuery = entityManager.createQuery(cq);
			return typedQuery.getSingleResult();
		}catch (Exception e) {
			throw new QueryException(e.getMessage(), e);
		} finally {
			entityManager.close();
		}
	}

}