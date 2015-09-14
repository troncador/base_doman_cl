package cl.doman.springmvc.datatable;

import javax.persistence.criteria.Selection;

public interface Field<T> {
	public Selection<?> get();
}
