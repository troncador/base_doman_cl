package cl.doman.core.mail.config;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import cl.doman.base.initializator.SystemInitializator;
import cl.doman.base.initializator.SystemInitializatorException;
import cl.doman.base.template.TemplateComposer;
import cl.doman.core.mail.EmailAccount;

public abstract class EmailCustomConfig implements EmailConfig,  SystemInitializator{
	


	public abstract Properties getProperties();
	public abstract EmailAccount getSenderEmailAccount();
	public abstract Authenticator getAuthenticator();

	public void init(XMLConfiguration globalConfiguration)
			throws SystemInitializatorException {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void config(HtmlEmail htmlEmail) throws EmailException {
		EmailAccount emailAccount = getSenderEmailAccount();
		htmlEmail.setFrom(emailAccount.getMail(),emailAccount.getName());
		
		Properties properties = getProperties();
		Authenticator authenticator = getAuthenticator();
		Session session = Session.getInstance(properties, authenticator);
		htmlEmail.setMailSession(session);
	}
}
