
package com.example.interview.controller;

import com.example.interview.model.AuthenticationRequest;
import com.example.interview.model.AuthenticationResponse;
import com.example.interview.model.MyUserPrincipal;
import com.example.interview.service.EmployeeService;
import com.example.interview.model.Users;
import com.example.interview.service.MyUserDetailsService;
import com.example.interview.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/home")
    public String homePage() {
        return ("<h1>Hello</h1");
    }

    @PostMapping("/save")
    public Users save(@RequestBody Users  user) {
        return employeeService.save(user);
    }

    @GetMapping ("/saveThread")
    public String saveThread() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int j = 10; j < 20 ; j++) {
            Users user = new Users();
            user.setId(j);
            user.setUserName("Abc");
            user.setPassword("Abcd");
            executorService.submit(() -> employeeService.save(user));
        }
        executorService.shutdown(); // Gracefully shut down the executor after submitting tasks
        return "Users are being saved asynchronously!";

    }

    @GetMapping("/getAll")
    public List<Users> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/getByUserName")
    public Users getByUserName(@RequestParam String userName) {
        return employeeService.findById(userName);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
                    authenticationRequest.getPassword()));
        }catch (Exception exe){
            throw new Exception("Bad credentials");
        }
        UserDetails myUserPrincipal = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        String jwtToken = jwtUtil.generateToken(myUserPrincipal);
        return ResponseEntity.ok((new AuthenticationResponse(myUserPrincipal.getUsername(),myUserPrincipal.getPassword(), jwtToken)));
    }

}

