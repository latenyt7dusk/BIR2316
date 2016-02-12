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
public class Account7_5 {
    
    private String Trace_ID;
    private String Surname,Firstname,Middlename;
    private String NT_Gross,NT_Basic_SMW,NT_Holiday,NT_Overtime,NT_NightDif,NT_Hazard,NT_13th,NT_Demi,NT_SSS,NT_Salary,NT_Compen;
    private String T_Gross,T_Basic_SMW,T_Holiday,T_Overtime,T_NightDif,T_Hazard,T_13th,T_Demi,T_SSS,T_Salary,T_Compen;
    
    public Account7_5(String trace){
        this.Trace_ID = trace;
    }
    public void setTrace(String s){
        this.Trace_ID = s;
    }
    public String getTrace(){
        return Trace_ID;
    }
    public void setSurname(String s){
        this.Surname = s;
    }
    public String getSurname(){
        return Surname;
    }
    public void setFirstname(String s){
        this.Firstname = s;
    }
    public String getFirstname(){
        return Firstname;
    }
    public void setMiddlename(String s){
        this.Middlename = s;
    }
    public String getMiddlename(){
        return Middlename;
    }
    public void setTaxableGrossCompensation(String s){
        this.T_Gross = s;
    }
    public String getTaxableGrossCompensation(){
        return T_Gross;
    }
    public void setTaxableBasicSMW(String s){
        this.T_Basic_SMW = s;
    }
    public String getTaxableBasicSMW(){
        return T_Basic_SMW;
    }
    public void setTaxableHolidayPay(String s){
        this.T_Holiday = s;
    }
    public String getTaxableHolidayPay(){
        return T_Holiday;
    }
    public void setTaxableOvertimePay(String s){
        this.T_Overtime = s;
    }
    public String getTaxableOvertimePay(){
        return T_Overtime;
    }
    public void setTaxableNightDifferentialPay(String s){
        this.T_NightDif = s;
    }
    public String getTaxableNightDifferentialPay(){
        return T_NightDif;
    }
    public void setTaxableHazardPay(String s){
        this.T_Hazard = s;
    }
    public String getTaxableHazardPay(){
        return T_Hazard;
    }
    public void setTaxable13thMonthPay(String s){
        this.T_13th = s;
    }
    public String getTaxable13thMonthPay(){
        return T_13th;
    }
    public void setTaxableDeminimisBenefits(String s){
        this.T_Demi = s;
    }
    public String getTaxableDeminimisBenefits(){
        return T_Demi;
    }
    public void setTaxableGovtContri(String s){
        this.T_SSS = s;
    }
    public String getTaxableGovtContri(){
        return T_SSS;
    }
    public void setTaxableSalary(String s){
        this.T_Salary = s;
    }
    public String getTaxableSalary(){
        return T_Salary;
    }
    public void setTaxableCompensation(String s){
        this.T_Compen = s;
    }
    public String getTaxableCompensation(){
        return T_Compen;
    }
    //Non Taxable
    public void setNonTaxableGrossCompensation(String s){
        this.NT_Gross = s;
    }
    public String getNonTaxableGrossCompensation(){
        return NT_Gross;
    }
    public void setNonTaxableBasicSMW(String s){
        this.NT_Basic_SMW = s;
    }
    public String getNonTaxableBasicSMW(){
        return NT_Basic_SMW;
    }
    public void setNonTaxableHolidayPay(String s){
        this.NT_Holiday = s;
    }
    public String getNonTaxableHolidayPay(){
        return NT_Holiday;
    }
    public void setNonTaxableOvertimePay(String s){
        this.NT_Overtime = s;
    }
    public String getNonTaxableOvertimePay(){
        return NT_Overtime;
    }
    public void setNonTaxableNightDifferentialPay(String s){
        this.NT_NightDif = s;
    }
    public String getNonTaxableNightDifferentialPay(){
        return NT_NightDif;
    }
    public void setNonTaxableHazardPay(String s){
        this.NT_Hazard = s;
    }
    public String getNonTaxableHazardPay(){
        return NT_Hazard;
    }
    public void setNonTaxable13thMonthPay(String s){
        this.NT_13th = s;
    }
    public String getNonTaxable13thMonthPay(){
        return NT_13th;
    }
    public void setNonTaxableDeminimisBenefits(String s){
        this.NT_Demi = s;
    }
    public String getNonTaxableDeminimisBenefits(){
        return NT_Demi;
    }
    public void setNonTaxableGovtContri(String s){
        this.NT_SSS = s;
    }
    public String getNonTaxableGovtContri(){
        return NT_SSS;
    }
    public void setNonTaxableSalary(String s){
        this.NT_Salary = s;
    }
    public String getNonTaxableSalary(){
        return NT_Salary;
    }
    public void setNonTaxableCompensation(String s){
        this.NT_Compen = s;
    }
    public String getNonTaxableCompensation(){
        return NT_Compen;
    }
    
}
