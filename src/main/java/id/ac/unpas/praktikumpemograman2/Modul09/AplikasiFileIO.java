/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.Modul09;

/**
 *
 * @author Narita Risnawati
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AplikasiFileIO extends JFrame {

    // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JButton btnSaveObject, btnLoadObject; // Latihan 3
    private JButton btnAppendText; // Latihan 4
    private JFileChooser fileChooser;

    public AplikasiFileIO() {
        super("Tutorial File IO & Exception Handling");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnAppendText = new JButton("Tambah Text (Append)"); // Latihan 4
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText); // Latihan 4
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);
        buttonPanel.add(btnSaveObject); // Latihan 3
        buttonPanel.add(btnLoadObject); // Latihan 3

        // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Handling ---

        // 1. MEMBACA FILE TEKS (Text Stream)
        btnOpenText.addActionListener(e -> bukaFileTeks());

        // 2. MENULIS FILE TEKS (Text Stream)
        btnSaveText.addActionListener(e -> simpanFileTeks());
        
        // 3. MENULIS FILE TEKS (APPEND)
        btnAppendText.addActionListener(e -> tambahFileTeks());

        // 4. MENULIS FILE BINARY (Byte Stream)
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());

        // 5. MEMBACA FILE BINARY (Byte Stream)
        btnLoadBinary.addActionListener(e -> muatConfigBinary());
        
        // 6. MENYIMPAN FILE OBJECT 
        btnSaveObject.addActionListener(e -> simpanConfigObject());
        
        // 7. MEMBACA FILE OBJECT
        btnLoadObject.addActionListener(e -> muatConfigObject());
   
        // Latihan 2 panggil method        
        muatLastNotes();
    }
   
    // Latihan 2
    private void muatLastNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("last_notes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
        }
    }
    
    // Contoh: Membaca File Teks dengan Try-Catch-Finally Konvensional
    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null; // Deklarasi di luar try agar bisa diakses di finally

            try {
                // Membuka stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); // Kosongkan area

                String line;
                // Baca baris demi baris
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            } finally {
                // Blok Finally: Selalu dijalankan untuk menutup resource
                try {
                    if (reader != null) {
                        reader.close(); // PENTING: Menutup stream
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Contoh: Menulis File Teks menggunakan Try-With-Resources (Lebih Modern)
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Try-with-resources otomatis menutup stream tanpa blok finally manual
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
            }
        }
    }

    // Contoh: Menulis Binary (Menyimpan ukuran font saat ini ke file .bin)
    private void simpanConfigBinary() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
            // Kita simpan ukuran font saat ini (Integer)
            int fontSize = textArea.getFont().getSize();
            dos.writeInt(fontSize);

            JOptionPane.showMessageDialog(this, "Ukuran font (" + fontSize + ") disimpan ke config.bin");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan binary: " + ex.getMessage());
        }
    }

    // Contoh: Membaca Binary (Mengambil ukuran font dari file .bin)
    private void muatConfigBinary() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
            // Membaca data integer mentah
            int fontSize = dis.readInt();

            // Terapkan ke aplikasi
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "Font diubah menjadi ukuran: " + fontSize);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File config.bin belum dibuat!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
        }
    }
    
    // Latihan 3
    private void simpanConfigObject() {
        // Minta input username dari user
        String username = JOptionPane.showInputDialog(this, "Masukkan Username untuk disimpan:");
        
        if (username != null && !username.isEmpty()) {
            // Ambil ukuran font saat ini
            int fontSize = textArea.getFont().getSize();
            
            // Buat objek UserConfig
            UserConfig config = new UserConfig(username, fontSize);
            
            // Simpan objek menggunakan ObjectOutputStream
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.cfg"))) {
                oos.writeObject(config); // Menulis objek utuh ke file
                JOptionPane.showMessageDialog(this, "Objek UserConfig berhasil disimpan!\n" + config.toString());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan objek: " + ex.getMessage());
            }
        }
    }

    // Latihan 3
    private void muatConfigObject() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.cfg"))) {
            // Baca objek dan casting kembali ke tipe UserConfig
            UserConfig config = (UserConfig) ois.readObject();
            
            // Terapkan konfigurasi dari objek
            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontSize()));
            
            JOptionPane.showMessageDialog(this, 
                "Objek berhasil dimuat!\nUsername: " + config.getUsername() + 
                "\nUkuran Font: " + config.getFontSize());
                
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "File user.cfg belum dibuat!");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat objek: " + ex.getMessage());
        }
    }
    
    // Latihan 4
    private void tambahFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // Perhatikan parameter 'true' pada FileWriter(file, true)
            // Ini mengaktifkan mode APPEND (menambah di akhir file)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "Teks berhasil ditambahkan (Append)!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan teks: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AplikasiFileIO().setVisible(true);
        });
    }
}
