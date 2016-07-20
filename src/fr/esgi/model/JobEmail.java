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
    	
    	// Recipient's email ID needs to be mentioned.
        String to = "alexandre.lau@beyourcoach.com";

        // Sender's email ID needs to be mentioned
        String from = "admin@beyourcoach.com";

        // Assuming you are sending email from localhost
        String host = "smtp.beyourcoach.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

           // Set Subject: header field
           message.setSubject("BeYourCoach Alerte");

           // Now set the actual message
           message.setText("Alerte! n'oubliez pas de venir mettre a jour vos informations sur la plate-forme BeYourCoach.");

           // Send message
           Transport.send(message);
           System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
           mex.printStackTrace();
        }
        System.out.println("Executing Job");

    }

}