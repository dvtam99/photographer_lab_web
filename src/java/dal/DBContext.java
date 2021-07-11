package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TamDV
 */
public abstract class DBContext {


    /*
        get connection from database with username, password
     */
    protected Connection getConnection() throws Exception {
        String databaseName = "Photographer", username = "sa", password = "1234";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + databaseName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection cnn = DriverManager.getConnection(url, username, password);
        return cnn;
    }

    /*
        Close connection with database
     */
    protected void closeConnection(ResultSet rs, PreparedStatement ps, Connection cnn) throws Exception {
        try {
            if (rs != null) {
                if (!rs.isClosed()) {
                    rs.close();
                }
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                if (ps != null) {
                    if (!ps.isClosed()) {
                        ps.close();
                    }
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                if (cnn != null) {
                    if (!cnn.isClosed()) {
                        cnn.close();
                    }
                }
            }
        }
    }

}
