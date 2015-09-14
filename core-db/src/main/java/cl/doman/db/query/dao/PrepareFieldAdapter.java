package cl.doman.db.query.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SingularAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrepareFieldAdapter<T> implements PrepareField<T> {
	static Logger log = LoggerFactory.getLogger(PrepareFieldAdapter.class);
	public Set<SingularAttribute<? super T,?>> fielSet = new HashSet<SingularAttribute<? super T,?>>();
	public Set<String> fielStringSet = new HashSet<String>();
	
	public PrepareFieldAdapter(){
		
	}
	
	public PrepareFieldAdapter(Set<SingularAttribute<? super T,?>> fielSet){
		this.fielSet = fielSet;
	}
	
	public void add(SingularAttribute<? super T,?> field){
		fielSet.add(field);
	}
	
	public void add(String field){
		fielStringSet.add(field);
	}
	
	public Selection<?>[] getSelection(Root<T> root) {
		Set<Selection<?>> set = new HashSet<Selection<?>>();
		
		for (SingularAttribute<? super T,?> attribute : fielSet){
			if(attribute == null){
				log.info("attr == null");
			}
			Path<?> path = root.get(attribute);
			set.add(path);
		}
		for (String attribute : fielStringSet){
			Path<?> path = root.get(attribute);
			set.add(path);
		}
		return set.toArray(new Selection<?>[set.size()]);
	}
}
