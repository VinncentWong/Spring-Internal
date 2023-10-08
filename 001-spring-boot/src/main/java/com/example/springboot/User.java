package com.example.springboot;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class User {
    public String name;
    public String hostName;
    public String namePattern;
    public String environment;

    @PostConstruct
    public void postConstruct(){
        System.out.println("AFTER BEAN HAS BEEN CREATED");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("BEFORE BEAN DESTROYED :((((");
    }
}
