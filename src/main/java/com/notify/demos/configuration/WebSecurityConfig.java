package com.notify.demos.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
        http.csrf(csrf->csrf.disable())
                .cors(cors->cors.disable())
                .authorizeHttpRequests(auth-> auth.requestMatchers(HttpMethod.OPTIONS,"/auth/authenticate").permitAll()
                		.requestMatchers("/auth/authenticate").permitAll()
                		.requestMatchers(HttpMethod.OPTIONS,"/notify/postEmailwithAttachment").permitAll()
                        .requestMatchers("/notify/postEmailwithAttachment").authenticated()
                        .anyRequest().authenticated())
                        .exceptionHandling(ex->ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
       
        return http.build();
    }
}