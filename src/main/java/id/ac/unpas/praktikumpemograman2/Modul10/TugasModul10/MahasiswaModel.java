/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.Modul10.TugasModul10;

/**
 *
 * @author Narita Risnawati
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import id.ac.unpas.praktikumpemograman2.Modul10.KoneksiDB;

public class MahasiswaModel {
    
    public List<Mahasiswa> getAll() {
        List<Mahasiswa> listMhs = new ArrayList<>();
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");
            while (res.next()) {
                listMhs.add(new Mahasiswa(
                    res.getInt("id"),
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return listMhs;
    }

    public void insert(String nama, String nim, String jurusan) throws SQLException {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nama);
        pst.setString(2, nim);
        pst.setString(3, jurusan);
        pst.execute();
    }

    public void update(String nama, String nim, String jurusan) throws SQLException {
        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nama);
        pst.setString(2, jurusan);
        pst.setString(3, nim);
        pst.executeUpdate();
    }

    public void delete(String nim) throws SQLException {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nim);
        pst.execute();
    }
    
    // Latihan 3
    public List<Mahasiswa> cari(String keyword) {
        List<Mahasiswa> listMhs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                listMhs.add(new Mahasiswa(
                    res.getInt("id"),
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return listMhs;
    }

    // Latihan 4
    public boolean cekNimSudahAda(String nim) {
        try {
            String sql = "SELECT * FROM mahasiswa WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            ResultSet res = pst.executeQuery();
            return res.next();
        } catch (Exception e) {
            return false;
        }
    }
}
