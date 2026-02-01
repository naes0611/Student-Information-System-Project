/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.naes.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author seany
 */
public class DBConnection {
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private static final String PROPERTIES_FILE = "/config.properties";
    
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;
    
    static {
        loadDatabaseProperties();
    }
    
    public static void loadDatabaseProperties() {
        
        Properties properties = new Properties();
        
        try (InputStream input = DBConnection.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                LOGGER.log(Level.SEVERE, "Unable to find " + PROPERTIES_FILE);
                throw new RuntimeException("Database configuration file not found");
            }
            
            properties.load(input);
            
            DB_URL = properties.getProperty("db.url");
            DB_USER = properties.getProperty("db.user");
            DB_PASSWORD = properties.getProperty("db.password");
            
            if (DB_URL == null || DB_USER == null || DB_PASSWORD == null) {
                throw new RuntimeException("Missing required database configuration properties");
            }
            
            LOGGER.log(Level.INFO, "Database configuration loaded successfully");
            
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading database properties", e);
            throw new RuntimeException("Failed to load database configuration", e);
        }
    }
    
    public static Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            LOGGER.log(Level.INFO, "Database connection successful");
            
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "MySQL JDBC Driver not found", e);
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Database connection error", e);
        }
        return connection;
    }
}
