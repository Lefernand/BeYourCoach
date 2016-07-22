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
        
        IUserManager userManager = new UserManagerDB();
        List<User> listUser = userManager.allUsers();
        
        for (int i = 0; i < listUser.size(); i++) {
			if (userManager.isPoidsDuJour(listUser.get(i).getId()).equals("pasok")) {
				System.out.println("envoie de mail a "+listUser.get(i).getEmail());
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
						InternetAddress.parse(listUser.get(i).getEmail()));
					message.setSubject("BeYourCoach Alerte");
					message.setText("Alerte! n'oubliez pas de venir mettre a jour vos informations sur la plate-forme BeYourCoach.");

					Transport.send(message);
					
			        System.out.println("Alert sent successfully....");


				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
			}else{
				System.out.println(listUser.get(i).getEmail() + "a bien set son poids aujourd'hui");
			}
		}

		

    }

}