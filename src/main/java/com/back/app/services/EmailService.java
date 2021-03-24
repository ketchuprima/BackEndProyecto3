package com.back.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService{
	
	@Autowired
    private JavaMailSender emailSender;
	
	public void sendSimpleMessage(String to, String subject, String text, String path) {
		        /*MimeMessage message = emailSender.createMimeMessage();
		        MimeMessageHelper helper = new MimeMessageHelper(message, true);
		        
		        helper.setTo(to); 
		        helper.setSubject(subject); 
		        helper.setText(text, true);
		        if(path != null) {
			        FileSystemResource file = new FileSystemResource(new File(path));
			        String nomDocu = path.substring(path.lastIndexOf("/") +1);
			        helper.addAttachment(nomDocu, file);
		        }
		        
		        emailSender.send(message);*/
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("noreply@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
		        
		        
	}

}
