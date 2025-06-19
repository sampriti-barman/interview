

package com.example.interview.repo;

import com.example.interview.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<Users,Integer> {

    public Users findByUserName(String username);
}


