package cl.doman.db.query.dao;

import java.util.Set;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

public interface PrepareField<T> {
	public Selection<?>[] getSelection(Root<T> root);
}
