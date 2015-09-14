package cl.doman.db.model.convert;


//import org.eclipse.persistence.mappings.converters.Converter;
//import org.eclipse.persistence.sessions.Session;


//extends SerializedObjectConverter

public abstract class AbstractConverter {
	
	
}
//public abstract class AbstractConverter implements Converter {
//
//	public abstract Convertable getConvertableEnum();
//
//	public Object convertDataValueToObjectValue(Object data, Session session) {
//		if (data == null) {
//			return getConvertableEnum();
//		}
//		Convertable convertableEnum = (Convertable) getConvertableEnum()
//				.getFromValue(data);
//		if (convertableEnum == null) {
//			throw new IllegalArgumentException(
//					"Data not with a value suitable got [" + data.getClass()
//							+ " : " + data + "] expected a valid value of ["
//							+ getConvertableEnum().getClass() + "]");
//		} else {
//			return convertableEnum;
//		}
//	}
//
//	public Object convertObjectValueToDataValue(Object data, Session session) {
//		if (data == null) {
//			return getConvertableEnum().convert();
//		}
//		if (data instanceof Convertable) {
//			return ((Convertable) data).convert();
//		}
//		throw new IllegalArgumentException("Data not of correct type got ["
//				+ data.getClass() + "] expected [Convertable]");
//	}
//	// left out the other methods for readibility
//}