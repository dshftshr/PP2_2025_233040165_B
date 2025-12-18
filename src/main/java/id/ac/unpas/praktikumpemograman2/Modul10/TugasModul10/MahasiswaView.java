/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.Modul10.TugasModul10;

/**
 *
 * @author Narita Risnawati
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MahasiswaView extends JFrame {
    JTextField txtNama, txtNIM, txtJurusan, txtCari;
    JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    JTable tableMahasiswa;
    DefaultTableModel model;

    public MahasiswaView() {
        setTitle("MVC Mahasiswa App");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Input
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panelForm.add(new JLabel("Nama:")); txtNama = new JTextField(); panelForm.add(txtNama);
        panelForm.add(new JLabel("NIM:")); txtNIM = new JTextField(); panelForm.add(txtNIM);
        panelForm.add(new JLabel("Jurusan:")); txtJurusan = new JTextField(); panelForm.add(txtJurusan);

        // Panel Tombol
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan"); panelTombol.add(btnSimpan);
        btnEdit = new JButton("Edit"); panelTombol.add(btnEdit);
        btnHapus = new JButton("Hapus"); panelTombol.add(btnHapus);
        btnClear = new JButton("Clear"); panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        // Tabel
        model = new DefaultTableModel(new String[]{"No", "Nama", "NIM", "Jurusan"}, 0);
        tableMahasiswa = new JTable(model);
        add(new JScrollPane(tableMahasiswa), BorderLayout.CENTER);

        // Panel Cari
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCari.add(new JLabel("Cari Nama:"));
        txtCari = new JTextField(20); panelCari.add(txtCari);
        btnCari = new JButton("Cari"); panelCari.add(btnCari);
        add(panelCari, BorderLayout.SOUTH);
    }
    
    public String getNama() { return txtNama.getText(); }
    public String getNim() { return txtNIM.getText(); }
    public String getJurusan() { return txtJurusan.getText(); }
    public String getCariInput() { return txtCari.getText(); }
}
