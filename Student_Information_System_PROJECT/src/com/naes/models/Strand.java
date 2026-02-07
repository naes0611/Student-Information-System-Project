/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.naes.models;

/**
 *
 * @author seany
 */
public class Strand {
    
    private Integer strandId;
    private String strandCode;
    private String strandName;
    private String strandCategory;
    
    public Strand(String strandCode, String strandName, String strandCategory) {
        this.strandCode = strandCode;
        this.strandName = strandName;
        this.strandCategory = strandCategory;
    }
    
    public Integer getStrandId() {
        return strandId;
    }
    
    public String getStrandCode() {
        return strandCode;
    }
    
    public String getStrandName() {
        return strandName;
    }
    
    public String getStrandCategory() {
        return strandCategory;
    }
    
    public void setStrandId(Integer strandId) {
        this.strandId = strandId;
    }
    
    public void setStrandCode(String strandCode) {
        this.strandCode = strandCode;
    }
    
    public void setStrandName(String strandCode) {
        this.strandName = strandName;
    }
    
    public void setStrandCategory(String strandCode) {
        this.strandCategory = strandCategory;
    }
}
