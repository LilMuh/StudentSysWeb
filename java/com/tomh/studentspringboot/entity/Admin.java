package com.tomh.studentspringboot.entity;

import javax.persistence.*;
import java.util.ArrayList;

@Table(name="admin")
public class Admin {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    /*
    username:
        1. Unique
        2. Length: 3~15
        3. Format: numbers + letters (Can't be pure numbers)
    password:
        1. Enter twice of password and make sure they are identical.
    workerID:
        1. Length: 18 digits
        2. Can't start with 0
        3. First 17 digits have to be pure numbers, and the last digit can be number / Letter X / Letter x
    phoneNumber:
        1. Length: 10 digits (Canada/American only -- Starts with 1) Ex. +1 314-456-62778
        2. Can't start with 0
        3. Must be pure numbers
     */
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "workerid")
    private String workerID;
    @Column(name = "phonenumber")
    private String phoneNumber;


    // Initialization
    public Admin() {}

    public Admin(String username, String password, String workerID, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.workerID = workerID;
        this.phoneNumber = phoneNumber;
    }

// Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorkerID() {
        return workerID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
