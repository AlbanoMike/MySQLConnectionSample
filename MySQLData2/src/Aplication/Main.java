package Aplication;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

      try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO coursejdbc.seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES"
            + "(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "Carl Purple");
            st.setString(2, "Carl@gmail.com");
            st.setDate(3, new java.sql.Date(sfd.parse("22/04/1985").getTime()));
            st.setDouble(4, 3000);
            st.setInt(5,4);

            int rowsaffected = st.executeUpdate();
            if(rowsaffected > 0){
                    ResultSet rs = st.getGeneratedKeys();
                    while (rs.next()){
                        int id = rs.getInt(1);
                        System.out.println("Done, ID igual a: "+ id);
                    }
            }else {
                System.out.println("No rows affected");
            }
        }
        catch (SQLException | ParseException e){
            e.printStackTrace();
        }
        finally {
            DB.CloseStatment(st);
            DB.closeConnection(conn);
        }
    }
}
