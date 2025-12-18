/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.Modul10;

/**
 *
 * @author Narita Risnawati
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiDB {
    private static Connection mysqlconfig;
    
    public static Connection configDB() throws SQLException {
        try {
            // URL Database (sesuaikan port jika bukan 3306)
            String url = "jdbc:mysql://localhost:3306/kampus_db";
            String user = "root"; // User default XAMPP
            String pass = "";     // Password default XAMPP (kosong)
            
            // Registrasi Driver MySQL
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            
            // Buat Koneksi
            mysqlconfig = DriverManager.getConnection(url, user, pass);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
        }
        return mysqlconfig;
    }
}
