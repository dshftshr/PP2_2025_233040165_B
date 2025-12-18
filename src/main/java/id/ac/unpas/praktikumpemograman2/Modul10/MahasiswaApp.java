/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.Modul10;

/**
 *
 * @author Narita Risnawati
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MahasiswaApp extends JFrame {
    
    // Komponen GUI
    private JTextField txtNama, txtNIM, txtJurusan;
    private JTextField txtCari; // Tambahan Latihan 3
    private JButton btnSimpan, btnEdit, btnHapus, btnClear;
    private JButton btnCari;    // Tambahan Latihan 3
    private JTable tableMahasiswa;
    private DefaultTableModel model;

    public MahasiswaApp() {
        // Setup Frame
        setTitle("Aplikasi CRUD Mahasiswa JDBC");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Panel Form (Input Data)
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // Panel Tombol
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        // Gabungkan Panel Form dan Tombol di bagian Atas (NORTH)
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        // 2. Tabel Data (Menampilkan Data)
        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");

        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);
        
        // Latihan 3
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCari.setBorder(BorderFactory.createTitledBorder("Pencarian Data"));
        
        panelCari.add(new JLabel("Cari Nama:"));
        txtCari = new JTextField(20);
        panelCari.add(txtCari);
        
        btnCari = new JButton("Cari");
        panelCari.add(btnCari);
        
        add(panelCari, BorderLayout.SOUTH);

        // --- Event Listeners ---
        
        // Listener Klik Tabel (Untuk mengambil data saat baris diklik)
        tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMahasiswa.getSelectedRow();
                txtNama.setText(model.getValueAt(row, 1).toString());
                txtNIM.setText(model.getValueAt(row, 2).toString());
                txtJurusan.setText(model.getValueAt(row, 3).toString());
            }
        });

        // Aksi Tombol
        btnSimpan.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnClear.addActionListener(e -> kosongkanForm());
        
        // Latihan 3
        btnCari.addActionListener(e -> cariData());

        // Load data saat aplikasi pertama kali jalan
        loadData();
    }

    // 1. READ (Menampilkan Data)
    private void loadData() {
        model.setRowCount(0); // Reset tabel
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");

            int no = 1;
            while (res.next()) {
                model.addRow(new Object[] {
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Load Data: " + e.getMessage());
        }
    }

    // 2. CREATE (Menambah Data)
    private void tambahData() {
        
        // Latihan 2
        if (txtNama.getText().trim().isEmpty() || txtNIM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!");
            return;
        }
        
        // Latihan 4
        if (cekNimSudahAda(txtNIM.getText())) {
            JOptionPane.showMessageDialog(this, "NIM sudah terdaftar! Gunakan NIM lain.");
            return;
        }
        
        try {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNama.getText());
            pst.setString(2, txtNIM.getText());
            pst.setString(3, txtJurusan.getText());

            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
        }
    }

    // 3. UPDATE (Mengubah Data berdasarkan NIM)
    private void ubahData() {
        try {
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNama.getText());
            pst.setString(2, txtJurusan.getText());
            pst.setString(3, txtNIM.getText()); // Kunci update (WHERE)

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Edit: " + e.getMessage());
        }
    }

    // 4. DELETE (Menghapus Data)
    private void hapusData() {
        try {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNIM.getText());

            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage());
        }
    }
    
    // Latihan 3
    private void cariData() {
        model.setRowCount(0);
        try {
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, "%" + txtCari.getText() + "%");
            
            ResultSet res = pst.executeQuery();

            int no = 1;
            while (res.next()) {
                model.addRow(new Object[] {
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Pencarian Gagal: " + e.getMessage());
        }
    }
    
    // Latihan 4
    private boolean cekNimSudahAda(String nim) {
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

    private void kosongkanForm() {
        txtNama.setText(null);
        txtNIM.setText(null);
        txtJurusan.setText(null);
        txtCari.setText(null); // Latihan 3
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
    }
}
