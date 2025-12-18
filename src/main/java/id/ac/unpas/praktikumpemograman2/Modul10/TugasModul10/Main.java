/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.Modul10.TugasModul10;

/**
 *
 * @author Narita Risnawati
 */
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaModel model = new MahasiswaModel();
            MahasiswaView view = new MahasiswaView();
            new MahasiswaController(model, view);
            
            view.setVisible(true);
        });
    }
}
