package com.manage.orders.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Email {

	@Value("${spring.mail.username}")
	private String from;
	@Autowired
	private JavaMailSender send;
	private static final Logger logger = LogManager.getLogger(Email.class);
	public void send(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(this.from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		send.send(message);
		logger.info("[Email : " + to);
	}
}