package db;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    //o Método getConnection usa as propetys dos métodos LoaProperties
    //passando o valor de iral para uma String e o resto dos valores vc envia diretamente como parametro
    //na função getConnection
    //conn = DriverManager.getConnection(String url,String user, String password) existe também este método
    //diferente do usado esse vc pode passar 3 strings que podem ser passadas igual feito com o url da debaixo
    public static Connection getConnection(){
        if(conn == null) {
            try {
                 Properties props = LoaProperties();
                 String url = props.getProperty("dburl");
                 //conn = DriverManager.getConnection(url, props);
                 conn = DriverManager.getConnection(props.getProperty("dburl"), props);
                 }
            catch (SQLException | FileNotFoundException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;

    } public static Connection getConnection2(){
        if(conn == null) {
            try {
                 Properties props = LoaProperties2();
                 String url = props.getProperty("dburl");
                 conn = DriverManager.getConnection(url, props);
                 }
            catch (IOException | SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }
    public static void closeConnection(Connection conn){
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    public static void CloseStatment(Statement st){
        if(st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    //Aqui temos dois métodos muito pareciddos porém um vc cria um file e ele é lido e tranformado em propetys
    //já no segundo vc coloca uma String por extenso e usa o StringReader para fazer essa leitura
    //OBS: Tanto o FileInputStream quanto o StringReader tem a pecilisaridade de ler linha por linha, seja do
    //aquivo ou da String. sendo assim no arquivo para ser reconhecido cada propety, vc terá que pular uma linha para cada
    //Já no StringReader vc precisa colocar a quebra de linhas "\n" pra ele reconhcer de forma individual
    private static Properties LoaProperties() throws FileNotFoundException {
        try(FileInputStream fs = new FileInputStream("C:\\Users\\mikel\\IdeaProjects\\MySQLConnectionSample\\MySQLData2\\src\\Db.propeties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }  private static Properties LoaProperties2() throws IOException {
        StringReader sr = new StringReader("user=developer\n" +
                "password=7504\n" +
                "dburl=jdbc:mysql://localhost:3306/coursejdbc\n" +
                "useSSL=false");
        Properties props = new Properties();
        props.load(sr);
        return props;
    }
}
