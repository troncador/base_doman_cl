package cl.doman.resource.meta;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyObject {
	static Logger log =LoggerFactory.getLogger(PropertyObject.class);
	private Object object;
	private String property;
	private PropertyDescriptor propertyDescriptor;

	public PropertyObject(Object object, String property) throws PropertyObjectException{
		try {
			this.object = object;
			this.property = property;
			this.propertyDescriptor = new PropertyDescriptor(property, object.getClass());
		} catch (IntrospectionException e) {
			throw new PropertyObjectException(e.getMessage(),e);
		}
	}
	
	public PropertyDescriptor getPropertyDescriptor(){
		return propertyDescriptor;
	}
	
	public Object getObject(){
		return object;
	}
	
	
	public void setValue(Object value ) throws PropertyObjectException{
		try{
			PropertyUtils.setSimpleProperty( object , property, value);
		} catch (Exception e){
			throw new PropertyObjectException(e.getMessage(),e);
		}
	}
	
	public Object getValue() throws PropertyObjectException{
		try{
			return PropertyUtils.getSimpleProperty( object , property);
		} catch (Exception e){
			throw new PropertyObjectException(e.getMessage(),e);
		}
	}
}
