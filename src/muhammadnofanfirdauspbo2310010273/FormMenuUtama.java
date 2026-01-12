package form;

import javax.swing.*;
import java.awt.*;

public class FormMenuUtama extends JFrame {

    private JButton btnPegawai, btnTersangka, btnSpdp, btnDetailSpdp, btnKeluar;
    private JLabel lblJudul;

    public FormMenuUtama() {
        setTitle("📋 Menu Utama Aplikasi SPDP");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Judul
        lblJudul = new JLabel("Aplikasi Manajemen SPDP", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblJudul.setBounds(50, 20, 300, 30);
        add(lblJudul);

        // Tombol menu
        btnPegawai = new JButton("👤 Data Pegawai");
        btnPegawai.setBounds(100, 70, 200, 35);
        add(btnPegawai);

        btnTersangka = new JButton("⚖️ Data Tersangka");
        btnTersangka.setBounds(100, 115, 200, 35);
        add(btnTersangka);

        btnSpdp = new JButton("📄 Data SPDP");
        btnSpdp.setBounds(100, 160, 200, 35);
        add(btnSpdp);

        btnDetailSpdp = new JButton("🗂️ Detail SPDP");
        btnDetailSpdp.setBounds(100, 205, 200, 35);
        add(btnDetailSpdp);

        btnKeluar = new JButton("❌ Keluar");
        btnKeluar.setBounds(100, 250, 200, 35);
        add(btnKeluar);

        // Aksi tombol
        btnPegawai.addActionListener(e -> new framePegawai().setVisible(true));
        btnTersangka.addActionListener(e -> new frameTersangka().setVisible(true));
        btnSpdp.addActionListener(e -> new frameSpdp().setVisible(true));
        btnDetailSpdp.addActionListener(e -> new frameDetailSpdp().setVisible(true));

        btnKeluar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin keluar?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}
