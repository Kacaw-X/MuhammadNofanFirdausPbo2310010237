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
public class spdp {
    
    private String username = "root";
    private String password = "";
    private String dbname = "muhammadnofanfirdauspbo2310010273";
    private String url = "jdbc:mysql://localhost/" + dbname;
    private Connection koneksidb;

    // variabel penampung
    public String VARPASAL , VARTANGGAL, VARSTATUS, VARIDPGN = null; 
    public boolean validasi = false;
    
    public spdp() {
        try {
            Driver drvdb = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(drvdb);
            koneksidb = DriverManager.getConnection(url, username, password);
            System.out.println("Database Terhubung ✅");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void simpanSpdpPRE(String id,String pasal, String tanggal, String status, String id_pgn) {
        try {
            String sqlSave = "INSERT INTO tb_spdp (id_spdp,pasal, tanggal, status, id_pengguna) VALUES (?, ?, ?, ?, ?)";
            String cari = "SELECT * FROM tb_spdp WHERE id_pegawai = ?";
            PreparedStatement cekdata = koneksidb.prepareStatement(cari);
            cekdata.setString(1, id);
            ResultSet data = cekdata.executeQuery();

            if (data.next()) {
                this.VARPASAL = data.getString("pasal");
                this.VARTANGGAL = data.getString("tanggal");
                this.VARSTATUS = data.getString("status");
                this.VARIDPGN = data.getString("id pengguna");
                this.validasi = true;
                JOptionPane.showMessageDialog(null, "Data sudah ada di database ⚠️");
            } else {
                PreparedStatement perintah = koneksidb.prepareStatement(sqlSave);
                perintah.setString(1, id);
                perintah.setString(2, pasal);
                perintah.setString(3, tanggal);
                perintah.setString(4, status);
                perintah.setString(5, id_pgn);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan ✅");
                this.validasi = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    public void ubahSpdp(String id,String pasal, String tanggal, String status, String id_pgn) {
        try {
            String sqlEdit = "UPDATE pegawai SET pasal=?,tanggal=?,status=? WHERE id_spdp=?";
            PreparedStatement perintah = koneksidb.prepareStatement(sqlEdit);
            perintah.setString(1, pasal);
            perintah.setString(2, tanggal);
            perintah.setString(3, status);
            perintah.setString(4, id_pgn);            
            perintah.setString(5, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah ✏️");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    // Hapus data
    public void hapusSpdp(String id) {
        try {
            String sqlDelete = "DELETE FROM tb_spdp WHERE id_spdp=?";
            PreparedStatement perintah = koneksidb.prepareStatement(sqlDelete);
            perintah.setString(1, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus 🗑️");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    // Tampilkan data ke JTable
    public void tampilDataPegawai(JTable tbl, String sql) {
        try {
            Statement tampil = koneksidb.createStatement();
            ResultSet data = tampil.executeQuery(sql);
            DefaultTableModel modeltbl = new DefaultTableModel();
            ResultSetMetaData meta = data.getMetaData();
            modeltbl.getDataVector().clear();
            modeltbl.fireTableDataChanged();
            int kolom = meta.getColumnCount();

            modeltbl.addColumn("ID Spdp");
            modeltbl.addColumn("Pasal");
            modeltbl.addColumn("tanggal");
            modeltbl.addColumn("Status");
            modeltbl.addColumn("ID_pgn");

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

