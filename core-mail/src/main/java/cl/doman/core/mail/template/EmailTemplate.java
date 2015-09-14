package cl.doman.core.mail.template;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.doman.base.template.TemplateComposer;
import cl.doman.core.mail.EmailAccount;
import cl.doman.core.mail.config.EmailConfig;


public abstract class EmailTemplate {
	private static final transient Logger log =LoggerFactory.getLogger(EmailTemplate.class);
	
	private HtmlEmail email;
	private EmailConfig emailConfig;

	public EmailTemplate(EmailConfig emailConfig) throws EmailException, EmailTemplateException{
		this.emailConfig = emailConfig;
		email = new HtmlEmail();
	}
	
	
	final protected void construct() throws EmailException{
		/**
		 * Config
		 */
		emailConfig.config(email);
		
		/**
		 * Variables
		 */
		Map<String, ?> variableMap = getVariableMap();
		
		/**
		 * Subject
		 */
		String subject = getSubject();
		email.setSubject(subject);
		
		/**
		 * AttachFile
		 */
		for(AttachFile attachFile : getAttachFiles()){
			attachFile.attach(email);
		}

		/**
		 * Message
		 */
		TemplateComposer templateEngine = emailConfig.getEngine();
		String templateName = getTemplateName();
		
		String msg = templateEngine.process(variableMap,templateName);
		email.setMsg(msg);
		
	}
	
//			String cif = email.embed(embedFile);
//			for(String key : fileByString.keySet()){
//				File embedFile = fileByString.get(key);
//				String cif = email.embed(embedFile);
//				String cifDecorated = String.format("cid:%s", cif);
//				context.s
//				variablesMap.put(key,cifDecorated);
//			}


	
	public void addTo(EmailAccount emailAccount) throws EmailException{
		email.addTo(emailAccount.getMail(),emailAccount.getName());
	}
	
	public void addBcc(EmailAccount emailAccount) throws EmailException{
		email.addBcc(emailAccount.getMail(),emailAccount.getName());
	}
	
	public void addCc(EmailAccount emailAccount) throws EmailException{
		email.addCc(emailAccount.getMail(),emailAccount.getName());
	}
	
	public void send() throws EmailException{
		if(email.getToAddresses().size() == 1){
			InternetAddress emailStr =  (InternetAddress) email.getToAddresses().get(0);
			log.info(String.format("Mail Send:[%s - subject:%s]",emailStr.toString(),email.getSubject()));
		} else {
			int size = email.getToAddresses().size();
			log.info(String.format("Mail Send:[personNumber: %d - subject:%s]",size,email.getSubject()));
		}
		
		email.send();
	}
	
//	protected File getResource(String filename) throws EmailTemplateException{
//		try {
//			URL url = this.getClass().getClassLoader().getResource(ROOT_FOLDER+filename); 
//			URI uri = url.toURI();
//			return new File(uri);
//		} catch (URISyntaxException e) {
//			throw new EmailTemplateException(e.getMessage(),e);
//		}
//	}
	
	
	protected abstract Map<String,?> getVariableMap();
	protected abstract List<AttachFile> getAttachFiles();
	protected abstract String getSubject();
	protected abstract String getTemplateName();


}
