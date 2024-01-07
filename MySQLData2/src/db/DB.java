package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection(){
        if(conn == null) {
            try {
                 Properties props = LoaProperties();
                 String url = props.getProperty("dburl");
                 conn = DriverManager.getConnection(url, props);
                 }
            catch (SQLException | FileNotFoundException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(){
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    private static Properties LoaProperties() throws FileNotFoundException {
        try(FileInputStream fs = new FileInputStream("db.propeties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }

    public static void CloseStatment(Statement st){
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void CloseResultSet(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
