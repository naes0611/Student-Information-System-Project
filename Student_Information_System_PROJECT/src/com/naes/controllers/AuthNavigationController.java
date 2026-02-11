/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.naes.controllers;

import com.naes.main.MainForm;
import com.naes.views.AuthPanel;
import com.naes.views.LoginPanel;
import com.naes.views.RegisterPanel;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author seany
 */
public class AuthNavigationController {
    
    private static final Logger LOGGER = Logger.getLogger(AuthNavigationController.class.getName());
    
    private final AuthPanel authPanel;
    
    AuthNavigationController(AuthPanel authPanel) {
        this.authPanel = authPanel;
    }
    
    public void navigateToLogin() {
        LOGGER.info("Navigating to Login Panel");
        
        MainForm mainForm = (MainForm) SwingUtilities.getWindowAncestor(authPanel);
        if (mainForm != null) {
            mainForm.setView(new LoginPanel());
        }
    }
    
    public void navigateToRegister() {
        LOGGER.info("Navigating to Register Panel");
        
        MainForm mainForm = (MainForm) SwingUtilities.getWindowAncestor(authPanel);
        if (mainForm != null) {
            mainForm.setView(new RegisterPanel());
        }
    }
}
