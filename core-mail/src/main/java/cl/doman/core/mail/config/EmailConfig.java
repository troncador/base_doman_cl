package cl.doman.core.mail.config;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import cl.doman.base.template.TemplateComposer;

public interface EmailConfig {
	
	void config(HtmlEmail htmlEmail) throws EmailException;
	
	TemplateComposer getEngine();

	
}
