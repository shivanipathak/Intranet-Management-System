
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class log extends javax.swing.JFrame {

     String ip;
     Socket sock;
    
    public log() {
        initComponents();
        setVisible(true);
        setSize(500, 500);
        signupbt.setVisible(false);
        loginbt.setVisible(false);
        exitbt.setVisible(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        signupbt = new javax.swing.JButton();
        loginbt = new javax.swing.JButton();
        exitbt = new javax.swing.JButton();
        connectbt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        signupbt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        signupbt.setText("SignUp");
        signupbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupbtActionPerformed(evt);
            }
        });
        getContentPane().add(signupbt);
        signupbt.setBounds(130, 80, 150, 50);

        loginbt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        loginbt.setText("Login");
        loginbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtActionPerformed(evt);
            }
        });
        getContentPane().add(loginbt);
        loginbt.setBounds(130, 150, 150, 50);

        exitbt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        exitbt.setText("Exit");
        exitbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitbtActionPerformed(evt);
            }
        });
        getContentPane().add(exitbt);
        exitbt.setBounds(130, 220, 150, 50);

        connectbt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        connectbt.setText("CONNECT");
        connectbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectbtActionPerformed(evt);
            }
        });
        getContentPane().add(connectbt);
        connectbt.setBounds(130, 10, 150, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signupbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupbtActionPerformed
       signup obj = new signup(ip);
       obj.setVisible(true);
       
               
    }//GEN-LAST:event_signupbtActionPerformed

    private void loginbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtActionPerformed
        login obj = new login(sock);
        obj.setVisible(true);
    }//GEN-LAST:event_loginbtActionPerformed

    private void connectbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectbtActionPerformed
        ip= JOptionPane.showInputDialog(this, "Enter the ip address");
       new Thread(new Runnable() {

           @Override
           public void run() 
           {
               try 
               {
                    sock= new Socket(ip,8000);
                   signupbt.setVisible(true);
                   loginbt.setVisible(true);
                   exitbt.setVisible(true);
               } 
               catch (Exception ex) 
               {
                   ex.printStackTrace();
               }
           }
       }).start();
    }//GEN-LAST:event_connectbtActionPerformed

    private void exitbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitbtActionPerformed
        
    }//GEN-LAST:event_exitbtActionPerformed

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
            java.util.logging.Logger.getLogger(log.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(log.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(log.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(log.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new log().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectbt;
    private javax.swing.JButton exitbt;
    private javax.swing.JButton loginbt;
    private javax.swing.JButton signupbt;
    // End of variables declaration//GEN-END:variables
}
