package com.notify.demos.controller;

import javax.mail.MessagingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notify.demos.domain.JwtResponse;
import com.notify.demos.domain.Notification;
import com.notify.demos.mail.SendEmailImpl;



@RestController
@RequestMapping("/notify")
public class NotificationController {

	private static final Logger logger = LogManager.getLogger(NotificationController.class);
	@Autowired
    public SendEmailImpl emailService;

	
	@PostMapping(value = "/postEmail")
	public String postEmailwithoutAttach(@ModelAttribute("notification") final Notification notification, final BindingResult result ) {
		if (result.hasErrors()) {
            return "error";
        }	
	       try {
			emailService.sendSimpleMessage("addie.keta@gmail.com",notification.getEmail(),
					 notification.getDescription(),notification.getName(),notification.getNumber(),null);
		} catch (MessagingException e) {			
			return e.toString();
		}

	        return "Sent message successfully....";
	     
	   }
	@CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type")
	@PostMapping(value = "/postEmailwithAttachment",
			consumes = "application/json", 
	        produces = "application/json")
	public ResponseEntity<JwtResponse> send(@RequestBody Notification notification, final BindingResult result ) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(new JwtResponse("Error....") , HttpStatus.BAD_REQUEST); 
        }
		logger.info("Request is received");
		try {
			int num = Integer.valueOf(notification.getNumber());
			emailService.sendSimpleMessage("fkerxhaliu@gmail.com",notification.getEmail(),
							 notification.getDescription(),notification.getName(),num, null);
		} catch (MessagingException e) {			
		  return new ResponseEntity<>(new JwtResponse("Error servicing request....") , HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		 return new ResponseEntity<>(new JwtResponse("Sent message successfully Now....") , HttpStatus.OK);     
	}
}
