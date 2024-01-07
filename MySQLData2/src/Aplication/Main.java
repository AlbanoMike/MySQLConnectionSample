package Aplication;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat sfd = new SimpleDateFormat("dd//MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT TO seller (Name, Email, BaseSalary, DepartmentId VALUES "
            + "?,?,?,?,?");
            st.setString(1, "Carl Purple");
            st.setString(2, "Carl@gmail.com");
            st.setDate(3, new java.sql.Date(sfd.parse("22/04/1985").getTime()));
            st.setInt(5,4);

            int rowsaffected = st.executeUpdate();
        }
        catch (SQLException | ParseException e){
            e.printStackTrace();
        }
        finally {
            DB.CloseStatment(st);
            DB.getConnection();
        }
    }
}