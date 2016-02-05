/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nakpil.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HERU
 */
public class Employee {
    
    private final String TRACE_ID;
    private String TIN_ID;
    private String Surname;
    private String Firstname;
    private String Middlename;
    private String Status;
    private String Birth;
    private String Contact;
    private String Address;
    private List<Dependent> Deps = new ArrayList();
    
    public static final String TABLE = "PROFILES";
    public static final String TRACE = "TRACE";
    public static final String TIN = "TIN";
    public static final String SURNAME = "SURNAME";
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String MIDDLENAME = "MIDDLENAME";
    public static final String CIVILSTATUS = "STATUS";
    public static final String BIRTHDATE = "BIRTH";
    public static final String CONTACT = "CONTACT";
    public static final String ADDRESS = "ADDRESS";
    
    public Employee(String trace){
        this.TRACE_ID = trace;
    }
    
    public void setTIN(String s){
        this.TIN_ID = s;
    }
    
    public void setSurname(String s){
        this.Surname = s;
    }
    
    public void setFirstname(String s){
        this.Firstname = s;
    }
    
    public void setMiddlename(String s){
        this.Middlename = s;
    }
    
    public void setStatus(String s){
        this.Status = s;
    }
    
    public void setBirth(String s){
        this.Birth = s;
    }
    
    public void setContact(String s){
        this.Contact = s;
    }
    
    public void setAddress(String s){
        this.Address = s;
    }
    
    public String getTraceID(){
        return TRACE_ID;
    }
    
    public String getTIN(){
        return TIN_ID;
    }
    
    public String getSurname(){
        return Surname;
    }
    
    public String getFirstname(){
        return Firstname;
    }
    
    public String getMiddlename(){
        return Middlename;
    }
    
    public String getStatus(){
        return Status;
    }
    
    public String getBirthDate(){
        return Birth;
    }
    
    public String getContact(){
        return Contact;
    }
    
    public String getAddress(){
        return Address;
    }
    
    public void setDependents(List<Dependent> d){
        this.Deps = d;
    }
    
    public void addDependent(Dependent d){
        this.Deps.add(d);
    }
    
    public String getEntry(){
        return "INSERT INTO "+TABLE+" VALUES('"+this.TRACE_ID+"','"+this.TIN_ID+"','"+this.Surname+"','"
                +this.Firstname+"','"+this.Middlename+"','"+this.Status+"','"+this.Birth+"','"+this.Contact
                +"','"+this.Address+"')";
    }
    
}
