package com.example.demo.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleMailController {
	@Autowired
	private JavaMailSender sender;

	@GetMapping("/sendMail")
	public String sendMail() {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo("priyanjay.g1501@gmail.com");
			helper.setText("Greetings :)");
			helper.setSubject("Mail From Spring Boot");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
	}

	// sendMail with attachment
	@GetMapping("/sendMailWIthAttchment")
	public String sendMailWIthAttachment() {

		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setSubject("Mail from Spring Boot");
			helper.setText("Hello Spring Boot With Attachemnt");
			helper.setTo("priyanjay.g1501@gmail.com");
			helper.setFrom("priyanjay.g1501@gmail.com");
			helper.addAttachment("attachment.png", new ClassPathResource("attachment.png"));
			sender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		return "Mail Sent Success!";
	}
}
