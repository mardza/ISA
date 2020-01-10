package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements MailServiceInterface {
	
	@Autowired
	private JavaMailSender javaMailSender;
	

	@Override
	public void sendMail(String address, String subject, String text) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(address);
		msg.setSubject(subject);
		msg.setText(text);
		javaMailSender.send(msg);
	}

}
