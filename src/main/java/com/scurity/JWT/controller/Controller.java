package com.scurity.JWT.controller;

import com.scurity.JWT.model.Employee;
import com.scurity.JWT.service.JwtService;
import com.scurity.JWT.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private ProductService productService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping( value = {"/newUser"} ,consumes = "application/json")
    public ResponseEntity<String> updateUser(@RequestBody Employee user ){
        String update= productService.createUser(user);
        return new ResponseEntity<>(  update, HttpStatus.CREATED);
    }

    @GetMapping("/allUser")
    public ResponseEntity<List<Employee>> getAllUser(){
        List<Employee> users=  productService.getUserDetails();
        return new ResponseEntity<>(users ,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
   // @PreAuthorize("hasAuthority('Admin')")
    public Optional<Employee> getUserById(@PathVariable String id){
        Optional<Employee> user= productService.getUserById(id);
        return user;
    }
    @PostMapping("/auth")
    public String getAuthToken(@RequestBody Employee employee) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getName(), employee.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(employee.getName());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}