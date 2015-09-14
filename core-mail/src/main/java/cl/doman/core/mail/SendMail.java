package cl.doman.core.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Authenticator;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;

import cl.doman.core.mail.config.EmailConfig;
import cl.doman.core.mail.config.EmailCustomConfig;
import cl.doman.core.mail.server.EmailSenderDaemon;
import cl.doman.core.mail.template.EmailTemplate;
import cl.doman.core.mail.template.EmailTemplateException;


public class SendMail {
//	public void mail(User user, String recoveryLink) throws FluxException {
//		try {
//			EmailConfig emailConfig = new EmailDefaultConfig();
//			EmailTemplate email = new EmailPassRecovery(recoveryLink,
//					emailConfig);
//			email.addTo(new EmailAccount(user.getMail(), user.getName()));
//			email.send();
//		} catch (EmailTemplateException e) {
//			throw new FluxException(FluxExceptionKind.INTERNAL_ERROR,
//					e.getMessage(), e);
//		} catch (IOException e) {
//			throw new FluxException(FluxExceptionKind.INTERNAL_ERROR,
//					e.getMessage(), e);
//		} catch (EmailException e) {
//			throw new FluxException(FluxExceptionKind.INTERNAL_ERROR,
//					e.getMessage(), e);
//		} catch (Exception e) {
//			throw new FluxException(FluxExceptionKind.UNSPECTED,
//					e.getMessage(), e);
//		}
//	}
//
//	public void a(User user) throws FluxException {
//		try {
//			EmailConfig emailConfig = new EmailDefaultConfig();
//			EmailTemplate email = new EmailSuccessRegister(user.getName(),
//					emailConfig);
//			email.addTo(new EmailAccount(user.getMail(), user.getName() + " "
//					+ user.getLastname()));
//			email.send();
//		} catch (EmailTemplateException e) {
//			throw new FluxException(FluxExceptionKind.INTERNAL_ERROR,
//					e.getMessage(), e);
//		} catch (IOException e) {
//			throw new FluxException(FluxExceptionKind.INTERNAL_ERROR,
//					e.getMessage(), e);
//		} catch (EmailException e) {
//			throw new FluxException(FluxExceptionKind.INTERNAL_ERROR,
//					e.getMessage(), e);
//		} catch (Exception e) {
//			throw new FluxException(FluxExceptionKind.UNSPECTED,
//					e.getMessage(), e);
//		}
//	}
//
//	public void sendMassiveMail(User userSender, ConfigMail configMail,
//			String subject, String message, List<User> userList)
//			throws FluxException {
//		// Email Account del que envia
//		try {
//			String fullname = String.format("%s %s", userSender.getName(),
//					userSender.getLastname());
//			EmailAccount senderEmailAccount = new EmailAccount(
//					userSender.getMail(), fullname);
//			// Authenticator del que envia
//			Authenticator senderAutheticator = new DefaultAuthenticator(
//					configMail.getUser(), configMail.getPassword());
//
//			// se genera el EmailConfig del que envia
//			EmailConfig senderEmailConfig = new EmailCustomConfig(
//					senderEmailAccount, senderAutheticator);
//
//			// Se genera el EmailTemplate
//			EmailToCampus emailToCampus = new EmailToCampus(senderEmailConfig,
//					subject, message);
//			// se obtienen los usuarios del campus
//
//			// se genera una lista de EmailConfig
//			List<EmailAccount> receiversEmailConfig = new ArrayList<EmailAccount>();
//			for (User user : userList) {
//				EmailAccount emailAccount = new EmailAccount(user.getMail(),
//						user.getName() + " " + user.getLastname());
//				receiversEmailConfig.add(emailAccount);
//			}
//
//			// se genera demonio que envia los correos a los usuarios
//			EmailSenderDaemon emailSenderDaemon = new EmailSenderDaemon(
//					emailToCampus, receiversEmailConfig);
//
//			emailSenderDaemon.start();
//		} catch (EmailException e) {
//			throw new FluxException(FluxExceptionKind.INTERNAL_ERROR,
//					e.getMessage(), e);
//		} catch (EmailTemplateException e) {
//			throw new FluxException(FluxExceptionKind.INTERNAL_ERROR,
//					e.getMessage(), e);
//		} catch (IOException e) {
//			throw new FluxException(FluxExceptionKind.INTERNAL_ERROR,
//					e.getMessage(), e);
//		} catch (Exception e) {
//			throw new FluxException(FluxExceptionKind.UNSPECTED,
//					e.getMessage(), e);
//		}
//	}
}
