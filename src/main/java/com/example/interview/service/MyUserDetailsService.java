


package com.example.interview.service;

import com.example.interview.model.MyUserPrincipal;
import com.example.interview.model.Users;
import com.example.interview.repo.UserDetailsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserDetailsRepo userDetailsRepo;



public MyUserDetailsService(UserDetailsRepo userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userDetailsRepo.findByUserName(username);
        if(users == null) {
            throw  new UsernameNotFoundException("User not found");
        }
        return new MyUserPrincipal(users);
    }
}



