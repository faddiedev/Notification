package com.notify.demos.mail;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class SendEmailImpl implements SendEmail{

	 @Autowired
	  private JavaMailSender emailSender;
	 @Autowired
	  private SpringTemplateEngine templateEngine;

	  public void sendSimpleMessage(String to, String from, String text,String name,int number,MultipartFile attachment)  throws MessagingException {
		  try {
				 MimeMessage message = emailSender.createMimeMessage();
	        	 
	        	 MimeMessageHelper helper = new MimeMessageHelper(message,
	                     MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
	                     StandardCharsets.UTF_8.name());
	        	 
	        	 Context context = new Context();	       	 
	             HashMap<String,Object> values = new HashMap<String,Object>(){{
	               put("client", "Client Surname");
	               put("name", name);
	               put("email", from);
	               put("number",number);
	               put("text", text); }};
	             context.setVariables(values);	             
	        	 String html = templateEngine.process("template", context);	        	 
	             helper.setTo(to);
	             helper.setText(html, true);
	             helper.setSubject("New client Request");
	             helper.setFrom(from);
	            /* if (attachment == null) {
	     			try {
	     				byte[] bytes = attachment.getBytes();
	     				String rootPath = System.getProperty("catalina.home");
	     				File dir = new File(rootPath + File.separator + "tmpFiles");
	     				if (!dir.exists()) {dir.mkdirs();}
	     				File serverFile = new File(dir.getAbsolutePath()
	     						+ File.separator + attachment.getOriginalFilename()+".docx");
	     				BufferedOutputStream stream = new BufferedOutputStream(
	     						new FileOutputStream(serverFile));
	     				stream.write(bytes);
	     				stream.close();
	     				helper.addAttachment(serverFile.getName(),serverFile );	     				
	     			} catch (Exception e) {
	     				System.out.println("You failed to upload " + name + " => " + e.getMessage());
	     			}	                          
		         
	             }  */	
	             emailSender.send(message);
	        } catch (MessagingException exception) {
	            exception.printStackTrace();
	            throw new MessagingException();
	        }
	    }
	  
}
