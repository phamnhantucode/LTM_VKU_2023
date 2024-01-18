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
                    System.out.println("Server: Connnect to clien number: "
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
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnThoat = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbComputerInfo = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);

        btnChatClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icon chat.png"))); // NOI18N
        btnChatClient.setText("Chat Client");
        btnChatClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChatClient.setMaximumSize(new java.awt.Dimension(169, 60));
        btnChatClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChatClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatClientActionPerformed(evt);
            }
        });
        jToolBar1.add(btnChatClient);

        btnGoiThongDiep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/send-message.png"))); // NOI18N
        btnGoiThongDiep.setText("Send notification");
        btnGoiThongDiep.setFocusable(false);
        btnGoiThongDiep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGoiThongDiep.setMaximumSize(new java.awt.Dimension(189, 60));
        btnGoiThongDiep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGoiThongDiep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoiThongDiepActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGoiThongDiep);

        btnTruyenTapTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/truyen file.png"))); // NOI18N
        btnTruyenTapTin.setText("File transfer");
        btnTruyenTapTin.setFocusable(false);
        btnTruyenTapTin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTruyenTapTin.setMaximumSize(new java.awt.Dimension(183, 60));
        btnTruyenTapTin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTruyenTapTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTruyenTapTinActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTruyenTapTin);
        jToolBar1.add(jSeparator1);

        jButtonTheoDoiClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icon_inspection.png"))); // NOI18N
        jButtonTheoDoiClient.setText("Client tracking");
        jButtonTheoDoiClient.setFocusable(false);
        jButtonTheoDoiClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTheoDoiClient.setMaximumSize(new java.awt.Dimension(188, 60));
        jButtonTheoDoiClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonTheoDoiClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTheoDoiClientActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonTheoDoiClient);

        jButtonChupHinhClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/photograph.png"))); // NOI18N
        jButtonChupHinhClient.setText("Client screenshot");
        jButtonChupHinhClient.setFocusable(false);
        jButtonChupHinhClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonChupHinhClient.setMaximumSize(new java.awt.Dimension(918, 60));
        jButtonChupHinhClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonChupHinhClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChupHinhClientActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonChupHinhClient);

        jButtonRemoteDesktop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/remote.png"))); // NOI18N
        jButtonRemoteDesktop.setText("Remote desktop");
        jButtonRemoteDesktop.setFocusable(false);
        jButtonRemoteDesktop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRemoteDesktop.setMaximumSize(new java.awt.Dimension(198, 60));
        jButtonRemoteDesktop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonRemoteDesktop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoteDesktopActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonRemoteDesktop);
        jToolBar1.add(jSeparator2);

        jButtonGoiLenhShell.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonGoiLenhShell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/cmd.png"))); // NOI18N
        jButtonGoiLenhShell.setText("CMD");
        jButtonGoiLenhShell.setFocusable(false);
        jButtonGoiLenhShell.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGoiLenhShell.setMaximumSize(new java.awt.Dimension(142, 58));
        jButtonGoiLenhShell.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGoiLenhShell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGoiLenhShellActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonGoiLenhShell);
        jToolBar1.add(jSeparator4);

        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 0, 0));
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/icon_exit.png"))); // NOI18N
        btnThoat.setText("Exit");
        btnThoat.setFocusable(false);
        btnThoat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThoat.setMaximumSize(new java.awt.Dimension(155, 62));
        btnThoat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnThoat);

        jTabbedPane1.setBackground(new java.awt.Color(205, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane1.setBackground(new java.awt.Color(242, 242, 242));

        tbComputerInfo.setBackground(new java.awt.Color(242, 242, 242));
        tbComputerInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Computer name", "IP"
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
        tbComputerInfo.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("List computer connected", new javax.swing.ImageIcon(getClass().getResource("/RES/tracking-computer.png")), jPanel1); // NOI18N
        jTabbedPane1.addTab("Seach", jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("List computer connected");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChatClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatClientActionPerformed
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Please select computer to chat!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Mở form chat với client đã chọn
        new Thread(new FrmChatVoiClient(mayClient)).start();
    }//GEN-LAST:event_btnChatClientActionPerformed
    
    private void btnGoiThongDiepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoiThongDiepActionPerformed
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Plear select computer to send!");
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
            JOptionPane.showMessageDialog(rootPane, "Select network to use this function");
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
            JOptionPane.showMessageDialog(rootPane, "Select computer to control!");
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
            JOptionPane.showMessageDialog(rootPane, "Select computer to control!");
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
            JOptionPane.showMessageDialog(rootPane, "Select computer to take screenshot!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Mở form chụp hình với client đã chọn
        new Thread(new FrmChupHinhClient(mayClient)).start();
    }//GEN-LAST:event_jButtonChupHinhClientActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        System.exit(0);
        //dispose();        // thoat tạm thời
    }//GEN-LAST:event_btnThoatActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChatClient;
    private javax.swing.JButton btnGoiThongDiep;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTruyenTapTin;
    private javax.swing.JButton jButtonChupHinhClient;
    private javax.swing.JButton jButtonGoiLenhShell;
    private javax.swing.JButton jButtonRemoteDesktop;
    private javax.swing.JButton jButtonTheoDoiClient;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tbComputerInfo;
    // End of variables declaration//GEN-END:variables
}
