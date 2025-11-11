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
public class detailSpdp {
 
    private String username = "root";
    private String password = "";
    private String dbname = "muhammadnofanfirdauspbo2310010273";
    private String url = "jdbc:mysql://localhost/" + dbname;
    private Connection koneksidb;

    // variabel penampung
    public String VARSPDP , VARPGW = null; 
    public boolean validasi = false;
    
    public detailSpdp() {
        try {
            Driver drvdb = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(drvdb);
            koneksidb = DriverManager.getConnection(url, username, password);
            System.out.println("Database Terhubung ✅");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void simpanDetailSpdpPRE(String idDetail, String idSpdp, String idPegawai) {
        try {
            String sqlSave = "INSERT INTO tb_detailspd (id_detail_spdp,id_spdp, id_pegawai) VALUES (?, ?, ?)";
            String cari = "SELECT * FROM tb_detail_spdp WHERE id_detail_spdp = ?";
            PreparedStatement cekdata = koneksidb.prepareStatement(cari);
            cekdata.setString(1, idDetail);
            ResultSet data = cekdata.executeQuery();

            if (data.next()) {
                this.VARSPDP = data.getString("id_detail_spdp");
                this.VARPGW = data.getString("id_pegawai");
                this.validasi = true;
                JOptionPane.showMessageDialog(null, "Data sudah ada di database ⚠️");
            } else {
                PreparedStatement perintah = koneksidb.prepareStatement(sqlSave);
                perintah.setString(1, idDetail);
                perintah.setString(2, idSpdp);
                perintah.setString(3, idPegawai);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan ✅");
                this.validasi = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    public void ubahDetailSpdp(String idDetail, String idSpdp, String idPegawai) {
        try {
            String sqlEdit = "UPDATE detailspdp SET id_spdp=?,id_pegawai=?, WHERE id_detail_spdp=?";
            PreparedStatement perintah = koneksidb.prepareStatement(sqlEdit);
            perintah.setString(1, idSpdp);
            perintah.setString(2, idPegawai);
            perintah.setString(3, idDetail);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah ✏️");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    // Hapus data
    public void hapusDetailSpdp(String id) {
        try {
            String sqlDelete = "DELETE FROM tb_detail_spdp WHERE id_detail_spdp=?";
            PreparedStatement perintah = koneksidb.prepareStatement(sqlDelete);
            perintah.setString(1, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus 🗑️");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    // Tampilkan data ke JTable
    public void tampilDetailSpdp(JTable tbl, String sql) {
        try {
            Statement tampil = koneksidb.createStatement();
            ResultSet data = tampil.executeQuery(sql);
            DefaultTableModel modeltbl = new DefaultTableModel();
            ResultSetMetaData meta = data.getMetaData();
            modeltbl.getDataVector().clear();
            modeltbl.fireTableDataChanged();
            int kolom = meta.getColumnCount();

            modeltbl.addColumn("ID Detail");
            modeltbl.addColumn("ID Spdp");
            modeltbl.addColumn("ID pegawai");

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

