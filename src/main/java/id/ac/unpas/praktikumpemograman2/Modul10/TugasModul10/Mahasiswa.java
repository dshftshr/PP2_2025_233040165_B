/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.Modul10.TugasModul10;

/**
 *
 * @author Narita Risnawati
 */
public class Mahasiswa {
    private int id;
    private String nama;
    private String nim;
    private String jurusan;

    public Mahasiswa(int id, String nama, String nim, String jurusan) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getNim() { return nim; }
    public String getJurusan() { return jurusan; }
}
