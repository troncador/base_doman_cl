package cl.doman.core.mail.template;

import javax.activation.DataSource;
import javax.mail.internet.MimeBodyPart;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AttachFile {
	static Logger log =LoggerFactory.getLogger(AttachFile.class);
	
	public enum Disposition{ATTACHMENT,INLINE,EMBED}
	private DataSource dataSource;
	private String description;
	private Disposition disposition = Disposition.ATTACHMENT;
	private String name;

	public AttachFile(DataSource dataSource,String name,String description){
		this.dataSource = dataSource;
		this.name = name;
		this.description = description;
	}
	public AttachFile(DataSource dataSource,String name,String description,Disposition disposition_){
		this.dataSource = dataSource;
		this.name = name;
		this.description = description;
		this.disposition = disposition_;

	}
	public void attach(HtmlEmail htmlEmail) throws EmailException{
		if(disposition==Disposition.EMBED){
			String cid = htmlEmail.embed(dataSource, name, name);
		} else {
			String dispositionstr = (Disposition.ATTACHMENT.equals(disposition))?
					MimeBodyPart.ATTACHMENT:MimeBodyPart.INLINE;
			htmlEmail.attach(dataSource, name, description, dispositionstr);
		}
		
		
	
		
	}
		
}
