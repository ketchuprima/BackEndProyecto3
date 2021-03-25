package com.back.app.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailService{
	
	@Autowired
    private JavaMailSender emailSender;
	
	public void sendSimpleMessage(String to, String subject, String text, MultipartFile file) throws MessagingException {
		
		MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        helper.setTo(to); 
        helper.setSubject(subject); 
        helper.setText("El usuario se ha inscrito a su oferta. Adjunto su Curriculum Vitae", true);
        helper.addAttachment("CV_" + text + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") +1), file);
        
        emailSender.send(message);
		        
		        
	}

}
