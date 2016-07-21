package fr.esgi.model;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class JobEmail implements Job {

    @Override
    public void execute(final JobExecutionContext ctx)
            throws JobExecutionException {

        System.out.println("Executing Job");
    	

		final String username = "lauctonflow@gmail.com";
		final String password = "Fucklapolice95";
    	
    	Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("juliien.decoen@gmail.com"));
			message.setSubject("BeYourCoach Alerte");
			message.setText("Alerte! n'oubliez pas de venir mettre a jour vos informations sur la plate-forme BeYourCoach.");

			Transport.send(message);
			
	        System.out.println("Alert sent successfully....");


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

    }

}