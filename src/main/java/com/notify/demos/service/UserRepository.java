package com.notify.demos.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.notify.demos.domain.UserApp;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {

	UserApp findByUsername(String username);
	
	@Query("select u from userapp u where username = ?1 and password = ?2 ")
	UserApp findByUsernameAndPassword(String username,String password);
	
	@Query("select u from userapp u where username = ?1 ")
	UserApp getUser(String username);
}
