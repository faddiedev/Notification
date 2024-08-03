package com.notify.demos.controller;

import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notify.demos.configuration.JwtTokenUtil;
import com.notify.demos.domain.JwtResponse;
import com.notify.demos.service.UserService;

@CrossOrigin(origins="*",maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class JwtAuthenticationController {

	private static final Logger logger = LogManager.getLogger(JwtAuthenticationController.class);

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;
	
	@CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type")
	@GetMapping(value = "/authenticate",
	        produces = "application/json")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestHeader("Authorization") String authorizationHeader) throws Exception {

		logger.info("Sending request for token");
		String credentials = authorizationHeader.substring("Basic ".length());
        String decodedCredentials = new String(Base64.getDecoder().decode(credentials));

        // Split the credentials into username and password
        String[] parts = decodedCredentials.split(":");
        String username = parts[0];
        String password = parts[1];
        logger.info("user:"+username+"user: "+ password);
		final UserDetails userDetails = this.userService.loadUserByUsernameAndPassword(username,password);
		 logger.info("userdet:"+ userDetails);
		final String token = this.jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<>(new JwtResponse(token) , HttpStatus.OK);
	}
}