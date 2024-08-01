package com.notify.demos.mail;

import java.io.File;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

public interface SendEmail {

	void sendSimpleMessage(String client,String to,
            String from,
            String text,String name,String number,MultipartFile attach) throws MessagingException;
	
}
