package com.example.interview.service;

import com.example.interview.model.Users;
import com.example.interview.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeThreadService implements Runnable{

    private Users users;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    public EmployeeThreadService(Users users) {
        this.users = users;
    }

    @Override
    public void run() {
        userDetailsRepo.save(users);
    }
}
