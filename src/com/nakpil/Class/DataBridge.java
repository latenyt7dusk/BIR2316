/*
 * Copyright (C) 2015 late7dusk
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.nakpil.Class;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.imageio.ImageIO;

/**
 *
 * @author Kelvin Nakpil
 */
public class DataBridge {

    private String SOURCE;
    private String USER;
    private String PASS;
    private int TYPE;

    public final static int ODBCJDBC = 0;
    public final static int MYSQL = 1;
    public final static int MSSQL = 2;
    public final static int H2 = 3;

    public static final String DEFAULT = "jdbc:h2:tcp://localhost/~/Nakpil Softwares/BIR Alphalist/database";

    private static final String DRIVER[] = {"sun.jdbc.odbc.JdbcOdbcDriver", "com.mysql.jdbc.Driver",
        "com.microsoft.sqlserver.jdbc.SQLServerDriver", "org.h2.Driver"};

    private Connection CONNECTION;
    private PreparedStatement PREPAREDST;
    private ResultSet RESULTS;
    private java.sql.Statement STATEMENT;
    private ByteArrayOutputStream output;
    private List<List> record;
    private List<String> Data;
    private String ST;
    private FileInputStream fis;
    private InputStream stream;
    private ByteArrayInputStream ba;
    private BufferedImage BUFFERED_IMAGE_2;
    private BufferedImage BUFFERED_IMAGE_1;
    private Graphics2D GRAPHICS_2D;
    private Map<String,Map<String,String>> COLLECTION;

    public DataBridge(Properties p) {
        this(p.getProperty("DATA_SOURCE"), p.getProperty("USER"),
                p.getProperty("PASSWORD"), Integer.parseInt(p.getProperty("TYPE")));
    }

    public DataBridge(String s, String u) {
        this(s, u, u);
    }

    public DataBridge(String s, String u, String p) {
        this(s, u, p, ODBCJDBC);
    }

    public DataBridge(String s, String u, String p, int t) {
        this.SOURCE = s;
        this.USER = u;
        this.PASS = p;
        this.TYPE = t;
        if (TYPE == H2) {
            this.SOURCE = SOURCE + ";CIPHER=AES";
            this.PASS = "dwr2rufd7ezj " + PASS;
        }
    }

    public boolean checkConnection() throws SQLException, ClassNotFoundException {
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER[TYPE]);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USER, PASS);
                return true;
            }
            return false;
        } catch (SQLException er) {
            return false;
        } finally {
            this.CONNECTION.close();
            this.CONNECTION = null;
            System.gc();
        }
    }

    public List<List> FetchTableCollection(String TB) throws SQLException {
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER[TYPE]);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USER, PASS);
                this.PREPAREDST = CONNECTION.prepareStatement("SELECT * FROM " + TB);
                this.RESULTS = PREPAREDST.executeQuery();

                record = new ArrayList();
                int column = RESULTS.getMetaData().getColumnCount();
                while (RESULTS.next()) {
                    String tList[] = new String[column];
                    int index = 0;
                    for (int i = 1; i <= column; i++) {
                        tList[index] = RESULTS.getString(i);
                        index++;
                    }
                    record.add(Arrays.asList(tList.clone()));
                }
                return record;
            }
            return record;
        } catch (SQLException | ClassNotFoundException er) {
            return null;
        } finally {
            if (CONNECTION != null) {
                RESULTS.close();
                PREPAREDST.close();
                CONNECTION.close();
                this.CONNECTION = null;
                System.gc();
            }
        }
    }
    
    public List<List> FetchTableCollection(String TB,String LB,String key) throws SQLException {
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER[TYPE]);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USER, PASS);
                this.PREPAREDST = CONNECTION.prepareStatement("SELECT * FROM " + TB +" WHERE "+LB+"='"+key+"'");
                this.RESULTS = PREPAREDST.executeQuery();

                record = new ArrayList();
                int column = RESULTS.getMetaData().getColumnCount();
                while (RESULTS.next()) {
                    String tList[] = new String[column];
                    int index = 0;
                    for (int i = 1; i <= column; i++) {
                        tList[index] = RESULTS.getString(i);
                        index++;
                    }
                    record.add(Arrays.asList(tList.clone()));
                }
                return record;
            }
            return record;
        } catch (SQLException | ClassNotFoundException er) {
            return null;
        } finally {
            if (CONNECTION != null) {
                RESULTS.close();
                PREPAREDST.close();
                CONNECTION.close();
                this.CONNECTION = null;
                System.gc();
            }
        }
    }
    
    public boolean hasDuplicate(String TB,String Col,String val) throws SQLException{
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER[TYPE]);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USER, PASS);
                this.PREPAREDST = CONNECTION.prepareStatement("SELECT "+Col+" FROM " + TB +" WHERE "+Col+"='"+val+"'");
                this.RESULTS = PREPAREDST.executeQuery();
                return RESULTS.next();
            }
            return false;
        } catch (SQLException | ClassNotFoundException er) {
            return false;
        } finally {
            if (CONNECTION != null) {
                RESULTS.close();
                PREPAREDST.close();
                CONNECTION.close();
                this.CONNECTION = null;
                System.gc();
            }
        }
    }
    
    public boolean AddData(String TB, List<String> data) throws SQLException {
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER[TYPE]);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USER, PASS);
                STATEMENT = CONNECTION.createStatement();
                
                ST = "INSERT INTO " + TB + " values('" + data.get(0) + "'";
                for (int i = 1; i < data.size(); i++) {
                    ST = ST.concat(",'" + data.get(i) + "'");
                }
                ST = ST.concat(")");
                STATEMENT.executeUpdate(ST);
                STATEMENT.close();
                return true;
            }
            return false;
        } catch (SQLException | ClassNotFoundException er) {
            return false;
        } finally {
            if (CONNECTION != null) {
                CONNECTION.close();
                this.CONNECTION = null;
            }
        }
    }

    public boolean RunScript(String data) throws SQLException {
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER[TYPE]);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USER, PASS);
                STATEMENT = CONNECTION.createStatement();
                STATEMENT.executeUpdate(data);
                STATEMENT.close();
                return true;
            }
            return false;
        } catch (SQLException | ClassNotFoundException er) {
            System.out.println(er);
            return false;
        } finally {
            if (CONNECTION != null) {
                CONNECTION.close();
                this.CONNECTION = null;
                System.gc();
            }
        }
    }

    public boolean RunBatchScript(List<String> d) throws SQLException {
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER[TYPE]);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USER, PASS);
                STATEMENT = CONNECTION.createStatement();
                if (d.size() > 0) {
                    for (String tmp : d) {
                        STATEMENT.addBatch(tmp);
                    }
                    STATEMENT.executeBatch();
                    STATEMENT.close();
                    return true;
                }

            }
            return false;
        } catch (SQLException | ClassNotFoundException er) {
            er.printStackTrace();
            return false;
        } finally {
            if (CONNECTION != null) {
                CONNECTION.close();
                this.CONNECTION = null;
                System.gc();
            }
        }
    }

    public boolean UpdateData(String TB, List<String> cols, List<String> data1, List<String> data2) throws SQLException {
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER[TYPE]);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USER, PASS);
                STATEMENT = CONNECTION.createStatement();

                ST = "update " + TB + " set " + cols.get(0) + " = '" + data2.get(0) + "'";

                for (int i = 1; i < data2.size(); i++) {
                    ST = ST.concat("," + cols.get(i) + " = '" + data2.get(i) + "'");
                }
                ST = ST.concat(" WHERE " + cols.get(0) + " = '" + data1.get(0) + "'");
                for (int i = 1; i < data1.size(); i++) {
                    ST = ST.concat("AND " + cols.get(i) + " = '" + data1.get(i) + "'");
                }
                STATEMENT.executeUpdate(ST);
                STATEMENT.close();
                return true;
            }
            return false;
        } catch (SQLException | ClassNotFoundException er) {
            return false;
        } finally {
            if (CONNECTION != null) {
                CONNECTION.close();
                this.CONNECTION = null;
                System.gc();
            }
        }
    }

    public List<String> FetchRowData(String TB, String id, String val) throws SQLException {
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER[TYPE]);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USER, PASS);
                this.PREPAREDST = CONNECTION.prepareStatement("SELECT * FROM " + TB + " WHERE " + id + " = '" + val + "'");
                this.RESULTS = PREPAREDST.executeQuery();

                int column = RESULTS.getMetaData().getColumnCount();
                String tList[] = {};
                while (RESULTS.next()) {
                    tList = new String[column];
                    int index = 0;
                    for (int i = 1; i <= column; i++) {
                        tList[index] = RESULTS.getString(i);
                        index++;
                    }
                }
                Data = Arrays.asList(tList);
            }
            return Data;
        } catch (ClassNotFoundException | SQLException er) {
            return Data;
        } finally {
            if (CONNECTION != null) {
                RESULTS.close();
                PREPAREDST.close();
                CONNECTION.close();
                this.CONNECTION = null;
                System.gc();
            }
        }
    }

    public boolean SaveFile(String TB, String ID, File f) throws SQLException, IOException {
        try {
            ST = "INSERT INTO " + TB + " VALUES( ?, ?)";
            if (CONNECTION == null) {
                Class.forName(DRIVER[TYPE]);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USER, PASS);
                this.PREPAREDST = CONNECTION.prepareStatement(ST);
                fis = new FileInputStream(f);
                PREPAREDST.setString(1, ID);
                PREPAREDST.setBinaryStream(2, fis, (int) f.length());
                PREPAREDST.executeUpdate();

            }

            return true;
        } catch (ClassNotFoundException | SQLException | FileNotFoundException er) {
            System.out.println(er);
            return false;
        } finally {
            if (CONNECTION != null) {
                this.PREPAREDST.close();
                this.CONNECTION.close();
                this.CONNECTION = null;
                if (fis != null) {
                    this.fis.close();
                }
                System.gc();
            }
        }
    }

    public BufferedImage getBufferedImage(String TB, String imgCol, String idLb, String ID) throws SQLException, IOException {
        output = new ByteArrayOutputStream();
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER[TYPE]);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USER, PASS);
                this.PREPAREDST = CONNECTION.prepareStatement("SELECT * FROM " + TB + " WHERE " + idLb + " = '" + ID + "'");
                this.RESULTS = PREPAREDST.executeQuery();
                RESULTS.next();
                stream = RESULTS.getBinaryStream(imgCol);
                int a1 = stream.read();
                while (a1 >= 0) {
                    output.write((char) a1);
                    a1 = stream.read();
                }

                ba = new ByteArrayInputStream(output.toByteArray());
                return ImageIO.read(ba);//Toolkit.getDefaultToolkit().createImage(output.toByteArray());

            }
            return null;
        } catch (ClassNotFoundException | SQLException er) {
            return null;
        } finally {
            if (CONNECTION != null) {
                RESULTS.close();
                PREPAREDST.close();
                CONNECTION.close();
                this.CONNECTION = null;
                if (stream != null) {
                    this.stream.close();
                }
                if (output != null) {
                    this.output.close();
                }
                System.gc();
            }
        }
    }

    public Image getImage(String TB, String imgCol, String idLb, String ID) throws SQLException, IOException {
        output = new ByteArrayOutputStream();
        try {
            if (CONNECTION == null) {
                Class.forName(DRIVER[TYPE]);
                this.CONNECTION = DriverManager.getConnection(SOURCE, USER, PASS);
                this.PREPAREDST = CONNECTION.prepareStatement("SELECT * FROM " + TB + " WHERE " + idLb + " = '" + ID + "'");
                this.RESULTS = PREPAREDST.executeQuery();
                RESULTS.next();
                stream = RESULTS.getBinaryStream(imgCol);
                int a1 = stream.read();
                while (a1 >= 0) {
                    output.write((char) a1);
                    a1 = stream.read();
                }

                return Toolkit.getDefaultToolkit().createImage(output.toByteArray());

            }
            return null;
        } catch (ClassNotFoundException | SQLException er) {
            return null;
        } finally {
            if (CONNECTION != null) {
                this.RESULTS.close();
                this.PREPAREDST.close();
                this.CONNECTION.close();
                this.CONNECTION = null;
                if (stream != null) {
                    this.stream.close();
                }
                if (output != null) {
                    this.output.close();
                }
                System.gc();
            }
        }
    }

    public File ResizeImageFile(File f, int w1, int h1) throws IOException {
        FileOutputStream tof = null;
        try {
            File to = File.createTempFile("tempResImage", ".tmp");
            tof = new FileOutputStream(to);
            BUFFERED_IMAGE_1 = ImageIO.read(f);
            BUFFERED_IMAGE_1 = resizeImage(BUFFERED_IMAGE_1, BUFFERED_IMAGE_1.getColorModel().getTransparency(), w1, h1);
            ImageIO.write(BUFFERED_IMAGE_1, "jpg", tof);

            return to;
        } catch (Exception er) {
            System.out.println(er);
            return null;
        } finally {
            if(tof != null){
                tof.close();
            }
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {
        BUFFERED_IMAGE_2 = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        GRAPHICS_2D = BUFFERED_IMAGE_2.createGraphics();
        GRAPHICS_2D.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        GRAPHICS_2D.dispose();

        return BUFFERED_IMAGE_2;
    }

}
