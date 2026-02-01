/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.naes.models;

/**
 *
 * @author seany
 */
public class User {
    
    private String username;
    private String hashedPassword;
    private String email;
    private String role;
    
    public User(String username, String hashedPassword, String email, String role) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.role = role;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getHashedPassword() {
        return hashedPassword;
    }
    
    private String getEmail() {
        return email.toLowerCase();
    }
    
    public String getRole() {
        return role.toLowerCase();
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
