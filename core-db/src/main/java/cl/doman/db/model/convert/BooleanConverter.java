package cl.doman.db.model.convert;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

public class BooleanConverter implements Converter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object convertObjectValueToDataValue(Object objectValue,
			Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object convertDataValueToObjectValue(Object dataValue,
			Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	public void initialize(DatabaseMapping mapping, Session session) {
		// TODO Auto-generated method stub
		
	}

}
