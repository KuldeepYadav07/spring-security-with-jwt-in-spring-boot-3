package com.scurity.JWT.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Employee {

    private String id;
    private String name;
    private String password;
    private String mobileNumber;
    private String roles;

}