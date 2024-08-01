package com.notify.demos.controller;


import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notify.demos.configuration.JwtTokenUtil;
import com.notify.demos.domain.JwtResponse;
import com.notify.demos.domain.Notification;
import com.notify.demos.mail.SendEmailImpl;
import com.notify.demos.service.NotificationRepository;



@RestController
@RequestMapping("/notify")
public class NotificationController {

	private static final Logger logger = LogManager.getLogger(NotificationController.class);
	@Autowired
    public SendEmailImpl emailService;
	
	@Autowired
    private NotificationRepository notificationRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type")
	@PostMapping(value = "/postEmailwithAttachment",
			consumes = "application/json", 
	        produces = "application/json")
	public ResponseEntity<JwtResponse> send(@RequestBody Notification notification, @RequestHeader("Authorization") String authorizationHeader, final BindingResult result ) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(new JwtResponse("Error....") , HttpStatus.BAD_REQUEST); 
        }
		logger.info("Request is received");
		try {
			Notification save = new Notification();
			save.setDescription(notification.getDescription());
			save.setEmail(notification.getEmail());
			save.setName(notification.getName());
			save.setNumber(notification.getNumber());
			save.setTimeRecieved(new Date());
			String token =  authorizationHeader.substring(7);
			logger.info("notifiction token: "+token);
	        String username =  this.jwtTokenUtil.getUsernameFromToken(token);
	        logger.info("notifiction username: "+username);
	        save.setClient(username);
			notificationRepository.save(save);
		} catch (Exception e) {			
		  return new ResponseEntity<>(new JwtResponse("Error servicing request....") , HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		 return new ResponseEntity<>(new JwtResponse("Sent message successfully Now....") , HttpStatus.OK);     
	}
}
