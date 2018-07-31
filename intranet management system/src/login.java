
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class login extends javax.swing.JFrame {

    ArrayList<String> arr;
    
    public login() {
        initComponents();
        setSize(400, 400);
        setVisible(true);
        arr = new ArrayList<>();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usernametf = new javax.swing.JTextField();
        passwordtf = new javax.swing.JPasswordField();
        loginbt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("LOGIN");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(120, 10, 130, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("PASSWORD");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 130, 90, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("USERNAME");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 80, 90, 30);
        getContentPane().add(usernametf);
        usernametf.setBounds(150, 80, 180, 30);
        getContentPane().add(passwordtf);
        passwordtf.setBounds(150, 130, 180, 30);

        loginbt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loginbt.setText("LOGIN");
        loginbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtActionPerformed(evt);
            }
        });
        getContentPane().add(loginbt);
        loginbt.setBounds(90, 200, 150, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtActionPerformed
        new Thread(new Runnable() 
        {

            @Override
            public void run() 
            {
                 try 
                    {
                        Socket sock = new Socket("localhost",8000);
                        DataInputStream dis = new DataInputStream(sock.getInputStream());
                         DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                         
                         String username=usernametf.getText();
                         String password = passwordtf.getText();
                         
                         dos.writeBytes("loginrequest\r\n");
                         
                         String response= dis.readLine();
                         
                         if(response.equals("senddata"))
                         {
                             dos.writeBytes(username+"\r\n");
                             dos.writeBytes(password+"\r\n");
                         }
                         
                         String s=dis.readLine();
                         if(s.equals("successful"))
                         {
                             JOptionPane.showMessageDialog(login.this, "Login successful");
                             
                             Home obj = new Home(username);
                             
                             setVisible(true);
                             
                         }
                         else
                         {
                             JOptionPane.showMessageDialog(login.this, "Invalid username or password");
                         }
                                 
                    }
                catch(Exception ex)
                    {
                         ex.printStackTrace();
                    }
                
            }
        }).start();
        
    }//GEN-LAST:event_loginbtActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginbt;
    private javax.swing.JPasswordField passwordtf;
    private javax.swing.JTextField usernametf;
    // End of variables declaration//GEN-END:variables
}
