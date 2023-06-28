package View;

import javax.swing.JOptionPane;
import ViewModel.BGM;
import ViewModel.ExperienceProcessing;
import ViewModel.Game;

public class Menu extends javax.swing.JFrame {
    private Game game;
    private ExperienceProcessing process;

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        // Set Frame Location
        this.setLocationRelativeTo(null);
        try {
            // Fetch TableData from Database
            process = new ExperienceProcessing();
            tabel_pemain.setModel(process.readData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        judul = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        tabel_pemain = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        input_username = new javax.swing.JTextField();
        play_btn = new javax.swing.JButton();
        exit_btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Muhammad Rizki - 2107922 | TMD 2023");
        setBackground(new java.awt.Color(0, 102, 102));
        setForeground(java.awt.Color.darkGray);

        judul.setFont(new java.awt.Font("Pixeled", 1, 36)); // NOI18N
        judul.setForeground(new java.awt.Color(0, 153, 153));
        judul.setText("KEEP STANDING");

        tabel_pemain.setFont(new java.awt.Font("pixelmix", 0, 12)); // NOI18N
        tabel_pemain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "score", "standing"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane.setViewportView(tabel_pemain);
        if (tabel_pemain.getColumnModel().getColumnCount() > 0) {
            tabel_pemain.getColumnModel().getColumn(0).setResizable(false);
            tabel_pemain.getColumnModel().getColumn(1).setResizable(false);
            tabel_pemain.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("pixelmix", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Username");

        play_btn.setText("Play");
        play_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                play_btnActionPerformed(evt);
            }
        });

        exit_btn.setText("Exit");
        exit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_btnActionPerformed(evt);
            }
        });

        jLabel4.setText("source image background : https://pngtree.com/freebackground/shading-background-abstract-colorful-background-colorful-art_938007.html");

        jLabel5.setText("music by SergeQuadrado https://pixabay.com/music/happy-childrens-tunes-life-of-a-wandering-wizard-15549/");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(input_username, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(judul, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(play_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(judul)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(input_username, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(play_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addGap(52, 52, 52)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Exit Button
    private void exit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_btnActionPerformed
        // Stop BGM
//        new BGM().stopSound(clip);
        
        // Dispose Frame
        this.dispose();

        // Exit
        System.exit(0);
    }//GEN-LAST:event_exit_btnActionPerformed

    // Play Button
    private void play_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_play_btnActionPerformed
        String username;

        // If Username Field is Not Empty
        if (!input_username.getText().isEmpty()) {
            username = input_username.getText();

            // Stop BGM
//            new BGM().stopSound(this.clip);

            // Set Game
            this.game = new Game();
            try {
                ExperienceProcessing process = new ExperienceProcessing();

                // Checking is Username Exist in Database or Not
                if (process.isDataExist(username)) {
                    // Get Player Data from Database
                    // And Then Set All Data
                    process.getData(username);
                    this.game.setUsername(process.getUsername(0));
                    this.game.setstanding(process.getstanding(0));
                    this.game.setscore(process.getscore(0));
                } else {
                    this.game.setUsername(username);
                    this.game.setstanding(0);
                    this.game.setscore(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Dispose Menu
            this.dispose();
        } else {    // If Username Field is Empty
            JOptionPane.showMessageDialog(null, "Username must be filled!");
        }
    }//GEN-LAST:event_play_btnActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit_btn;
    private javax.swing.JTextField input_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JLabel judul;
    private javax.swing.JButton play_btn;
    private javax.swing.JTable tabel_pemain;
    // End of variables declaration//GEN-END:variables
}