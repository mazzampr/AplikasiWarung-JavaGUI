package ProjectUTSBP2;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Koneksi {
    // Menyiapkan paramter JDBC untuk koneksi ke datbase
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/warung";
    static final String USER = "root";
    static final String PASS = "";
    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection koneksi;
    static Statement stmt;
    static ResultSet rs;    
    
    public static Connection getKoneksi() {
        if(koneksi == null) {
            try {
//                Class.forName(JDBC_DRIVER);
                koneksi = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Berhasil terhubung ke database");
            } catch(Exception e) {
                System.out.println("Error :"+e.getMessage());
            }
        }
        return koneksi;
    }
    
    public static void main(String[] args) throws SQLException {
        getKoneksi();
    }
}
