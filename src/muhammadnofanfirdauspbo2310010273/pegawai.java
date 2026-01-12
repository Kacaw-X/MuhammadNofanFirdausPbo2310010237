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
    import java.io.File;
    import net.sf.jasperreports.engine.JREmptyDataSource;
    import net.sf.jasperreports.engine.JRException;
    import net.sf.jasperreports.engine.JasperCompileManager; 
    import net.sf.jasperreports.engine.JasperFillManager; 
    import net.sf.jasperreports.engine.JasperPrint;
    import net.sf.jasperreports.engine.JasperReport;
    import net.sf.jasperreports.engine.design.JRDesignQuery; 
    import net.sf.jasperreports.engine.design.JasperDesign; 
    import net.sf.jasperreports.engine.xml.JRXmlLoader; 
    import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author LENOVO
 */
public class pegawai {

    private String username = "root";
    private String password = "";
    private String dbname = "muhammadnofanfirdauspbo2310010273";
    private String url = "jdbc:mysql://localhost/" + dbname;
    private Connection koneksidb;

    // variabel penampung
    public String VARNAMA , VARJABATAN, VARNIP, VARNRP = null; 
    public boolean validasi = false;
    
    public pegawai() {
        try {
            Driver drvdb = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(drvdb);
            koneksidb = DriverManager.getConnection(url, username, password);
            System.out.println("Database Terhubung ✅");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void simpanPegawaiPRE(String id,String nama, String jabatan, String nip, String nrp) {
        try {
            String sqlSave = "INSERT INTO tb_pegawai (id_pegawai,nama, jabatan, nip, nrp) VALUES (?, ?, ?, ?, ?)";
            String cari = "SELECT * FROM tb_pegawai WHERE id_pegawai = ?";
            PreparedStatement cekdata = koneksidb.prepareStatement(cari);
            cekdata.setString(1, id);
            ResultSet data = cekdata.executeQuery();

            if (data.next()) {
                this.VARNAMA = data.getString("nama");
                this.VARJABATAN = data.getString("jabatan");
                this.VARNIP = data.getString("nip");
                this.VARNRP = data.getString("nrp");
                this.validasi = true;
                JOptionPane.showMessageDialog(null, "Data sudah ada di database ⚠️");
            } else {
                PreparedStatement perintah = koneksidb.prepareStatement(sqlSave);
                perintah.setString(1, id);
                perintah.setString(2, nama);
                perintah.setString(3, jabatan);
                perintah.setString(4, nip);
                perintah.setString(5, nrp);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan ✅");
                this.validasi = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    public void ubahPegawai(String id,String nama, String jabatan, String nip, String nrp) {
        try {
            String sqlEdit = "UPDATE pegawai SET nama=?,jabatan=?,nip=?,nrp=? WHERE id_pegawai=?";
            PreparedStatement perintah = koneksidb.prepareStatement(sqlEdit);
            perintah.setString(1, nama);
            perintah.setString(2, jabatan);
            perintah.setString(3, nip);
            perintah.setString(4, nrp);
            perintah.setString(5, id);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah ✏️");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    // Hapus data
    public void hapusPegawai(String id) {
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
    public void tampilDataPegawai(JTable tbl, String sql) {
        try {
            Statement tampil = koneksidb.createStatement();
            ResultSet data = tampil.executeQuery(sql);
            DefaultTableModel modeltbl = new DefaultTableModel();
            ResultSetMetaData meta = data.getMetaData();
            modeltbl.getDataVector().clear();
            modeltbl.fireTableDataChanged();
            int kolom = meta.getColumnCount();

            modeltbl.addColumn("ID Pegawai");
            modeltbl.addColumn("NAMA");
            modeltbl.addColumn("Jabatan");
            modeltbl.addColumn("NIP");
            modeltbl.addColumn("NRP");

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
    
    public void tampilLaporan(String laporanFile, String SQL){
            try {
              File file = new File (laporanFile);
              JasperDesign jasDes = JRXmlLoader.load(file);

               JRDesignQuery sqlQuery = new JRDesignQuery();
               sqlQuery.setText(SQL);
               jasDes.setQuery(sqlQuery);

               JasperReport JR = JasperCompileManager.compileReport(jasDes);
               JasperPrint JP = JasperFillManager.fillReport(JR,null,this.koneksidb); 
               JasperViewer.viewReport(JP,false);
             } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.toString());       

             }
    }
    }