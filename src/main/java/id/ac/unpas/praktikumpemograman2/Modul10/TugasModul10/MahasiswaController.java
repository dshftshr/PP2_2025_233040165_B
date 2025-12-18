/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.Modul10.TugasModul10;

/**
 *
 * @author Narita Risnawati
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;

public class MahasiswaController {
    private MahasiswaModel model;
    private MahasiswaView view;

    public MahasiswaController(MahasiswaModel model, MahasiswaView view) {
        this.model = model;
        this.view = view;

        // --- Event Listeners ---
        view.btnSimpan.addActionListener(e -> simpanData());
        view.btnEdit.addActionListener(e -> ubahData());
        view.btnHapus.addActionListener(e -> hapusData());
        view.btnClear.addActionListener(e -> kosongkanForm());
        view.btnCari.addActionListener(e -> cariData());
        
        // Listener Klik Tabel
        view.tableMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.tableMahasiswa.getSelectedRow();
                view.txtNama.setText(view.model.getValueAt(row, 1).toString());
                view.txtNIM.setText(view.model.getValueAt(row, 2).toString());
                view.txtJurusan.setText(view.model.getValueAt(row, 3).toString());
            }
        });

        // Load data awal
        loadData();
    }

    private void loadData() {
        updateTable(model.getAll());
    }

    private void cariData() { // Latihan 3
        updateTable(model.cari(view.getCariInput()));
    }

    private void updateTable(List<Mahasiswa> listMhs) {
        view.model.setRowCount(0); // Reset tabel
        int no = 1;
        for (Mahasiswa mhs : listMhs) {
            view.model.addRow(new Object[]{no++, mhs.getNama(), mhs.getNim(), mhs.getJurusan()});
        }
    }

    private void simpanData() {
        // Latihan 2
        if (view.getNama().trim().isEmpty() || view.getNim().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Data tidak boleh kosong!");
            return;
        }
        // Latihan 4
        if (model.cekNimSudahAda(view.getNim())) {
            JOptionPane.showMessageDialog(view, "NIM sudah terdaftar!");
            return;
        }

        try {
            model.insert(view.getNama(), view.getNim(), view.getJurusan());
            JOptionPane.showMessageDialog(view, "Data Berhasil Disimpan");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }

    private void ubahData() {
        try {
            model.update(view.getNama(), view.getNim(), view.getJurusan());
            JOptionPane.showMessageDialog(view, "Data Berhasil Diubah");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }

    private void hapusData() {
        try {
            model.delete(view.getNim());
            JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus");
            loadData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Error: " + e.getMessage());
        }
    }

    private void kosongkanForm() {
        view.txtNama.setText("");
        view.txtNIM.setText("");
        view.txtJurusan.setText("");
        view.txtCari.setText("");
    }
}
