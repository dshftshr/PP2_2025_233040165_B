/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.Modul06;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Narita Risnawati
 */
public class Latihan2 {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Konverter Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 150);
        
        frame.setLayout(new GridLayout(3, 2, 5, 5)); 

        JLabel labelCelcius = new JLabel("Celcius:");
        JTextField fieldCelcius = new JTextField();
        JLabel labelFahrenheit = new JLabel("Fahrenheit:");
        JLabel labelHasil = new JLabel(""); 
        JButton buttonKonversi = new JButton("Konversi");

        frame.add(labelCelcius);
        frame.add(fieldCelcius);
        
        frame.add(labelFahrenheit);
        frame.add(labelHasil);
        
        frame.add(buttonKonversi);
        
        ActionListener listener = new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                
                try {
                    String input = fieldCelcius.getText();
                    
                    double celcius = Double.parseDouble(input);
                    
                    double fahrenheit = (celcius * 9.0 / 5.0) + 32;
                    
                    labelHasil.setText(String.format("%.2f", fahrenheit));
                    
                } catch (NumberFormatException ex) {
                    labelHasil.setText("Input salah!");
                }
            }
        };

        buttonKonversi.addActionListener(listener);

        frame.setVisible(true);
    }
}
