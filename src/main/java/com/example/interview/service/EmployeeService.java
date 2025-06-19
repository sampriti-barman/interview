
package com.example.interview.service;

import com.example.interview.repo.UserDetailsRepo;
import com.example.interview.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class EmployeeService {

    @Autowired
    UserDetailsRepo userDetailsRepo;

    public Users save(Users users){
        if(userDetailsRepo.existsById(users.getId())) {
            throw new RuntimeException("User Id already exists");
        }
        return userDetailsRepo.save(users);
    }

    public List<Users> getAll() {
        List<Users> userList =  userDetailsRepo.findAll();
        if(userList.isEmpty()) {
            throw new NoSuchElementException("User List is empty");
        }
        return  userList;
    }

    public Users findById(String userName) {
        Users users =  userDetailsRepo.findByUserName(userName);
        if(Objects.isNull(users)) {
            throw new NoSuchElementException("User List is empty");
        }
        return  users;
    }
}

