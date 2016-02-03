/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.nakpil.Class.DataBridge;
import com.nakpil.Class.Dependent;
import com.nakpil.Class.Employee;
import com.nakpil.Class.Log;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HERU
 */
public class DataManager {
    
    private final DataBridge DB;
    private List<String> TMP_QUERYLIST;
    
    
    public DataManager(DataBridge db){
        this.DB = db;
    }
    
    public void loadDefaults(){
        try{
            String emp_Table = "CREATE TABLE IF NOT EXISTS "+Employee.TABLE+"("+Employee.TRACE+" VARCHAR(255),"
                +Employee.TIN+" VARCHAR(255),"+Employee.SURNAME+" VARCHAR(255),"+Employee.FIRSTNAME+" VARCHAR(255),"
                +Employee.MIDDLENAME+" VARCHAR(255),"+Employee.CIVILSTATUS+" VARCHAR(255),"+Employee.BIRTHDATE+" DATE,"
                +Employee.CONTACT+" VARCHAR(255))";
            String dep_Table = "CREATE TABLE IF NOT EXISTS "+Dependent.TABLE+"("+Dependent.TRACE+" VARCHAR(255),"
                +Dependent.NAME+" VARCHAR(255),"+Dependent.BIRTHDATE+" DATE)";
            String log_Table = "CREATE TABLE IF NOT EXISTS "+Log.TABLE+"("+Log.TRACE+" VARCHAR(255),"
                +Log.DESCRIPTION+" VARCHAR(255),"+Log.DATE+" DATE)";
            TMP_QUERYLIST = new ArrayList();
            TMP_QUERYLIST.add(emp_Table);
            TMP_QUERYLIST.add(dep_Table);
            TMP_QUERYLIST.add(log_Table);
            DB.RunBatchData(TMP_QUERYLIST);
        }catch(Exception er){
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, er);
        }
    }
    
    public boolean createEmployeeTable(){
        try {
            return  DB.RunScript("CREATE TABLE IF NOT EXISTS "+Employee.TABLE+"("+Employee.TRACE+" VARCHAR(255),"
                    +Employee.TIN+" VARCHAR(255),"+Employee.SURNAME+" VARCHAR(255),"+Employee.FIRSTNAME+" VARCHAR(255),"
                    +Employee.MIDDLENAME+" VARCHAR(255),"+Employee.CIVILSTATUS+" VARCHAR(255),"+Employee.BIRTHDATE+" DATE,"
                    +Employee.CONTACT+" VARCHAR(255))");
        } catch (SQLException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean createDependentsTable(){
        try{
            return  DB.RunScript("CREATE TABLE IF NOT EXISTS "+Dependent.TABLE+"("+Dependent.TRACE+" VARCHAR(255),"
                    +Dependent.NAME+" VARCHAR(255),"+Dependent.BIRTHDATE+" DATE)");
        } catch (Exception ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //EMPLOYEE DATA MANAGEMENT
    public boolean saveNewEmployee(Employee e){
        try{
            TMP_QUERYLIST = new ArrayList();
            TMP_QUERYLIST.add(e.getTraceID());
            TMP_QUERYLIST.add(e.getTIN());
            TMP_QUERYLIST.add(e.getSurname());
            TMP_QUERYLIST.add(e.getFirstname());
            TMP_QUERYLIST.add(e.getMiddlename());
            TMP_QUERYLIST.add(e.getStatus());
            TMP_QUERYLIST.add(e.getBirthDate());
            TMP_QUERYLIST.add(e.getContact());
            return DB.AddData(Employee.TABLE, TMP_QUERYLIST);
        }catch(Exception er){
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, er);
            return false;
        }
    }
    
    public boolean deleteEmployee(Employee e){
        try{
            String Dq = "DELETE FROM "+Employee.TABLE+" WHERE "+Employee.TRACE+" ='"+e.getTraceID();
            String Eq = "DELETE FROM "+Dependent.TABLE+" WHERE "+Dependent.TRACE+" ='"+e.getTraceID();
            TMP_QUERYLIST = new ArrayList();
            TMP_QUERYLIST.add(Eq);
            TMP_QUERYLIST.add(Dq);
            return DB.RunBatchData(TMP_QUERYLIST);
        }catch(Exception er){
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, er);
            return false;
        }
    }
    
    public List<List> getEmployees(){
        try{
            List<List> emps = DB.FetchTableCollection(Employee.TABLE);
            return emps;
        }catch (Exception ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList();
        }
    }
    
    public Properties getConfig(){
        try{
            
            return new Properties();
        }catch(Exception ex){
             Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void defaultConfig(){
        try{
            
        }catch(Exception ex){
             Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createDefaultConfig(){
        try{
            File config = new File(MainManager.ConfigFile);
            if(!config.exists()){
                config.createNewFile();
                
            }
        }catch(Exception ex){
             Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
