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
public class Log {
    
    private final String TRACE_ID,DESC,TIMESTAMP;
    public static final String TABLE = "LOGS";
    public static final String TRACE = "TRACE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String DATE = "STAMP";
    
    public Log(String trace,String desc,String date){
        this.TRACE_ID = trace;
        this.DESC = desc;
        this.TIMESTAMP = date;
    }
    
    public String getTraceID(){
        return TRACE_ID;
    }
    
    public String getDescription(){
        return DESC;
    }
    
    public String getDate(){
        return TIMESTAMP;
    }
}
