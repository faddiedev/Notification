package com.notify.demos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.notify.demos.domain.UserApp;
import org.springframework.security.core.userdetails.User;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public  UserDetails loadUserByUsername(String username) {
        UserApp user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public  UserDetails loadUserByUsernameAndPassword(String username,String password) {
        UserApp user = this.userRepository.findByUsernameAndPassword(username,password);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
	
	
	
	
	/*
    private List<User>store=new ArrayList<>();

    public UserService(){
        store.add(new User(UUID.randomUUID().toString(),"Prathiksha",
                "abc"));
        store.add(new User(UUID.randomUUID().toString(),"Padmini Kini",
                "kinipadmini@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(),"Mahalasa Kini",
                "kinimahalasa@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(),"Gurudath Kini",
                "gurukini@gmail.com"));
    }

    public List<User>getUsers(){
        return this.store;
    }
}*/