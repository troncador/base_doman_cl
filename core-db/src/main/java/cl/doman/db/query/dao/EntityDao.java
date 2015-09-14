 package cl.doman.db.query.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Tuple;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.SingularAttribute;

import cl.doman.db.QueryException;


public interface EntityDao<T, PK>{
	/*
	 * TODO:
	 *	List<T> findParcial(List<SingularAttribute<? super T, ?>> columnList, Class<O> oclass) throws QueryException;
	 */
	
	/*
	 * GET
	 */
	T get(PK id) throws QueryException;
	T get(PreparePredicate<T> prepareQuery) throws QueryException;
	<O> O getColumn(PreparePredicate<T> preparePredicate, SingularAttribute<? super T, O> column,Class<O> oclass) throws QueryException;
	<O> O getColumn(PK id, SingularAttribute<? super T, O> column,Class<O> oclass) throws QueryException;
	
	/*
	 * EXIST
	 */
	boolean exist(PK id) throws QueryException;
	boolean exist(PreparePredicate<T> prepareQuery) throws QueryException;
	<P> boolean exist(final P value,final SingularAttribute<T,P> field) throws QueryException;

	/*
	 * FIND
	 */
	List<T> find() throws QueryException;
	List<T> find(PrepareQuery<T> prepareQuery) throws QueryException;
	
	List<PK> findPK(Class<PK> classPK) throws QueryException;
	List<PK> findPK(PrepareQuery<T> prepareQuery,Class<PK> classPK) throws QueryException;
	/**
	 * findColumn
	 */
	<O> List<O> findColumn(SingularAttribute<? super T, O> column, Class<O> oclass) throws QueryException;
	<O> List<O> findColumn(PrepareQuery<T> prepareQuery, SingularAttribute<? super T, O> column,Class<O> oclass) throws QueryException;
	
	/**
	 * findMap
	 */
	<O> Map<PK, O> findMap(SingularAttribute<? super T, O> column, Class<O> oclass) throws QueryException;
	<O> Map<PK, O> findMap(PrepareQuery<T> prepareQuery, SingularAttribute<? super T, O> column,Class<O> oclass) throws QueryException;
	/**
	 * findTuple
	 */
//	<O> List<Tuple> findTuple(SingularAttribute<? super T, O> column, Class<O> oclass) throws QueryException;
//	<O> List<Tuple> findTuple(PrepareQuery<T> prepareQuery, SingularAttribute<? super T, O> column, Class<O> oclass) throws QueryException;
	
	/*
	 * COUNT
	 */
	long count() throws QueryException;
	long count(PreparePredicate<T> preparePredicate) throws QueryException;


	/*
	 * UPDATE
	 */
	List<T> update(List<T> tList)	throws QueryException;
	T update(T e) 			throws QueryException;
	
	/*
	 * SAVE
	 */
	T save(T e) 			throws QueryException;
	List<T> save(List<T> e) 	throws QueryException;
	
	/*
	 * DELETE
	 */
	void delete(T e) 		throws QueryException;
	void deleteByPK(PK e) 	throws QueryException;
	void deleteAll() 		throws QueryException;
	void delete(List<T> entityList) throws QueryException;
	
	
	
	//[start] prospectos
		//	 List<T> findAll(int start,int pageSize,String sortField, SortOrder sortOrder, Map<String, String> filters);
		//	 Long countByFilter(Map<String,String> filters);
	
		//Other
		//	void fill(EntityFiller<T> entityFiller);
	//[end] prospectos
}
