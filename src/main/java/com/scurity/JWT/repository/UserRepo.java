package com.scurity.JWT.repository;

import com.scurity.JWT.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<Employee, String> {
    Employee findByMobileNumber(String mobileNumber);
    Optional<Employee> findByName(String username);
}