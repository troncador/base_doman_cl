package cl.doman.core.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import javax.mail.Authenticator;

import org.apache.commons.mail.DefaultAuthenticator;

import cl.doman.core.mail.config.EmailConfig;
import cl.doman.core.mail.template.EmailTemplateException;

public class EmailTestConfig {//implements EmailConfig{
	
//	static final private String PROPERTIES_FILE =  "javamail.properties";
//	
//	static final private EmailAccount emailAccount = new EmailAccount("maildetesta@gmail.com", "Punto de Encuentro");
//	static final private Authenticator authenticator = 
//			new DefaultAuthenticator("maildetesta@gmail.com","puntodeencuentro");
//	
//	static final private String ROOT_FOLDER = "META-INF/mail/";
//
//	private Properties properties;
//	
//	public EmailTestConfig() throws EmailTemplateException, IOException{
//		
//		this.properties = new Properties();
//		File propertiesFile = getResource(PROPERTIES_FILE);
//		
//		FileInputStream fileInputStream = new FileInputStream(propertiesFile);
//		properties.load(fileInputStream);
//		
//	}
//	
//	protected File getResource(String filename) throws EmailTemplateException{
//		try {
//			URL url = this.getClass().getClassLoader().getResource(ROOT_FOLDER+filename); 
//			URI uri = url.toURI();
//			return new File(uri);
//		} catch (URISyntaxException e) {
//			throw new EmailTemplateException(e.getMessage(),e);
//		}
//		
//	}
//	
//	@Override
//	public Authenticator getAuthenticator() {
//		return authenticator;
//	}
//
//	@Override
//	public EmailAccount getSenderEmailAccount() {
//		return emailAccount;
//	}
//
//	@Override
//	public Properties getProperties() {
//		return properties;
//	}
	
}
