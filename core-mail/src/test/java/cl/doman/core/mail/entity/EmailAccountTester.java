package cl.doman.core.mail.entity;

import cl.doman.core.mail.EmailAccount;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class EmailAccountTester {

	@Test
	public void parser(){
		String local = "juan.perez_34";
		String domain = "alumnos.usm.cl";
		String mail = String.format("%s@%s", local,domain);
		EmailAccount emailAcount = new EmailAccount(mail);
		
		assertThat(domain, is(emailAcount.getDomain()));
		assertThat(local, is(emailAcount.getLocal()));
	}
	
	@Test(expected = NullPointerException.class) 
	public void parser2(){
		new EmailAccount(null);
	}
}
