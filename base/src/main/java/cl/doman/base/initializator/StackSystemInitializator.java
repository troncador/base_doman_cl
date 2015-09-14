package cl.doman.base.initializator;

import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StackSystemInitializator implements SystemInitializator {
	private Logger log = LoggerFactory.getLogger(StackSystemInitializator.class);
	private SystemInitializator[] configurationArray;
	
	
	public StackSystemInitializator(SystemInitializator[] configurationArray){
		this.configurationArray = configurationArray;
	}
	
	final public void init(XMLConfiguration globalConfiguration) throws SystemInitializatorException{
		for(SystemInitializator configuration : configurationArray){
			Class<?> clazz = configuration.getClass();
			String classname = clazz.getSimpleName();
			String name = configuration.getName();
			
			try{
				configuration.init(globalConfiguration);
				String message = String.format("init SUCCESS: %s : %s", classname, name);
				log.info(message);
			} catch (SystemInitializatorException e){
				String message = String.format("init FAIL: %s : %s", classname, name);
				log.error(message);
				throw new SystemInitializatorException(e.getMessage(),e);
			}
		}
	}

	public String getName() {
		return "stack";
	}
}
