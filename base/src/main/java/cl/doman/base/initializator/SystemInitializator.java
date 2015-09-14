package cl.doman.base.initializator;

import org.apache.commons.configuration.XMLConfiguration;

public interface SystemInitializator {
	public void init(XMLConfiguration globalConfiguration) throws SystemInitializatorException;
	public String getName();
	//public boolean isRequired();
}
