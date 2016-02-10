/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.nakpil.Class.DataBridge;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.Cryptographer;

/**
 *
 * @author HERU
 */
public class MainManager {

    public static final String ConfigFile = System.getProperty("user.home") + File.separator + "Nakpil Softwares" + File.separator + "BIR Alphalist" + File.separator + "Config.def";
    public static DataManager DATAMAN;
    public static DataBridge TUNNEL;
    public static Properties Config;

    private static InputStream InStream;
    private static OutputStream OutStream;
    private static Properties Props;
    private static File FILE_HOLDER;
    private static File TEMP_FILE;
    private static org.h2.tools.Server LocalServer;

    public static void main(String[] args) {
        try {
            Props = getConfig();
            if (Props.getProperty("Server").isEmpty()) {
                LocalServer = org.h2.tools.Server.createTcpServer();
                LocalServer.start();
                TUNNEL = new DataBridge(Props.getProperty("Local"), Props.getProperty("User"), Props.getProperty("Password"), 3);
                DATAMAN = new DataManager(TUNNEL);
                DATAMAN.loadDefaults();
            } else {
                TUNNEL = new DataBridge(Props.getProperty("Server"), Props.getProperty("User"), Props.getProperty("Password"), 3);
                DATAMAN = new DataManager(TUNNEL);
            }
            
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            TUNNEL.advancedPreparedInsert();
            MainFrame MF = new MainFrame();
            MF.setVisible(true);

        } catch (Exception ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Properties getConfig() throws IOException {
        try {
            FILE_HOLDER = new File(MainManager.ConfigFile);
            TEMP_FILE = File.createTempFile("ncryptf", ".nsf");
            if (FILE_HOLDER.exists()) {
                Cryptographer.decrypt(Cryptographer.KEYPASS, FILE_HOLDER, TEMP_FILE);
                InStream = new FileInputStream(TEMP_FILE);
                Props = new Properties();
                Props.load(InStream);
                return Props;
            } else {
                createDefaultConfig();
                return Props;
            }
        } catch (Exception ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (InStream != null) {
                InStream.close();
            }
            if (TEMP_FILE != null) {
                TEMP_FILE.delete();
            }
        }
    }

    public static void createDefaultConfig() throws IOException {
        try {
            FILE_HOLDER = new File(MainManager.ConfigFile);
            TEMP_FILE = new File(MainManager.ConfigFile);
            if (!FILE_HOLDER.exists()) {
                FILE_HOLDER.getParentFile().mkdirs();
                FILE_HOLDER.createNewFile();
                OutStream = new FileOutputStream(FILE_HOLDER);
                Props = new Properties();
                Props.put("Local", DataBridge.DEFAULT);
                Props.put("Server", "");
                Props.put("User", "root");
                Props.put("Password", "dwr2rufd7ezj");
                Props.save(OutStream, "");
                Cryptographer.encrypt(Cryptographer.KEYPASS, FILE_HOLDER, TEMP_FILE);
            }
        } catch (Exception ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (OutStream != null) {
                OutStream.flush();
                OutStream.close();
            }
        }
    }

}
