package cl.doman.db.model.table;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import cl.doman.db.model.table.StandardTable_;

@Generated(value="Dali", date="2014-07-18T16:36:04.839-0400")
@StaticMetamodel(FileTable.class)
public class FileTable_  extends StandardTable_{
	public static volatile SingularAttribute<FileTable, byte[]> content;
	public static volatile SingularAttribute<FileTable, String> filename;
	public static volatile SingularAttribute<FileTable, String> mimetype;
}
