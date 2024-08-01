package com.notify.demos.scheduler;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.notify.demos.controller.JwtAuthenticationController;
import com.notify.demos.domain.Notification;
import com.notify.demos.mail.SendEmailImpl;
import com.notify.demos.service.NotificationRepository;
import com.notify.demos.service.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Component
public class ScheduledTask {

	private static final Logger logger = LogManager.getLogger(JwtAuthenticationController.class);
	
	@Autowired
	NotificationRepository notification;
	
	@Autowired
	UserRepository user;
	
	@Autowired
	SendEmailImpl emailService;
	
	@PersistenceContext
    EntityManager em;
    EntityManagerFactory emf;
	
	@Scheduled(fixedRate = 10000)
	protected void sendEmailsGrOne() {
	   
		Collection<Notification> notifications = notification.getUnsentEmailsGrOne();
		if(notifications.size() > 0) {
			for(Notification n : notifications) {
				try {
					String client_email = user.getUser(n.getClient()).getEmail();
					String client_name = user.getUser(n.getClient()).getName();
					emailService.sendSimpleMessage(client_name,client_email,n.getEmail(),
									 n.getDescription(),n.getName(),n.getNumber().toString(), null);
					logger.info("Email was sent for: "+n.getName());
					n.setTimeSent(new Date());
					notification.save(n);
					logger.info("Updated: "+n.getName());
				} catch (Exception e) {			
					logger.error("Error 1: ",e);
				}
			}
		}
	   

	}
	@Scheduled(fixedRate = 10000)
	protected void sendEmailsGrTwo() {
		Collection<Notification> notifications = notification.getUnsentEmailsGrTwo();
		if(notifications.size() > 0) {
			for(Notification n : notifications) {
				try {
					String client_email = user.getUser(n.getClient()).getEmail();
					String client_name = user.getUser(n.getClient()).getName();
					emailService.sendSimpleMessage(client_name,client_email,n.getEmail(),
									 n.getDescription(),n.getName(),n.getNumber().toString(), null);
					
					logger.info("Email was sent for: "+n.getName());
					notification.save(n);
					logger.info("Updated: "+n.getName());
				} catch (Exception e) {			
					logger.error("Error 2: ",e);
				}
			}
		}
	}
	
}
