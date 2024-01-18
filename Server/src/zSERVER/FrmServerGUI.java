package zSERVER;

import PACKAGES.ComputerTableModel;
import PACKAGES.PacketRemoteDesktop;
import PACKAGES.PacketTheoDoiClient;
import PACKAGES.PacketTruyenFile;
import UTILS.DataUtils;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import java.time.DateTimeException;

/**
 *
 * @author Nguyen minh tien_1601702
 */
public class FrmServerGUI extends javax.swing.JFrame implements Runnable {
    
    private final int mainThreadPortNumber = 999;
    private final int remoteDesktopThreadPortNumber = 998;
    private final int theoDoiClientThreadPortNumber = 997;
    private final int fileTransferThreadPortNumber = 996;
    
    Timer timerUpdateListSocket;
    private int timeUpdateTable = 5; // second
    public FrmServerGUI() {
        initComponents();
        setLocation(300, 100);
        tbComputerInfo.setModel(new ComputerTableModel(new ArrayList()));
        tbComputerInfo.getColumnModel().getColumn(0).setMaxWidth(100);
        setVisible(true);
        // Cập nhật list socket sau mỗi timeUpdateTable giây
        timerUpdateListSocket = new Timer();
        timerUpdateListSocket.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getTbModel().updateAllElement();
        // server lắng nghe remote desktop
        runThreadRemoteDesktop(); 
            }
        }, timeUpdateTable * 1000, timeUpdateTable * 1000);
        // server lắng nghe remote desktop
        runThreadRemoteDesktop(); 
        // server lắng nghe gởi/ nhận file
        runThreadTransferFile();
        // server lắng nghe theo dõi client
        runThreadTheoDoiClient();
    }
    
    private ComputerTableModel getTbModel() {
        return (ComputerTableModel) tbComputerInfo.getModel();
    }


    @Override
    public void run() {
       // chat, gởi thông điệp, gởi lệnh shell
       try {
            final ServerSocket server = new ServerSocket(mainThreadPortNumber);
            // Phục vụ nhiều client
            while (true) {
                Socket socket;
                try {
                    // Nếu không dùng thread
                    // chương trình sẽ chờ 1 client đầu tiên ở đây
                    socket = server.accept();
                    getTbModel().addElement(socket);
                    System.out.println("Server: Đã kết nối client thứ "
                            + getTbModel().getSize());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }   
    }
     
    //<editor-fold defaultstate="collapsed" desc="Thread transfer file">
    private void runThreadTransferFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final ServerSocket server = new ServerSocket(fileTransferThreadPortNumber);
                    // Phục vụ nhiều client
                    while (true) {
                        Socket socket;
                        try {
                            socket = server.accept();
                            new Thread(new FrmGoiFile(socket)).start();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Thread remote desktop">

    private void runThreadRemoteDesktop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final ServerSocket server = new ServerSocket(remoteDesktopThreadPortNumber);
                    // Phục vụ nhiều client
                    while (true) {
                        Socket socket;
                        try {
                            socket = server.accept();
                            new Thread(new FrmRemoteDesktop(socket)).start();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Thread theo dõi client">
    private void runThreadTheoDoiClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final ServerSocket server = new ServerSocket(theoDoiClientThreadPortNumber);
                    // Phục vụ nhiều client
                    while (true) {
                        Socket socket;
                        try {
                            socket = server.accept();
                            new Thread(new FrmTheoDoiClient(socket)).start();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    //</editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnChatClient = new javax.swing.JButton();
        btnGoiThongDiep = new javax.swing.JButton();
        btnTruyenTapTin = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButtonTheoDoiClient = new javax.swing.JButton();
        jButtonChupHinhClient = new javax.swing.JButton();
        jButtonRemoteDesktop = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButtonGoiLenhShell = new javax.swing.JButton();
        btnGiaitri = new javax.swing.JButton();
        btnAbout = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnThoat = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbComputerInfo = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblTrangThai = new javax.swing.JLabel();
        lblDateTime = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblTrangThai1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jbtThoat = new javax.swing.JMenuItem();
        jEdit = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jTools = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jHelp = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jbtThoat1 = new javax.swing.JMenuItem();
        jSettings = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setBackground(new java.awt.Color(204, 255, 255));
        jToolBar1.setRollover(true);

        btnChatClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icon chat.png"))); // NOI18N
        btnChatClient.setText("Chat Client");
        btnChatClient.setFocusable(false);
        btnChatClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChatClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChatClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatClientActionPerformed(evt);
            }
        });
        jToolBar1.add(btnChatClient);

        btnGoiThongDiep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/send-message.png"))); // NOI18N
        btnGoiThongDiep.setText("Gửi thông điệp");
        btnGoiThongDiep.setFocusable(false);
        btnGoiThongDiep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGoiThongDiep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGoiThongDiep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoiThongDiepActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGoiThongDiep);

        btnTruyenTapTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/truyen file.png"))); // NOI18N
        btnTruyenTapTin.setText("Truyền tập tin");
        btnTruyenTapTin.setFocusable(false);
        btnTruyenTapTin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTruyenTapTin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTruyenTapTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTruyenTapTinActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTruyenTapTin);
        jToolBar1.add(jSeparator1);

        jButtonTheoDoiClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/theo doi client.png"))); // NOI18N
        jButtonTheoDoiClient.setText("Theo dõi client");
        jButtonTheoDoiClient.setFocusable(false);
        jButtonTheoDoiClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTheoDoiClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonTheoDoiClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTheoDoiClientActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonTheoDoiClient);

        jButtonChupHinhClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/photograph.png"))); // NOI18N
        jButtonChupHinhClient.setText("Chụp hình client");
        jButtonChupHinhClient.setFocusable(false);
        jButtonChupHinhClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonChupHinhClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonChupHinhClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChupHinhClientActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonChupHinhClient);

        jButtonRemoteDesktop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/remote-access.png"))); // NOI18N
        jButtonRemoteDesktop.setText("Điều khiển client");
        jButtonRemoteDesktop.setFocusable(false);
        jButtonRemoteDesktop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRemoteDesktop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonRemoteDesktop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoteDesktopActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonRemoteDesktop);
        jToolBar1.add(jSeparator2);

        jButtonGoiLenhShell.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonGoiLenhShell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/Scan wifi.png"))); // NOI18N
        jButtonGoiLenhShell.setText("WiFi Scanner");
        jButtonGoiLenhShell.setFocusable(false);
        jButtonGoiLenhShell.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGoiLenhShell.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGoiLenhShell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGoiLenhShellActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonGoiLenhShell);

        btnGiaitri.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGiaitri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icon game.png"))); // NOI18N
        btnGiaitri.setText("Giải trí");
        btnGiaitri.setFocusable(false);
        btnGiaitri.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGiaitri.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGiaitri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaitriActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGiaitri);

        btnAbout.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/about.png"))); // NOI18N
        btnAbout.setText("About");
        btnAbout.setFocusable(false);
        btnAbout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAbout);
        jToolBar1.add(jSeparator4);

        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 0, 0));
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/exit2.png"))); // NOI18N
        btnThoat.setText("Exit");
        btnThoat.setFocusable(false);
        btnThoat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThoat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnThoat);

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 204));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbComputerInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên máy", "IP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbComputerInfo);
        if (tbComputerInfo.getColumnModel().getColumnCount() > 0) {
            tbComputerInfo.getColumnModel().getColumn(0).setResizable(false);
            tbComputerInfo.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbComputerInfo.getColumnModel().getColumn(1).setResizable(false);
            tbComputerInfo.getColumnModel().getColumn(1).setPreferredWidth(250);
            tbComputerInfo.getColumnModel().getColumn(2).setResizable(false);
            tbComputerInfo.getColumnModel().getColumn(2).setPreferredWidth(250);
        }

        lblTrangThai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(0, 0, 255));
        lblTrangThai.setText("Trạng thái");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblTrangThai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrangThai)
                    .addComponent(lblDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("Copyright@2020. Version 0.1.0.0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)))
        );

        jTabbedPane1.addTab("Danh sách máy tính đang kết nối", jPanel1);

        lblTrangThai1.setForeground(new java.awt.Color(0, 0, 255));
        lblTrangThai1.setText("Trạng thái");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblTrangThai1)
                .addGap(0, 587, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblTrangThai1)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Copyright@2020. Version 0.1.0.0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(292, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)))
        );

        jTabbedPane2.addTab("Danh sách máy tính đang hoạt động trong LAN", jPanel3);

        jTabbedPane1.addTab("Seach", jTabbedPane2);

        jMenuBar1.setBackground(new java.awt.Color(204, 255, 204));

        jFile.setText("File");
        jFile.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N

        jMenuItem1.setText("Kiểm tra kết nối");
        jFile.add(jMenuItem1);

        jMenuItem3.setText("Xóa toàn bộ dữ liệu");
        jFile.add(jMenuItem3);

        jMenuItem4.setText("Giới thiệu");
        jFile.add(jMenuItem4);

        jbtThoat.setText("Thoát");
        jbtThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtThoatActionPerformed(evt);
            }
        });
        jFile.add(jbtThoat);

        jMenuBar1.add(jFile);

        jEdit.setText("Edit");
        jEdit.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N

        jMenuItem9.setText("Nén file");
        jEdit.add(jMenuItem9);

        jMenuItem10.setText("Doawnload");
        jEdit.add(jMenuItem10);

        jMenuBar1.add(jEdit);

        jTools.setText("Tools");
        jTools.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N

        jMenuItem2.setText("Nén file");
        jTools.add(jMenuItem2);

        jMenuItem6.setText("Doawnload");
        jTools.add(jMenuItem6);

        jMenuBar1.add(jTools);

        jHelp.setText("Help");
        jHelp.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N

        jMenuItem5.setText("Help content");
        jHelp.add(jMenuItem5);

        jMenuItem7.setText("Online Docs and Support");
        jHelp.add(jMenuItem7);

        jMenuItem13.setText("Check for Update");
        jHelp.add(jMenuItem13);

        jMenuItem8.setText("Giới thiệu");
        jHelp.add(jMenuItem8);

        jbtThoat1.setText("Thoát");
        jbtThoat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtThoat1ActionPerformed(evt);
            }
        });
        jHelp.add(jbtThoat1);

        jMenuBar1.add(jHelp);

        jSettings.setText("Settings");
        jSettings.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N

        jMenuItem11.setText("Preferences");
        jSettings.add(jMenuItem11);

        jMenuItem12.setText("Style Configurato");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jSettings.add(jMenuItem12);

        jMenuBar1.add(jSettings);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChatClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatClientActionPerformed
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để chat!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Mở form chat với client đã chọn
        new Thread(new FrmChatVoiClient(mayClient)).start();
    }//GEN-LAST:event_btnChatClientActionPerformed
    
    private void btnGoiThongDiepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoiThongDiepActionPerformed
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để gởi!");
            return;
        }
        int[] rowsSelected = tbComputerInfo.getSelectedRows();
        List<Socket> dsMayClient = new ArrayList();
        for (int i : rowsSelected) {
            dsMayClient.add(getTbModel().getItem(i));
        }
        // Mở form chat với các client đã chọn
        FrmGoiThongDiep goiThongDiep = new FrmGoiThongDiep(dsMayClient);
        goiThongDiep.setVisible(true);
    }//GEN-LAST:event_btnGoiThongDiepActionPerformed
   
    private void btnTruyenTapTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTruyenTapTinActionPerformed
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane,
                    "Bạn chưa chọn máy để gởi file!");
            return;
        }
        Socket mayClient =
                getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Gởi lệnh yêu cầu client kết nối đến socket server file transfer
        PacketTruyenFile packetTruyenFile = new PacketTruyenFile();
        packetTruyenFile.setCmd(PacketTruyenFile.CMD_KHOIDONG);
        packetTruyenFile.setMessage(String.valueOf(fileTransferThreadPortNumber));
        DataUtils.goiDuLieu(mayClient, packetTruyenFile.toString()); 
    }//GEN-LAST:event_btnTruyenTapTinActionPerformed
    
    private void jButtonGoiLenhShellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGoiLenhShellActionPerformed
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn mạng để sử dụng chức năng này!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Mở form gởi lệnh shell đến client đã chọn
        FrmGoiLenhShell goiLenhShell = new FrmGoiLenhShell(mayClient);
        goiLenhShell.khoiDong();
        goiLenhShell.setVisible(true);
    }//GEN-LAST:event_jButtonGoiLenhShellActionPerformed
    
    private void jButtonRemoteDesktopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoteDesktopActionPerformed
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để điều khiển!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Gởi lệnh yêu cầu client kết nối đến socket server remote desktop
        PacketRemoteDesktop pk = new PacketRemoteDesktop();
        pk.setCmd(PacketRemoteDesktop.CMD_KHOIDONG);
        pk.setMessage(String.valueOf(remoteDesktopThreadPortNumber));
        DataUtils.goiDuLieu(mayClient, pk.toString());
    }//GEN-LAST:event_jButtonRemoteDesktopActionPerformed
    
    private void jButtonTheoDoiClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTheoDoiClientActionPerformed
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để điều khiển!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Gởi lệnh yêu cầu client kết nối đến socket server remote desktop
        PacketTheoDoiClient pk = new PacketTheoDoiClient();
        pk.setCmd(PacketTheoDoiClient.CMD_KHOIDONG);
        pk.setMessage(String.valueOf(theoDoiClientThreadPortNumber));
        DataUtils.goiDuLieu(mayClient, pk.toString());
        
    }//GEN-LAST:event_jButtonTheoDoiClientActionPerformed

    private void jButtonChupHinhClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChupHinhClientActionPerformed
         if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để chụp hình!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Mở form chụp hình với client đã chọn
        new Thread(new FrmChupHinhClient(mayClient)).start();
    }//GEN-LAST:event_jButtonChupHinhClientActionPerformed

    private void btnGiaitriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaitriActionPerformed
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Hệ thống đang Update - Xin vui lòng thử lại sau!");
            return;
        }

    }//GEN-LAST:event_btnGiaitriActionPerformed

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed
        /* if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane,"\b Chương trình hỗ trợ và giám sát các máy tính trong cùng mạng LAN"
                +"\r\n Được thực hiện trong hơn 3 tháng làm Đồ án tốt nghiệp nhằm mục đích giúp người sử dụng dễ dàng thao tác hơn mà không phải sử dung nhiều câu lệnh"
                +"\r\n Email: tientienmk@gmail.com");
            return;
        } */

        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane,"\b Chương trình hỗ trợ và giám sát các máy tính trong cùng mạng LAN"
                +"\r\n Được thực hiện trong hơn 3 tháng làm Đồ án tốt nghiệp nhằm mục đích giúp người sử dụng dễ dàng thao tác hơn mà không phải sử dung nhiều câu lệnh"
                +"\r\n Email: tientienmk@gmail.com");
            return;
        }
    }//GEN-LAST:event_btnAboutActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        System.exit(0);
        //dispose();        // thoat tạm thời
    }//GEN-LAST:event_btnThoatActionPerformed

    private void jbtThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtThoatActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbtThoatActionPerformed

    private void jbtThoat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtThoat1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtThoat1ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbout;
    private javax.swing.JButton btnChatClient;
    private javax.swing.JButton btnGiaitri;
    private javax.swing.JButton btnGoiThongDiep;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTruyenTapTin;
    private javax.swing.JButton jButtonChupHinhClient;
    private javax.swing.JButton jButtonGoiLenhShell;
    private javax.swing.JButton jButtonRemoteDesktop;
    private javax.swing.JButton jButtonTheoDoiClient;
    private javax.swing.JMenu jEdit;
    private javax.swing.JMenu jFile;
    private javax.swing.JMenu jHelp;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JMenu jSettings;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu jTools;
    private javax.swing.JMenuItem jbtThoat;
    private javax.swing.JMenuItem jbtThoat1;
    private javax.swing.JLabel lblDateTime;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblTrangThai1;
    private javax.swing.JTable tbComputerInfo;
    // End of variables declaration//GEN-END:variables
}
