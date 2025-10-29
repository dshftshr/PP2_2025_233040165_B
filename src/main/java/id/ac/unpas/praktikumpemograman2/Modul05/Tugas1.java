/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.Modul05;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 *
 * @author Narita Risnawati
 */
public class Tugas1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLayout");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // 1. Atur Layout Manager ke BorderLayout
                // Sebenarnya ini tidak perlu
                // Karena BorderLayout adalah Layout Manager default
                frame.setLayout(new BorderLayout());

                // 2. Buat komponen
                JLabel label = new JLabel("Label ada di Atas (NORTH)");
                JButton buttonSouth = new JButton("Tombol ada di Bawah (SOUTH)");
                JButton buttonNorth = new JButton("Tombol NORTH");
                JButton buttonWest = new JButton("Tombol WEST");
                JButton buttonEast = new JButton("Tombol EAST");
                JButton buttonCenter = new JButton("Tombol CENTER");

                // 3. Tambahkan Aksi (ActionListener) ke setiap tombol
                // Tombol SOUTH
                buttonSouth.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!");
                });

                // Tombol NORTH
                buttonNorth.addActionListener(e -> {
                    label.setText("Tombol di NORTH diklik!");
                });

                // Tombol WEST
                buttonWest.addActionListener(e -> {
                    label.setText("Tombol di WEST diklik!");
                });

                // Tombol EAST
                buttonEast.addActionListener(e -> {
                    label.setText("Tombol di EAST diklik!");
                });

                // Tombol CENTER
                buttonCenter.addActionListener(e -> {
                    label.setText("Tombol di CENTER diklik!");
                });

                // 4. Tambahkan komponen ke frame DENGAN POSISI
                frame.add(label, BorderLayout.NORTH);
                frame.add(buttonSouth, BorderLayout.SOUTH);
                frame.add(buttonWest, BorderLayout.WEST);
                frame.add(buttonEast, BorderLayout.EAST);
                frame.add(buttonCenter, BorderLayout.CENTER);

                // 5. Tampilkan frame
                frame.setVisible(true);
            }
        });
    }
}
