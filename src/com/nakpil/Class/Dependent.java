/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nakpil.Class;

/**
 *
 * @author HERU
 */
public class Dependent {
    
    private final String TRACE_ID;
    private String Name;
    private String Birth;
    
    public static final String TRACE = "TRACE";
    public static final String NAME = "FULLNAME";
    public static final String BIRTHDATE = "BIRTH";
    public static final String TABLE = "DEPENDENTS";
    
    public Dependent(String trace){
        this.TRACE_ID = trace;
    }
    
    public void setName(String s){
        this.Name = s;
    }
    
    public void setBirthDate(String s){
        this.Birth = s;
    }
    
    public String getTraceID(){
        return TRACE_ID;
    }
    
    public String getName(){
        return Name;
    }
    
    public String getBirth(){
        return Birth;
    }
    
    
}
