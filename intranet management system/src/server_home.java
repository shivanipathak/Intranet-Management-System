
import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class server_home extends javax.swing.JFrame {
    
    
    ArrayList<String> al = new ArrayList<>();
    int flag=0;
    DataInputStream dis;
    DataOutputStream dos;
    Socket sock;
    
    public server_home(Socket sock) {
        
        
        
        
        initComponents();
        setSize(700, 700);
        setVisible(true);
        this.sock= sock;
        try 
        {
           
            dis= new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        JMenuBar outerbar = new JMenuBar();
        outerbar.setLayout(new GridLayout(0,1));
        JMenu menu = new JMenu("FILE");
        JMenu menu1 = new JMenu("RESOURCES");
        JMenu menu2 = new JMenu("TOOLS");
        JMenu menu3 = new JMenu("HELP");
        
        menu.add(new JMenuItem("File Transfer"));
        menu1.add(new JMenuItem("Screenshot"));
        menu2.add(new JMenuItem("Private Chat"));
        menu3.add(new JMenuItem("About"));
        JMenuBar innerbar = new JMenuBar();
        innerbar.add(menu);
        innerbar.add(menu1);
        innerbar.add(menu2);
        innerbar.add(menu3);
          outerbar.add(new JLabel("SERVER HOME",JLabel.LEFT));
       outerbar.add(innerbar);
        
        this.setJMenuBar(outerbar);
       this.setSize(700, 700);
        this.setVisible(true);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       chatta.setEditable(false);
       chatta.setFocusable(false);
       onlineta.setFocusable(false);
       
      
       
    }
    public void onlinelist(String username)
    {
        int i;
        al.add(username);
        for(i=0;i<al.size()-1;i++)
        {
            
        }
        
        onlineta.append(al.get(i)+"\n");
    }
    
    
    public void receive_message(String message)
    {
        chatta.append(message+"\n");
        
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatta = new javax.swing.JTextArea();
        sendbt = new javax.swing.JButton();
        chattf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        onlineta = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        chatta.setColumns(20);
        chatta.setRows(5);
        jScrollPane1.setViewportView(chatta);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 130, 290, 230);

        sendbt.setText("SEND");
        sendbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendbtActionPerformed(evt);
            }
        });
        getContentPane().add(sendbt);
        sendbt.setBounds(230, 390, 90, 40);
        getContentPane().add(chattf);
        chattf.setBounds(40, 390, 180, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ONLINE");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(390, 126, 110, 30);

        onlineta.setColumns(20);
        onlineta.setRows(5);
        jScrollPane2.setViewportView(onlineta);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(380, 160, 166, 260);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendbtActionPerformed
       
        
          if(flag==0)
            {
              try 
              {
                  dos.writeBytes("servermessage\r\n");
              }
              catch (Exception ex) 
              {
                  ex.printStackTrace();
              }
            flag=1;
            }
        String s = chattf.getText();
        chatta.append(s+"\n");
        chattf.setText("");
        
        sender(s);
    }//GEN-LAST:event_sendbtActionPerformed

     public void sender(String message)
    {
        try 
        {
           // if(flag==0)
            //{
           // dos.writeBytes("servermessage\r\n");
            //flag=1;
            //}
            System.out.println(message);
            dos.writeBytes("Server: "+message+"\r\n");
        }
        catch (Exception ex) 
        {
         ex.printStackTrace();
        }
    }
   
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
            java.util.logging.Logger.getLogger(server_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(server_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(server_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(server_home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatta;
    private javax.swing.JTextField chattf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea onlineta;
    private javax.swing.JButton sendbt;
    // End of variables declaration//GEN-END:variables
}
