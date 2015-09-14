package cl.doman.db.model.convert;



//import org.eclipse.persistence.internal.helper.ClassConstants;
//import org.eclipse.persistence.internal.helper.DatabaseField;
//import org.eclipse.persistence.mappings.DatabaseMapping;
//import org.eclipse.persistence.mappings.DirectCollectionMapping;
//import org.eclipse.persistence.mappings.converters.Converter;
//import org.eclipse.persistence.sessions.Session;
//import org.eclipse.persistence.mappings.converters.SerializedObjectConverter;
//import org.eclipse.persistence.mappings.foundation.AbstractDirectMapping;


//extends SerializedObjectConverter
public class MailConverter {
	
}
//public class MailConverter implements Converter{
//
//	   public Object convertDataValueToObjectValue(Object dataValue, Session session) {
//	        if (dataValue != null) {
//	            String sex = (String) dataValue;
//	        
//	            if (sex.equals("M")) {
//	                return "Male";
//	            } else {
//	                return "Female";
//	            }
//	        }
//	        
//	        return null;
//	    }
//	    
//	    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
//	        if (objectValue != null) {
//	            String sex = (String) objectValue;
//	        
//	            if (sex.equals("Made of testosterone")) {
//	                return "M";
//	            } else if (sex.equals("Made of estrogen")) {
//	                return "F";
//	            } else if (sex.equals("Male")) {
//	                return "M";
//	            } else {
//	                return "F";
//	            }
//	        }
//	        
//	        return null;
//	    }
//
//		public void initialize(DatabaseMapping mapping, Session session) {}
//	    
//		public boolean isMutable() {
//	        return false;
//	    }
//}
