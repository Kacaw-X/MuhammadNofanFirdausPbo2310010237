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
public class pengguna {

    private String username = "root";
    private String password = "";
    private String dbname = "pbo2310010273";
    private String url = "jdbc:mysql://localhost/" + dbname;
    private Connection koneksidb;

    public pengguna() {
        try {
            Driver drvdb = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(drvdb);
            koneksidb = DriverManager.getConnection(url, username, password);
            System.out.println("Database terhubung ✅");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    // SIMPAN DATA PENGGUNA
    public void simpanPengguna(String id, String sandi, String nama, String pengguna, String peran, String foto) {
        try {
            String sql = "INSERT INTO tb_pengguna (id_pengguna, kata_sandi, nama, pengguna, peran) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = koneksidb.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, sandi);
            ps.setString(3, nama);
            ps.setString(4, pengguna);
            ps.setString(5, peran);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data pengguna berhasil disimpan ✅");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    // UBAH DATA
    public void ubahPengguna(String id, String sandi, String nama, String pengguna, String peran, String foto) {
        try {
            String sql = "UPDATE tb_pengguna SET kata_sandi=?, nama=?, pengguna=?, peran=?, foto=? WHERE id_pengguna=?";
            PreparedStatement ps = koneksidb.prepareStatement(sql);

            ps.setString(1, sandi);
            ps.setString(2, nama);
            ps.setString(3, pengguna);
            ps.setString(4, peran);
            ps.setString(5, foto);
            ps.setString(6, id);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data pengguna berhasil diubah ✏️");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    // HAPUS DATA
    public void hapusPengguna(String id) {
        try {
            String sql = "DELETE FROM tb_pengguna WHERE id_pengguna=?";
            PreparedStatement ps = koneksidb.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data pengguna berhasil dihapus 🗑️");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    // TAMPILKAN DATA KE JTABLE
    public void tampilPengguna(JTable tbl, String sql) {
        try {
            Statement st = koneksidb.createStatement();
            ResultSet rs = st.executeQuery(sql);
            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData meta = rs.getMetaData();

            int kolom = meta.getColumnCount();
            model.getDataVector().clear();
            model.fireTableDataChanged();

            model.addColumn("ID Pengguna");
            model.addColumn("Kata Sandi");
            model.addColumn("Nama");
            model.addColumn("Pengguna");
            model.addColumn("Peran");
            model.addColumn("Foto");

            while (rs.next()) {
                Object[] row = new Object[kolom];
                for (int i = 1; i <= kolom; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            tbl.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
}
