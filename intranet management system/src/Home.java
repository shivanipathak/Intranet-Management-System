
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Home extends javax.swing.JFrame {

    String username;
    DataInputStream dis;
    DataOutputStream dos;
    Socket sock;
    int flag=0;
    
    public Home(String username) throws IOException {
        initComponents();
        setSize(700, 700);
        setVisible(true);
        this.username= username;
        sock= new Socket("localhost",8000);
        
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
          outerbar.add(new JLabel("USERHOME",JLabel.LEFT));
       outerbar.add(innerbar);
        
        this.setJMenuBar(outerbar);
       this.setSize(700, 700);
        this.setVisible(true);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        DefaultListModel list_model= new DefaultListModel();
       chatta.setEditable(false);
       chatta.setFocusable(false);
       onlineta.setFocusable(false);
       onlineta.setVisibleRowCount(6);
       list_model.addElement(username);
          
       reciever(dis,dos);
          
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chattf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatta = new javax.swing.JTextArea();
        sendbt = new javax.swing.JButton();
        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        onlineta = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(chattf);
        chattf.setBounds(20, 420, 220, 50);

        chatta.setColumns(20);
        chatta.setRows(5);
        jScrollPane1.setViewportView(chatta);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 160, 340, 230);

        sendbt.setText("SEND");
        sendbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendbtActionPerformed(evt);
            }
        });
        getContentPane().add(sendbt);
        sendbt.setBounds(250, 420, 110, 50);
        getContentPane().add(jScrollBar1);
        jScrollBar1.setBounds(281, 248, 70, 20);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ONLINE");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(430, 150, 160, 30);

        onlineta.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(onlineta);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(430, 180, 140, 280);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendbtActionPerformed
          try {
            dis= new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());
            
            if(flag==0)
            {
            dos.writeBytes("chat started\r\n");
            flag=1;
            }
                   String s1 = chattf.getText();
                    chatta.append(s1+"\n");
                    dos.writeBytes(username+": "+s1+"\r\n");
                    chattf.setText("");
                    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_sendbtActionPerformed

    
      public void reciever(DataInputStream dis, DataOutputStream dos)
          {
              
              
        try {
            String a = dis.readLine();
              if(a.equals("servermessage"))
              {
            while(true)
            {
                System.out.println("in receiver");
            String a1 = dis.readLine();
            chatta.append(a1+"\n");
            }
              }
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
              }
          
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList onlineta;
    private javax.swing.JButton sendbt;
    // End of variables declaration//GEN-END:variables
}
