package com.bridgelabz.Util;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import javax.mail.Session;
import javax.mail.Transport;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Properties;

import com.bridgelabz.model.Mail;

@Service
public class EmailService{

	@Autowired
    private JavaMailSender emailSender;


	public void sendMail(String varificationLink, String subject, String email){
		Properties property = new Properties();
		property.put("mail.smtp.auth", "true");
		property.put("mail.smtp.starttls.enable", "true");
		property.put("mail.smtp.host", "smtp.gmail.com");
		property.put("mail.smtp.port", "587");

		String fromEmail ="pallavijha0722@gmail.com" ;      //System.getenv("email");
		String password =   "pallavi123456789";
        
        
        Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
        
    };


	Session session = Session.getInstance(property, auth);
	
	send(session, fromEmail, email, subject, varificationLink);
	
}

	private void send(Session session, String fromEmail, String emailContact, String emailSubject, String body) {
	
		try {

			MimeMessage mimeMessage = new MimeMessage(session);

		mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");

			mimeMessage.addHeader("format", "flowed");

			mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");

			mimeMessage.setFrom(new InternetAddress(fromEmail,"Pallavi"));

			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailContact));

			mimeMessage.setReplyTo(InternetAddress.parse(" u cannot pallavikumari2207@gmial.com",false));

			mimeMessage.setSubject(emailSubject);

			mimeMessage.setText(body);

			Transport.send(mimeMessage);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception occured while sending mail");

		}
	}
	
	public void sendSimpleMessage(final Mail mail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setTo(mail.getTo());
        message.setFrom(mail.getFrom());
        message.setSentDate(mail.getSentDate());
        emailSender.send(message);
    }
	}
