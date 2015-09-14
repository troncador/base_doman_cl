package cl.doman.base.properties;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.XMLConfiguration;

import cl.doman.base.initializator.LogBackConfigLoader;
import cl.doman.base.initializator.SystemInitializator;
import cl.doman.base.initializator.SystemInitializatorException;

public class PropertiesConfiguration implements SystemInitializator{
	private String filepath;
	private String id;
	
	static private Map<String,Properties> propertiesMap = new HashMap<String,Properties>();
	
	public static String get(String id, String name) throws PropertiesConfigurationException{
		try{
			Properties properties = propertiesMap.get(id);
			return properties.getProperty(name);
		} catch (Exception e){
			throw new PropertiesConfigurationException(e.getMessage(),e);
		}
	}
	
	public PropertiesConfiguration(String id,String filepath){
		this.id = id;
		this.filepath = filepath;
	}
	
	public void init(XMLConfiguration globalConfiguration) throws SystemInitializatorException {
		try {
			 FileInputStream fileInputStream 
				= new FileInputStream(filepath);
			 Properties properties = new Properties();
			 properties.load(fileInputStream);
			 propertiesMap.put(id, properties);
		} catch (Exception e) {
			throw new SystemInitializatorException(e.getMessage(),e);
		} 
	}

	public String getName() {
		return String.format("%s", filepath);
	}
}
