/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.naes.main;

import com.naes.utils.AuthenticationService;

/**
 *
 * @author seany
 */
public class registerUser {
    public static void main(String[] args) {
        AuthenticationService authService = new AuthenticationService();
        authService.registerUser("naes1126", "seanyauder", "seanyauder1126@gmail.com");
    }
}
