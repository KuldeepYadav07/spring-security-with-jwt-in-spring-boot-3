package com.scurity.JWT.service;

import com.scurity.JWT.exception.UserAlreadyFoundException;
import com.scurity.JWT.model.Employee;
import com.scurity.JWT.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String createUser (Employee employee){
        Employee userDetails= userRepo.findByMobileNumber(employee.getMobileNumber());
        if (userDetails ==null) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            userRepo.save(employee);
            return "User Created SuccessFully Thanks!";
        }
        throw new UserAlreadyFoundException("User is already present Please change the mobile");
    }

    public List<Employee> getUserDetails() {
        return userRepo.findAll();
    }

    public Optional<Employee> getUserById(String id) {
       Optional<Employee> userDetails = userRepo.findById(id);
        return userDetails;
    }
}
