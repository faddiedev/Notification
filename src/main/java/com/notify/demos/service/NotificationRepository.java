package com.notify.demos.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.notify.demos.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	@Query("select n from notification n  where client in (select username from userapp where userId < 1000) and timeSent is null")
	Collection<Notification> getUnsentEmailsGrOne();
	
	@Query("select n from notification n  where client in (select username from userapp where userId > 1000 and userId < 20000) and timeSent is null")
	Collection<Notification> getUnsentEmailsGrTwo();
	
}
