package ProjectUTSBP2;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

public class MenuPembelian extends javax.swing.JFrame {
    static cPelanggan p = null;
    static cMakanan mk = null;
    static cMinuman mi = null;
    String tanggal;
    private DefaultTableModel model;
    private double totalBiayaGlobal = 0;
    private double diskonGlobal = 0;
    private double totalAwal = 0;
    private boolean sudahBayar = false;
    
    public void totalBiaya() {
        int jumlahBaris = jTablePembelian.getRowCount();
        double diskon = 0;
        double totalBiaya = 0;
        double jumlahMenu, hargaMenu;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahMenu = Double.parseDouble(jTablePembelian.getValueAt(i, 3).toString());
            hargaMenu = Double.parseDouble(jTablePembelian.getValueAt(i, 4).toString());
            totalBiaya += Double.parseDouble(jTablePembelian.getValueAt(i, 5).toString());
        }
        // simpan total awal
        totalAwal = totalBiaya;
        //simpan totalBiaya variabel global
        totalBiayaGlobal = totalBiaya;
        // Mengatur diskon
        diskon = totalBiayaGlobal * 0.1;
        diskonGlobal = diskon;
        totalBiayaGlobal -= diskon;
        //Menampilakan di text-field
        jTFTotalFinal.setText("Rp"+String.valueOf((int)totalBiayaGlobal));
        jTFDiskon2.setText("Rp"+String.valueOf((int)diskonGlobal));
        jTFTotalAwal.setText("Rp"+String.valueOf((int)totalAwal));
    }
    
    public void totalNonPlg() {
        int jumlahBaris = jTablePembelian.getRowCount();
        double diskon = 0;
        double totalBiaya = 0;
        double jumlahMenu, hargaMenu;
        for (int i = 0; i < jumlahBaris; i++) {
            jumlahMenu = Double.parseDouble(jTablePembelian.getValueAt(i, 3).toString());
            hargaMenu = Double.parseDouble(jTablePembelian.getValueAt(i, 4).toString());
            totalBiaya += Double.parseDouble(jTablePembelian.getValueAt(i, 5).toString());
        }
        // simpan total awal
        totalAwal = totalBiaya;
        //simpan totalBiaya variabel global
        totalBiayaGlobal = totalBiaya;
        diskonGlobal = diskon;
        //Menampilakan di text-field
        jTFTotalFinal.setText("Rp"+String.valueOf((int)totalBiayaGlobal));
        jTFDiskon2.setText("Rp"+String.valueOf((int)diskonGlobal));
        jTFTotalAwal.setText("Rp"+String.valueOf((int)totalAwal));
    }
    
    private void autoNumber() throws SQLException {
        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM penjualan ORDER BY NoTransaksi DESC";
            ResultSet r = s.executeQuery(sql);
            if(r.next()) {
                String NoTransaksi = r.getString("NoTransaksi").substring(2);
                String TR = "" +(Integer.parseInt(NoTransaksi)+1);
                String Nol = "";
                
                if(TR.length() == 1) {
                    Nol = "000";
                } else if(TR.length() == 2) {
                    Nol = "00";
                } else if(TR.length() == 3) {
                    Nol = "0";
                } else if(TR.length() == 4) {
                    Nol = "";
                }
                jTFNoTransaksi.setText("TR" + Nol + TR);
            }
            else {
                jTFNoTransaksi.setText("TR0001");
            }
            r.close();
            s.close();
        } catch(Exception e) {
            System.out.println("autonumber error");
        }
    }
   
    
    public void kosong() {
        DefaultTableModel model = (DefaultTableModel) jTablePembelian.getModel();
        
        while(model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }
    
    public void utama() throws SQLException {
        jTFKodeMenuBeli.setText("");
        jTFIdPelangganBeli.setText("");
        countBeli.setValue(0);
        autoNumber();
    }
    
    public void clearFinal() {
        jTFDiskon2.setText("0");
        jTFJmlBayar.setText("0");
        jTFKembalian1.setText("0");
        jTFTotalAwal.setText("0");
        jTFTotalFinal.setText("0");
    }
    
    public void clear() {
        jTFKodeMenuBeli.setText("");
        countBeli.setValue(0);
    }
    
    public void showPelanggan() {
        DefaultTableModel model = (DefaultTableModel)jTablePlg.getModel();
        if(model.getRowCount()<1) {
            String row[][] = new String[Warung.jplg][3];
            for (int i = 0; i < Warung.jplg; i++) {
                row[i][0] = Warung.plg[i].getID();
                row[i][1] = Warung.plg[i].getNama();
                row[i][2] = Warung.plg[i].getAlamat();
                model.addRow(row[i]);
            }
        }
    }
    
    

    /**
     * Creates new form MenuPembelian
     */
    public MenuPembelian() throws SQLException {
        initComponents();
        
        // Set jendela selalu di tengah
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
        // membuat titik x dan y
        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;
        this.setLocation(x, y-20);
        
        utama();
        Date date  = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        
        jTFTanggal.setText(s.format(date));
        
        jTFKembalian1.setText("0");
        jTFTotalFinal.setText("0");
        jTFJmlBayar.setText("0");
        
        showPelanggan();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMenu2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnShowDaftarMenu = new javax.swing.JButton();
        btnBackMenuBeli = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableMenu1 = new javax.swing.JTable();
        labelHargaMenu2 = new javax.swing.JLabel();
        labelHargaMenu3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelKodeMenuBeli = new javax.swing.JLabel();
        jTFTotalFinal = new javax.swing.JTextField();
        labelIdPelangganBeli = new javax.swing.JLabel();
        jTFIdPelangganBeli = new javax.swing.JTextField();
        labelJumlahBeli = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePembelian = new javax.swing.JTable();
        btnTambahBeli = new javax.swing.JButton();
        btnUbahBeli = new javax.swing.JButton();
        btnBayar = new javax.swing.JButton();
        btnBackBeli = new javax.swing.JButton();
        jTFNoTransaksi = new javax.swing.JTextField();
        labelKodeMenuBeli1 = new javax.swing.JLabel();
        countBeli = new javax.swing.JSpinner();
        jTFTanggal = new javax.swing.JTextField();
        labelKodeMenuBeli2 = new javax.swing.JLabel();
        labelKodeMenuBeli3 = new javax.swing.JLabel();
        jTFDiskon2 = new javax.swing.JTextField();
        labelKodeMenuBeli4 = new javax.swing.JLabel();
        jTFJmlBayar = new javax.swing.JTextField();
        labelKodeMenuBeli5 = new javax.swing.JLabel();
        jTFKembalian1 = new javax.swing.JTextField();
        btnHapusBeli = new javax.swing.JButton();
        jTFKodeMenuBeli = new javax.swing.JTextField();
        labelKodeMenuBeli6 = new javax.swing.JLabel();
        jTFTotalAwal = new javax.swing.JTextField();
        labelKodeMenuBeli7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablePlg = new javax.swing.JTable();
        labelKodeMenuBeli8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(93, 139, 244));
        jPanel4.setLayout(null);

        jTableMenu2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTableMenu2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Menu", "Nama Menu", "Harga Menu"
            }
        ));
        jScrollPane1.setViewportView(jTableMenu2);

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(90, 360, 620, 170);

        jLabel1.setFont(new java.awt.Font("Segoe Script", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Warung Barokah");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(290, 40, 230, 39);

        jLabel2.setFont(new java.awt.Font("Segoe Script", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Daftar Menu");
        jPanel4.add(jLabel2);
        jLabel2.setBounds(320, 10, 180, 39);

        btnShowDaftarMenu.setText("Tampilkan Data");
        btnShowDaftarMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnShowDaftarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowDaftarMenuActionPerformed(evt);
            }
        });
        jPanel4.add(btnShowDaftarMenu);
        btnShowDaftarMenu.setBounds(570, 100, 140, 30);

        btnBackMenuBeli.setText("< Kembali");
        btnBackMenuBeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBackMenuBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackMenuBeliActionPerformed(evt);
            }
        });
        jPanel4.add(btnBackMenuBeli);
        btnBackMenuBeli.setBounds(30, 550, 110, 22);

        jTableMenu1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTableMenu1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Menu", "Nama Menu", "Harga Menu"
            }
        ));
        jScrollPane3.setViewportView(jTableMenu1);

        jPanel4.add(jScrollPane3);
        jScrollPane3.setBounds(90, 150, 620, 170);

        labelHargaMenu2.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
        labelHargaMenu2.setForeground(new java.awt.Color(255, 255, 255));
        labelHargaMenu2.setText("Daftar Minuman");
        jPanel4.add(labelHargaMenu2);
        labelHargaMenu2.setBounds(90, 330, 160, 25);

        labelHargaMenu3.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
        labelHargaMenu3.setForeground(new java.awt.Color(255, 255, 255));
        labelHargaMenu3.setText("Daftar Makanan");
        jPanel4.add(labelHargaMenu3);
        labelHargaMenu3.setBounds(90, 120, 160, 25);

        jTabbedPane1.addTab("DAFTAR MENU", jPanel4);

        jPanel2.setBackground(new java.awt.Color(93, 139, 244));
        jPanel2.setLayout(null);

        labelKodeMenuBeli.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
        labelKodeMenuBeli.setForeground(new java.awt.Color(255, 255, 255));
        labelKodeMenuBeli.setText("Kode Menu");
        jPanel2.add(labelKodeMenuBeli);
        labelKodeMenuBeli.setBounds(10, 100, 144, 25);

        jTFTotalFinal.setBackground(new java.awt.Color(140, 197, 255));
        jTFTotalFinal.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        jTFTotalFinal.setForeground(new java.awt.Color(0, 0, 0));
        jTFTotalFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFTotalFinalActionPerformed(evt);
            }
        });
        jPanel2.add(jTFTotalFinal);
        jTFTotalFinal.setBounds(10, 500, 250, 40);

        labelIdPelangganBeli.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
        labelIdPelangganBeli.setForeground(new java.awt.Color(255, 255, 255));
        labelIdPelangganBeli.setText("ID Pelanggan");
        jPanel2.add(labelIdPelangganBeli);
        labelIdPelangganBeli.setBounds(10, 60, 120, 25);

        jTFIdPelangganBeli.setBackground(new java.awt.Color(140, 197, 255));
        jTFIdPelangganBeli.setForeground(new java.awt.Color(0, 0, 0));
        jTFIdPelangganBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFIdPelangganBeliActionPerformed(evt);
            }
        });
        jPanel2.add(jTFIdPelangganBeli);
        jTFIdPelangganBeli.setBounds(150, 60, 160, 30);

        labelJumlahBeli.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
        labelJumlahBeli.setForeground(new java.awt.Color(255, 255, 255));
        labelJumlahBeli.setText("Jumlah");
        jPanel2.add(labelJumlahBeli);
        labelJumlahBeli.setBounds(330, 100, 70, 25);

        jScrollPane2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTablePembelian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Transaksi", "Kode Menu", "Nama Menu", "Jumlah Beli", "Harga Satuan", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTablePembelian);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 150, 640, 240);

        btnTambahBeli.setText("Tambah");
        btnTambahBeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambahBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahBeliActionPerformed(evt);
            }
        });
        jPanel2.add(btnTambahBeli);
        btnTambahBeli.setBounds(700, 150, 110, 50);

        btnUbahBeli.setText("Ubah");
        btnUbahBeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUbahBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahBeliActionPerformed(evt);
            }
        });
        jPanel2.add(btnUbahBeli);
        btnUbahBeli.setBounds(700, 220, 110, 50);

        btnBayar.setText("Bayar");
        btnBayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        jPanel2.add(btnBayar);
        btnBayar.setBounds(700, 440, 110, 70);

        btnBackBeli.setText("< Kembali");
        btnBackBeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBackBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackBeliActionPerformed(evt);
            }
        });
        jPanel2.add(btnBackBeli);
        btnBackBeli.setBounds(10, 550, 84, 22);

        jTFNoTransaksi.setEditable(false);
        jTFNoTransaksi.setBackground(new java.awt.Color(140, 197, 255));
        jTFNoTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        jTFNoTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTFNoTransaksi.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jTFNoTransaksi.setEnabled(false);
        jTFNoTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNoTransaksiActionPerformed(evt);
            }
        });
        jPanel2.add(jTFNoTransaksi);
        jTFNoTransaksi.setBounds(150, 20, 160, 30);

        labelKodeMenuBeli1.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
        labelKodeMenuBeli1.setForeground(new java.awt.Color(255, 255, 255));
        labelKodeMenuBeli1.setText("No Transaksi");
        jPanel2.add(labelKodeMenuBeli1);
        labelKodeMenuBeli1.setBounds(10, 20, 144, 25);
        jPanel2.add(countBeli);
        countBeli.setBounds(410, 100, 64, 22);

        jTFTanggal.setEditable(false);
        jTFTanggal.setBackground(new java.awt.Color(140, 197, 255));
        jTFTanggal.setForeground(new java.awt.Color(0, 0, 0));
        jTFTanggal.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTFTanggal.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jTFTanggal.setEnabled(false);
        jTFTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFTanggalActionPerformed(evt);
            }
        });
        jPanel2.add(jTFTanggal);
        jTFTanggal.setBounds(720, 0, 100, 20);

        labelKodeMenuBeli2.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        labelKodeMenuBeli2.setForeground(new java.awt.Color(255, 255, 255));
        labelKodeMenuBeli2.setText("Daftar Pelanggan");
        jPanel2.add(labelKodeMenuBeli2);
        labelKodeMenuBeli2.setBounds(530, 30, 130, 20);

        labelKodeMenuBeli3.setFont(new java.awt.Font("Kristen ITC", 0, 16)); // NOI18N
        labelKodeMenuBeli3.setForeground(new java.awt.Color(255, 255, 255));
        labelKodeMenuBeli3.setText("Total Bayar( -diskon 10%)");
        jPanel2.add(labelKodeMenuBeli3);
        labelKodeMenuBeli3.setBounds(10, 470, 200, 30);

        jTFDiskon2.setEditable(false);
        jTFDiskon2.setBackground(new java.awt.Color(140, 197, 255));
        jTFDiskon2.setForeground(new java.awt.Color(0, 0, 0));
        jTFDiskon2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTFDiskon2.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jTFDiskon2.setEnabled(false);
        jTFDiskon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFDiskon2ActionPerformed(evt);
            }
        });
        jPanel2.add(jTFDiskon2);
        jTFDiskon2.setBounds(490, 410, 160, 30);

        labelKodeMenuBeli4.setFont(new java.awt.Font("Kristen ITC", 0, 16)); // NOI18N
        labelKodeMenuBeli4.setForeground(new java.awt.Color(255, 255, 255));
        labelKodeMenuBeli4.setText("Bayar");
        jPanel2.add(labelKodeMenuBeli4);
        labelKodeMenuBeli4.setBounds(380, 450, 70, 30);

        jTFJmlBayar.setBackground(new java.awt.Color(140, 197, 255));
        jTFJmlBayar.setForeground(new java.awt.Color(0, 0, 0));
        jTFJmlBayar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTFJmlBayar.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jTFJmlBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFJmlBayarActionPerformed(evt);
            }
        });
        jPanel2.add(jTFJmlBayar);
        jTFJmlBayar.setBounds(490, 450, 160, 30);

        labelKodeMenuBeli5.setFont(new java.awt.Font("Kristen ITC", 0, 16)); // NOI18N
        labelKodeMenuBeli5.setForeground(new java.awt.Color(255, 255, 255));
        labelKodeMenuBeli5.setText("Kembalian");
        jPanel2.add(labelKodeMenuBeli5);
        labelKodeMenuBeli5.setBounds(380, 490, 100, 30);

        jTFKembalian1.setEditable(false);
        jTFKembalian1.setBackground(new java.awt.Color(140, 197, 255));
        jTFKembalian1.setForeground(new java.awt.Color(0, 0, 0));
        jTFKembalian1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTFKembalian1.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        jTFKembalian1.setEnabled(false);
        jTFKembalian1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFKembalian1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTFKembalian1);
        jTFKembalian1.setBounds(490, 490, 160, 30);

        btnHapusBeli.setText("Hapus");
        btnHapusBeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapusBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusBeliActionPerformed(evt);
            }
        });
        jPanel2.add(btnHapusBeli);
        btnHapusBeli.setBounds(700, 290, 110, 50);

        jTFKodeMenuBeli.setBackground(new java.awt.Color(140, 197, 255));
        jTFKodeMenuBeli.setForeground(new java.awt.Color(0, 0, 0));
        jTFKodeMenuBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFKodeMenuBeliActionPerformed(evt);
            }
        });
        jPanel2.add(jTFKodeMenuBeli);
        jTFKodeMenuBeli.setBounds(150, 100, 160, 30);

        labelKodeMenuBeli6.setFont(new java.awt.Font("Kristen ITC", 0, 16)); // NOI18N
        labelKodeMenuBeli6.setForeground(new java.awt.Color(255, 255, 255));
        labelKodeMenuBeli6.setText("Diskon");
        jPanel2.add(labelKodeMenuBeli6);
        labelKodeMenuBeli6.setBounds(380, 410, 110, 30);

        jTFTotalAwal.setBackground(new java.awt.Color(140, 197, 255));
        jTFTotalAwal.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        jTFTotalAwal.setForeground(new java.awt.Color(0, 0, 0));
        jTFTotalAwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFTotalAwalActionPerformed(evt);
            }
        });
        jPanel2.add(jTFTotalAwal);
        jTFTotalAwal.setBounds(10, 420, 250, 40);

        labelKodeMenuBeli7.setFont(new java.awt.Font("Kristen ITC", 0, 16)); // NOI18N
        labelKodeMenuBeli7.setForeground(new java.awt.Color(255, 255, 255));
        labelKodeMenuBeli7.setText("Total Belanja");
        jPanel2.add(labelKodeMenuBeli7);
        labelKodeMenuBeli7.setBounds(10, 390, 200, 30);

        jTablePlg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama", "Alamat"
            }
        ));
        jScrollPane4.setViewportView(jTablePlg);

        jPanel2.add(jScrollPane4);
        jScrollPane4.setBounds(530, 50, 290, 80);

        labelKodeMenuBeli8.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        labelKodeMenuBeli8.setForeground(new java.awt.Color(255, 255, 255));
        labelKodeMenuBeli8.setText("Tanggal");
        jPanel2.add(labelKodeMenuBeli8);
        labelKodeMenuBeli8.setBounds(670, 0, 50, 20);

        jTabbedPane1.addTab("PEMBELIAN", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFTotalFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFTotalFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFTotalFinalActionPerformed

    private void jTFIdPelangganBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFIdPelangganBeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFIdPelangganBeliActionPerformed

    private void btnTambahBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahBeliActionPerformed
        // TODO add your handling code here:
        String idPel = jTFIdPelangganBeli.getText();
        String kdMenu = jTFKodeMenuBeli.getText();
        

        boolean adaId=false;
        for (int i = 0; i < Warung.jplg; i++) { 
            if(Warung.plg[i].getID().equalsIgnoreCase(idPel)){
            adaId=true; 
            p = Warung.plg[i]; 
            break;
            }
        }
        
        int kdMK = kdMenu.toLowerCase().indexOf("mk");
        int kdMI = kdMenu.toLowerCase().indexOf("mi");
        
        // Transaksi Pelanggan
        if(adaId) {
            boolean adaKd = false;
            if(kdMK == 0) {
                for (int i = 0; i < Warung.jmk; i++) {
                    if(Warung.mk[i].getKode().equalsIgnoreCase(kdMenu)) {
                        adaKd = true;
                        mk = Warung.mk[i];
                        break;
                    }
                }
                if(adaKd == true) {
                    double totalHarga = (int) countBeli.getValue() * mk.getHarga();
                    DefaultTableModel model = (DefaultTableModel) jTablePembelian.getModel();
                    model.addRow(new Object[] {
                    jTFNoTransaksi.getText(),
                    jTFKodeMenuBeli.getText(),
                    mk.getNama(),
                    countBeli.getValue(),
                    (int)mk.getHarga(),
                    (int)totalHarga,
                    });
                    JOptionPane.showMessageDialog(null, "Berhasil menambah pesanan...");
                    
                }
                else {
                    JOptionPane.showMessageDialog(this, "Kode menu tidak ada");
                }   
            } 
            else if(kdMI == 0) {
                for (int i = 0; i < Warung.jmi; i++) {
                    if(Warung.mi[i].getKode().equalsIgnoreCase(kdMenu)) {
                        adaKd = true;
                        mi = Warung.mi[i];
                        break;
                    }
                }
                if(adaKd == true) {
                    double totalHarga = (int) countBeli.getValue() * mi.getHarga();
                    DefaultTableModel model = (DefaultTableModel) jTablePembelian.getModel();
                    model.addRow(new Object[] {
                    jTFNoTransaksi.getText(),
                    jTFKodeMenuBeli.getText(),
                    mi.getNama(),
                    countBeli.getValue(),
                    (int)mi.getHarga(),
                    (int)totalHarga,
                    });
                    JOptionPane.showMessageDialog(null, "Berhasil menambah pesanan...");
                }
                else {
                    JOptionPane.showMessageDialog(this, "Kode menu tidak ada");
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Kode tidak valid");
            }
            totalBiaya();
        }
        // Transaksi Non Pelanggan
        else {
            p = new cPelanggan(idPel, "Non Pelanggan");
            boolean adaKd = false;
            if(kdMK == 0) {
                for (int i = 0; i < Warung.jmk; i++) {
                    if(Warung.mk[i].getKode().equalsIgnoreCase(kdMenu)) {
                        adaKd = true;
                        mk = Warung.mk[i];
                        break;
                    }
                }
                if(adaKd == true) {
                    double totalHarga = (int) countBeli.getValue() * mk.getHarga();
                    DefaultTableModel model = (DefaultTableModel) jTablePembelian.getModel();
                    model.addRow(new Object[] {
                    jTFNoTransaksi.getText(),
                    jTFKodeMenuBeli.getText(),
                    mk.getNama(),
                    countBeli.getValue(),
                    (int)mk.getHarga(),
                    (int)totalHarga,
                    });
                    JOptionPane.showMessageDialog(null, "Berhasil menambah pesanan...");
                    
                }
                else {
                    JOptionPane.showMessageDialog(this, "Kode menu tidak ada");
                }   
            } 
            else if(kdMI == 0) {
                for (int i = 0; i < Warung.jmi; i++) {
                    if(Warung.mi[i].getKode().equalsIgnoreCase(kdMenu)) {
                        adaKd = true;
                        mi = Warung.mi[i];
                        break;
                    }
                }
                if(adaKd == true) {
                    double totalHarga = (int) countBeli.getValue() * mi.getHarga();
                    DefaultTableModel model = (DefaultTableModel) jTablePembelian.getModel();
                    model.addRow(new Object[] {
                    jTFNoTransaksi.getText(),
                    jTFKodeMenuBeli.getText(),
                    mi.getNama(),
                    countBeli.getValue(),
                    (int)mi.getHarga(),
                    (int)totalHarga,
                    });
                    JOptionPane.showMessageDialog(null, "Berhasil menambah pesanan...");
                }
                else {
                    JOptionPane.showMessageDialog(this, "Kode menu tidak ada");
                }
            }
            totalNonPlg();
        }
        
        
        clear();
        jTFKodeMenuBeli.requestFocus();
    }//GEN-LAST:event_btnTambahBeliActionPerformed

    private void btnUbahBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahBeliActionPerformed
        // TODO add your handling code here:
        String jb = JOptionPane.showInputDialog(this,"Masukkan jumlah beli yang baru");
        int j = JOptionPane.showConfirmDialog(this, "Yakin diubah?");
        DefaultTableModel model = (DefaultTableModel) jTablePembelian.getModel();
        int row = jTablePembelian.getSelectedRow();
        int harga = (int) model.getValueAt(row, 4);
        int jml = Integer.parseInt(jb);
        if(j==0) {
            model.setValueAt(jb, row, 3);
            model.setValueAt(jml*harga, row, 5);
            totalBiaya();
            jTFJmlBayar.setText("0");
            jTFKembalian1.setText("0");
            JOptionPane.showMessageDialog(null, "Jumlah beli berhasil diubah");
        } else {
            JOptionPane.showMessageDialog(null, "Batal Ubah!");
        }
        // Mengambil data tabel totalHarga
    }//GEN-LAST:event_btnUbahBeliActionPerformed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        // TODO add your handling code here:
        if(sudahBayar) {
            DefaultTableModel model = (DefaultTableModel) jTablePembelian.getModel();
        
            String noTransaksi = jTFNoTransaksi.getText();
            String idPelanggan = jTFIdPelangganBeli.getText();
            String tanggal = jTFTanggal.getText();
            String total = String.valueOf((int)totalBiayaGlobal);
            String nmPelanggan = p.getNama();


            try {
                Connection c = Koneksi.getKoneksi();
                String sql = "INSERT INTO penjualan VALUES (?,?,?,?,?)";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, noTransaksi);
                p.setString(2, tanggal);
                p.setString(3, idPelanggan);
                p.setString(4, nmPelanggan);
                p.setString(5, total);
                p.executeUpdate();
                p.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "databse penjualan Error");
                System.out.println(e.getMessage());
            }
            try {
                Connection c = Koneksi.getKoneksi();
                int baris = jTablePembelian.getRowCount();
                for (int i = 0; i < baris; i++) {
                    String sql = "INSERT INTO penjualanrinci VALUES ('"
                            + jTablePembelian.getValueAt(i, 0) +"','"
                            + jTablePembelian.getValueAt(i, 1) +"','"
                            + jTablePembelian.getValueAt(i, 2) +"','"
                            + jTablePembelian.getValueAt(i, 3) +"','"
                            + jTablePembelian.getValueAt(i, 4) +"','"
                            + jTablePembelian.getValueAt(i, 5) +"')";
                    PreparedStatement p = c.prepareStatement(sql);
                    p.executeUpdate();
                    p.close();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "database penjualanrinci Error");
                System.out.println(e.getMessage());
            }

            JOptionPane.showMessageDialog(this, "Pesanan atas nama "+p.getNama()+" selesai");
            clearFinal();
            try {
                utama();
                autoNumber();
            } catch (SQLException ex) {
                Logger.getLogger(MenuPembelian.class.getName()).log(Level.SEVERE, null, ex);
            }

            kosong();
            jTFTotalFinal.setText("Rp. 0");
        }
        else {
            JOptionPane.showMessageDialog(null, "Pembayaran belum selesai");
        }
        
    }//GEN-LAST:event_btnBayarActionPerformed

    private void btnShowDaftarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowDaftarMenuActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelMk = (DefaultTableModel)jTableMenu1.getModel();
        DefaultTableModel modelMi = (DefaultTableModel)jTableMenu2.getModel();
        if(modelMk.getRowCount()<1 && modelMi.getRowCount()<1) {
            String rowMk[][] = new String[Warung.jmk][3];
            for (int i = 0; i < Warung.jmk; i++) {
                rowMk[i][0] = Warung.mk[i].getKode();
                rowMk[i][1] = Warung.mk[i].getNama();
                rowMk[i][2] = String.valueOf((int) Warung.mk[i].getHarga());
                modelMk.addRow(rowMk[i]);
            }
            String rowMi[][] = new String[Warung.jmi][3];
            for (int i = 0; i < Warung.jmi; i++) {
                rowMi[i][0] = Warung.mi[i].getKode();
                rowMi[i][1] = Warung.mi[i].getNama();
                rowMi[i][2] = String.valueOf((int) Warung.mi[i].getHarga());
                modelMi.addRow(rowMi[i]);
            }
        }
    }//GEN-LAST:event_btnShowDaftarMenuActionPerformed

    private void btnBackMenuBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackMenuBeliActionPerformed
        // TODO add your handling code here:
        new PilihMenu().show();
        this.dispose();
    }//GEN-LAST:event_btnBackMenuBeliActionPerformed

    private void btnBackBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackBeliActionPerformed
        // TODO add your handling code here:
        new PilihMenu().show();
        this.dispose();
    }//GEN-LAST:event_btnBackBeliActionPerformed

    private void jTFNoTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNoTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNoTransaksiActionPerformed

    private void jTFTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFTanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFTanggalActionPerformed

    private void jTFDiskon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFDiskon2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFDiskon2ActionPerformed

    private void jTFJmlBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFJmlBayarActionPerformed
        // TODO add your handling code here:
        int total, bayar, kembalian;
        
        total = (int)totalBiayaGlobal;
        bayar = Integer.valueOf(jTFJmlBayar.getText());
        
        if(total > bayar) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
        } else {
            kembalian = bayar-total;
            jTFKembalian1.setText(String.valueOf(kembalian));
            sudahBayar = true;
        }
    }//GEN-LAST:event_jTFJmlBayarActionPerformed

    private void jTFKembalian1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFKembalian1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFKembalian1ActionPerformed

    private void btnHapusBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusBeliActionPerformed
        // TODO add your handling code here:
        int j = JOptionPane.showConfirmDialog(this, "Yakin dihapus?");
        if(j == 0) {
            DefaultTableModel model = (DefaultTableModel) jTablePembelian.getModel();
            int row = jTablePembelian.getSelectedRow();
            model.removeRow(row);
            totalBiaya();
            jTFJmlBayar.setText("0");
            jTFKembalian1.setText("0");
            JOptionPane.showMessageDialog(null, "Pesanan berhasil dihapus");
        } else {
            JOptionPane.showMessageDialog(null, "Batal Hapus!");
        }
    }//GEN-LAST:event_btnHapusBeliActionPerformed

    private void jTFKodeMenuBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFKodeMenuBeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFKodeMenuBeliActionPerformed

    private void jTFTotalAwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFTotalAwalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFTotalAwalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MenuPembelian().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuPembelian.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackBeli;
    private javax.swing.JButton btnBackMenuBeli;
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnHapusBeli;
    private javax.swing.JButton btnShowDaftarMenu;
    private javax.swing.JButton btnTambahBeli;
    private javax.swing.JButton btnUbahBeli;
    private javax.swing.JSpinner countBeli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTFDiskon2;
    private javax.swing.JTextField jTFIdPelangganBeli;
    private javax.swing.JTextField jTFJmlBayar;
    private javax.swing.JTextField jTFKembalian1;
    private javax.swing.JTextField jTFKodeMenuBeli;
    private javax.swing.JTextField jTFNoTransaksi;
    private javax.swing.JTextField jTFTanggal;
    private javax.swing.JTextField jTFTotalAwal;
    private javax.swing.JTextField jTFTotalFinal;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableMenu1;
    private javax.swing.JTable jTableMenu2;
    private javax.swing.JTable jTablePembelian;
    private javax.swing.JTable jTablePlg;
    private javax.swing.JLabel labelHargaMenu2;
    private javax.swing.JLabel labelHargaMenu3;
    private javax.swing.JLabel labelIdPelangganBeli;
    private javax.swing.JLabel labelJumlahBeli;
    private javax.swing.JLabel labelKodeMenuBeli;
    private javax.swing.JLabel labelKodeMenuBeli1;
    private javax.swing.JLabel labelKodeMenuBeli2;
    private javax.swing.JLabel labelKodeMenuBeli3;
    private javax.swing.JLabel labelKodeMenuBeli4;
    private javax.swing.JLabel labelKodeMenuBeli5;
    private javax.swing.JLabel labelKodeMenuBeli6;
    private javax.swing.JLabel labelKodeMenuBeli7;
    private javax.swing.JLabel labelKodeMenuBeli8;
    // End of variables declaration//GEN-END:variables
}
