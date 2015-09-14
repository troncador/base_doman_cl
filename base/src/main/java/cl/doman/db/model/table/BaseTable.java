package cl.doman.db.model.table;

public interface BaseTable<PK> {
	PK getId();
	void setId(PK id);
}