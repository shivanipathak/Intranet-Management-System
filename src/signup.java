
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class signup extends javax.swing.JFrame {

    DataInputStream dis;
    DataOutputStream dos;
    String ip;
    public signup(String ip) {
        initComponents();
        setVisible(true);
        setSize(600, 600);
        this.ip=ip;
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        agetf = new javax.swing.JTextField();
        sextf = new javax.swing.JTextField();
        emailtf = new javax.swing.JTextField();
        phonetf = new javax.swing.JTextField();
        usernametf = new javax.swing.JTextField();
        nametf = new javax.swing.JTextField();
        confirmpasstf = new javax.swing.JPasswordField();
        passwordtf = new javax.swing.JPasswordField();
        signupbt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("SIGN UP");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(270, 0, 180, 50);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("AGE");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 120, 130, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("EMAIL");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 200, 150, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("SEX");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 160, 50, 22);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("PHONE NUMBER");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 240, 150, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("USERNAME");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(50, 280, 110, 40);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("NAME");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(50, 80, 110, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("PASSWORD");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(50, 320, 110, 40);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("CONFIRM PASSWORD");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(50, 360, 180, 40);
        getContentPane().add(agetf);
        agetf.setBounds(320, 120, 180, 30);
        getContentPane().add(sextf);
        sextf.setBounds(320, 160, 180, 30);
        getContentPane().add(emailtf);
        emailtf.setBounds(320, 200, 180, 30);

        phonetf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phonetfActionPerformed(evt);
            }
        });
        getContentPane().add(phonetf);
        phonetf.setBounds(320, 240, 180, 30);
        getContentPane().add(usernametf);
        usernametf.setBounds(320, 280, 180, 30);
        getContentPane().add(nametf);
        nametf.setBounds(320, 80, 180, 30);
        getContentPane().add(confirmpasstf);
        confirmpasstf.setBounds(320, 360, 180, 30);
        getContentPane().add(passwordtf);
        passwordtf.setBounds(320, 320, 180, 30);

        signupbt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        signupbt.setText("SIGN UP");
        signupbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupbtActionPerformed(evt);
            }
        });
        getContentPane().add(signupbt);
        signupbt.setBounds(180, 415, 210, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void phonetfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phonetfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phonetfActionPerformed

    private void signupbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupbtActionPerformed
        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                try {
                    Socket sock = new Socket(ip,8000);
                    dis= new DataInputStream(sock.getInputStream());
                    dos= new DataOutputStream(sock.getOutputStream());
                    
                    int flag=0;
                    if(nametf.getText().equals("")||agetf.getText().equals("")||sextf.getText().equals("")||emailtf.getText().equals("")||phonetf.getText().equals("")||usernametf.getText().equals("")||passwordtf.getText().equals("")||confirmpasstf.equals(""))
                    {
                        JOptionPane.showMessageDialog(signup.this, "All fields are mandatory");
                    }
                    
                    if(passwordtf.getText().equals(confirmpasstf.getText()))
                    {
                        flag=1;
                    }
                    
                    else
                    {
                        JOptionPane.showMessageDialog(signup.this, "Passwords donot match");
                    }
                    
                    
                    if(flag==1)
                    {
                        System.out.println("pathu");
                    dos.writeBytes("signuprequest\r\n");
                    
                    
                    String s=dis.readLine();
                    if(s.equals("send data"))
                    {
                        System.out.println("shivi");
                        dos.writeBytes(nametf.getText()+"\r\n");
                        dos.writeBytes(agetf.getText()+"\r\n");
                        dos.writeBytes(sextf.getText()+"\r\n");
                        dos.writeBytes(emailtf.getText()+"\r\n");
                        dos.writeBytes(phonetf.getText()+"\r\n");
                        dos.writeBytes(usernametf.getText()+"\r\n");
                        dos.writeBytes(passwordtf.getText()+"\r\n");
                    }
                    
                    String s1= dis.readLine();
                    if(s1.equals("failed"))
                    {
                        JOptionPane.showMessageDialog(signup.this,"You already have an account");
                    }
                    else if(s1.equals("sameusername"))
                    {
                        JOptionPane.showMessageDialog(signup.this, "username already exist. enter new username");
                    }
                    else if(s1.equals("successful"))
                    {
                        JOptionPane.showMessageDialog(signup.this, "signup successful");
                    }
                    }
                } 
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                }
            }
        }).start();
    }//GEN-LAST:event_signupbtActionPerformed

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
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField agetf;
    private javax.swing.JPasswordField confirmpasstf;
    private javax.swing.JTextField emailtf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nametf;
    private javax.swing.JPasswordField passwordtf;
    private javax.swing.JTextField phonetf;
    private javax.swing.JTextField sextf;
    private javax.swing.JButton signupbt;
    private javax.swing.JTextField usernametf;
    // End of variables declaration//GEN-END:variables
}
