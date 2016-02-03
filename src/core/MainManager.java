/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.nakpil.Class.DataBridge;
import java.io.File;

/**
 *
 * @author HERU
 */
public class MainManager {

    public static final String ConfigFile = System.getProperty("user.home")+File.separator+"Nakpil Softwares"+File.separator+"BIR Alphalist"+File.separator+"Config.def";
    public static DataManager DATAMAN;
    public static DataBridge TUNNEL;
    public static File ConfigXML;
    
    public static void main(String[] args) {
        
    }
    
}
