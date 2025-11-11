/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package muhammadnofanfirdauspbo2310010273;

import java.sql.Connection; 
import java.sql.Driver; 
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import javax.swing.JTable;
import java.sql.ResultSetMetaData;

/**
 *
 * @author LENOVO
 */
public class tersangka {
    
    private String username = "root";
    private String password = "";
    private String dbname = "muhammadnofanfirdauspbo2310010273";
    private String url = "jdbc:mysql://localhost/" + dbname;
    private Connection koneksidb;

    // variabel penampung
    public String VARNIK , VARNAMA, VARJENISKELAMIN, VARAGAMA, VARTANGGALLAHIR = null; 
    public boolean validasi = false;
    
    public tersangka() {
        try {
            Driver drvdb = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(drvdb);
            koneksidb = DriverManager.getConnection(url, username, password);
            System.out.println("Database Terhubung ✅");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void simpanTersangkaPRE(String id,String nik, String nama, String jeniskelamin, String agama, String tanggallahir) {
        try {
            String sqlSave = "INSERT INTO tb_tersangka (id_tersangka,nik, nama, jeniskelamin, agama, tanggallahir) VALUES (?, ?, ?, ?, ?, ?)";
            String cari = "SELECT * FROM tb_tersangka WHERE id_tersangka = ?";
            PreparedStatement cekdata = koneksidb.prepareStatement(cari);
            cekdata.setString(1, id);
            ResultSet data = cekdata.executeQuery();

            if (data.next()) {
                this.VARNIK = data.getString("nik");
                this.VARNAMA = data.getString("nama");
                this.VARJENISKELAMIN = data.getString("jeniskelamin");
                this.VARAGAMA = data.getString("agama");
                this.VARTANGGALLAHIR = data.getString("tanggallahir");
                this.validasi = true;
                JOptionPane.showMessageDialog(null, "Data sudah ada di database ⚠️");
            } else {
                PreparedStatement perintah = koneksidb.prepareStatement(sqlSave);
                perintah.setString(1, id);
                perintah.setString(2, nik);
                perintah.setString(3, nama);
                perintah.setString(4, jeniskelamin);
                perintah.setString(5, agama);
                perintah.setString(6, tanggallahir);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan ✅");
                this.validasi = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    public void ubahTersangka(String id,String nik, String nama, String jeniskelamin, String agama, String tanggallahir) {
        try {
            String sqlEdit = "UPDATE tersangka SET nik=?,nama=?,jeniskelamin=?,agama=?, tanggallahir=? WHERE id_tersangka=?";
            PreparedStatement perintah = koneksidb.prepareStatement(sqlEdit);
            perintah.setString(1, nik);
            perintah.setString(2, nama);
            perintah.setString(3, jeniskelamin);
            perintah.setString(4, agama);
            perintah.setString(5, tanggallahir);
            perintah.setString(6, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah ✏️");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    // Hapus data
    public void hapusTersangka(String id) {
        try {
            String sqlDelete = "DELETE FROM tb_pegawai WHERE id_pegawai=?";
            PreparedStatement perintah = koneksidb.prepareStatement(sqlDelete);
            perintah.setString(1, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus 🗑️");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    // Tampilkan data ke JTable
    public void tampilDataTersangka(JTable tbl, String sql) {
        try {
            Statement tampil = koneksidb.createStatement();
            ResultSet data = tampil.executeQuery(sql);
            DefaultTableModel modeltbl = new DefaultTableModel();
            ResultSetMetaData meta = data.getMetaData();
            modeltbl.getDataVector().clear();
            modeltbl.fireTableDataChanged();
            int kolom = meta.getColumnCount();

            modeltbl.addColumn("NIK");
            modeltbl.addColumn("NAMA");
            modeltbl.addColumn("JENISKELAMIN");
            modeltbl.addColumn("AGAMA");
            modeltbl.addColumn("TANGGALLAHIR");
            modeltbl.addColumn("ID");

            while (data.next()) {
                Object[] row = new Object[kolom];
                for (int i = 1; i <= kolom; i++) {
                    row[i - 1] = data.getObject(i);
                }
                modeltbl.addRow(row);
            }
            tbl.setModel(modeltbl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
}
