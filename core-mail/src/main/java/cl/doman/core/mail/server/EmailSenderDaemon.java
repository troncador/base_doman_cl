package cl.doman.core.mail.server;


import java.util.List;

import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.doman.core.mail.EmailAccount;
import cl.doman.core.mail.template.EmailTemplate;

public class EmailSenderDaemon extends Thread{
	private static Logger log = LoggerFactory.getLogger(EmailSenderDaemon.class);
	
	public final static int DEFAULT_SLEEP_TIME = 60000;// UN MINUTO
	public final static int DEFAULT_MAX_AT_ONCE = 10; // 
	
	
	private EmailTemplate emailTemplate;
	private List<EmailAccount> receiversAccountsList;
	
	private long sleepTime;
	private int maxAtOnce;
	private int indexList;
	private boolean stopSendMail;
	
	public EmailSenderDaemon(
			EmailTemplate emailTemplate, 
			List<EmailAccount> receiversAccountsList){
		
		this.emailTemplate = emailTemplate;
		this.receiversAccountsList = receiversAccountsList;
		
		this.stopSendMail = false;
		
		this.setDaemon(true);
		
		this.indexList = 0;
		this.sleepTime = DEFAULT_SLEEP_TIME;
		this.maxAtOnce = DEFAULT_MAX_AT_ONCE ;
		
	}
	
	public void setSleepTime(int sleepTime){
		this.sleepTime = sleepTime;
	}
	
	public void setMaxAtOnce(int maxAtOnce){
		this.maxAtOnce = maxAtOnce;
	}
	
	@Override
	public void run(){
		
		while(!stopSendMail && (indexList < receiversAccountsList.size())){
			
			try {
				log.info("Se via correo a: " + receiversAccountsList.get(indexList).getMail());
				emailTemplate.addTo(receiversAccountsList.get(indexList));
				emailTemplate.send();
			} catch (EmailException e) {
				log.error("Error al enviar correo:", e.getMessage());
			}
			
			indexList++;
			
			//espera por un tiempo despues de enviar los correos de una secuencia
			if((maxAtOnce % indexList) == 0){
				try {
					Thread.sleep(this.sleepTime);
				} catch (InterruptedException e) {
					log.error("Sleep between mail interrupted: " + e.getLocalizedMessage());
				}
			}
		}
		//log.info("Fin del demonio de correos - Asunto: " +  emailTemplate.getSubject());
		
	}
	
	public void abort(){
		this.stopSendMail = true;
	}


}
