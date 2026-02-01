/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.naes.utils;

import com.naes.models.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author seany
 */
public class AuthenticationService {
    
    private static final Logger LOGGER = Logger.getLogger(AuthenticationService.class.getName());
    
    public AuthenticationResult registerUser(String username, String password, String email) {
        
        if (userExists(username)) {
            LOGGER.log(Level.WARNING, "Registration failed: Username already exists - {0}", username);
            return AuthenticationResult.failed("Username already exists");
        }
        
        String hashedPassword = hashPassword(password);
        if (insertUser(username, hashedPassword, email)) {
            LOGGER.log(Level.INFO, "User registered successfully - {0}", username);
            return AuthenticationResult.success("User registered successfully");
        } else {
            return AuthenticationResult.failed("Registration failed due to database error");
        }
    }
    
    public LoginResult loginUser(String username, String password) {
        User user = getUserByUsername(username);
        
        if (user == null) {
            LOGGER.log(Level.WARNING, "Login failed: User not found - {0}", username);
            return LoginResult.failed("Invalid username or password");
        }
        
        if (verifyPassword(password, user.getHashedPassword())) {
            LOGGER.log(Level.INFO, "User logged in successfully - {0}", username);
            return LoginResult.success("Login successful", user);
        } else {
            LOGGER.log(Level.WARNING, "Login failed: Incorrect password - {0}", username);
            return LoginResult.failed("Invalid username or password");
        }
    }
    
    public User getUserByUsername(String username) {
        String query = "SELECT username, password, email, role FROM users WHERE username = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("role")
                );
            }
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving user from database", e);
        }
        return null;
    }
    
    private boolean userExists(String username) {
        String query = "SELECT 1 FROM users WHERE username = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking if user exists", e);
            return true;
        }
        return false;
    }
    
    private boolean insertUser(String username, String hashedPassword, String email) {
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, email);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting user into database", e);
            return false;
        }
    }
    
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    
    private boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
