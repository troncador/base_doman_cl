package cl.doman.base.initializator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.JoranException;

public class LoggerInitializator extends BaseSystemInitializator{
	private InputStream inputStream;
	private String filename="logback/logback.xml";
	
	
	public LoggerInitializator() throws IOException{
		String filePath = String.format("/%s",this.filename);
		
		String resourcePath =  this.getClass().getResource(filePath).getFile();
		File file = new File(resourcePath);
		
		if(!file.exists()){
			//TODO ver si el archivo no existe y lanzar warning
		//	throw new IOException("logback.xml don't find");
		}
		this.inputStream =  this.getClass().getResourceAsStream(filePath);
	}
	
	public LoggerInitializator(String filepath) throws IOException{
		File file = new File(filepath);
		if(!file.exists()){
			throw new IOException("logback.xml don't find");
		}
		this.inputStream = new FileInputStream(filepath);
	}
	
	public void init(XMLConfiguration globalConfiguration) throws SystemInitializatorException {
		
		try {
			LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(loggerContext);
			loggerContext.reset();
			
			Context context = configurator.getContext();
		
			String base = "log";
			String var[] = new String[]{"type","file","path"};
			for(String str :  var){
				String configurationPath = String.format("%s.%s", base,str);
				String value = globalConfiguration.getString(configurationPath);
				String variableName = String.format("config%s%s", base,str);
				System.out.println(String.format("%s => %s",variableName,value));
				context.putProperty(variableName,value);
				
			}
			configurator.doConfigure(this.inputStream);
			
		} catch (JoranException e) {
			System.out.println(e.getMessage());
			
			for( StackTraceElement a : e.getStackTrace()){
				System.out.println(a.toString());
			}
			throw new SystemInitializatorException(e.getMessage(),e);
		} 
	}

	public String getName() {
		return "init loggerConfiguration";
	}
}
