
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


public class Home extends javax.swing.JFrame implements ActionListener {

    String username;
    DataInputStream dis;
    DataOutputStream dos;
    ObjectInputStream ois;
    Socket sock;
    int flag=0;
     String ip;
     
     ArrayList<String> alonline = new ArrayList<>();
     onlineta tmonlineta;
    
    public Home(String username,String ip,Socket sock) throws IOException {
        
        tmonlineta = new onlineta();
        initComponents();
        setSize(700, 700);
        setVisible(true);
        this.username= username;
        this.ip=ip;
        this.sock=sock;
      
        
        
        
        JMenuBar outerbar = new JMenuBar();
        outerbar.setLayout(new GridLayout(0,1));
        JMenu menu = new JMenu("FILE");
        JMenu menu1 = new JMenu("RESOURCES");
        JMenu menu2 = new JMenu("TOOLS");
        JMenu menu3 = new JMenu("HELP");
        
        menu.add(new JMenuItem("File Transfer"));
        menu1.add(new JMenuItem("Screenshot"));
        JMenuItem item =new JMenuItem("Private Chat");
        menu2.add(item);
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
       
       item.addActionListener(this);
       okbt.setVisible(false);
       
       list_model.addElement(username);
       
       dis= new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());
            
            while(true){
                String a = dis.readLine();
                System.out.println(a);
                
                
                
                
                if(a.equals("newuseradded"))
                {
                    System.out.println("hello");
                   onlinelist();
                }
                 if(a.equals("letschat"))
                {
                    System.out.println("bevkoof.a   a.a");
                    reciever(dis,dos);
                }
                 else if(a.equals("allowprivatechat"))
                 {
                     System.out.println("OK");
                 }
            }
       
       
          
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        chattf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatta = new javax.swing.JTextArea();
        sendbt = new javax.swing.JButton();
        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel1 = new javax.swing.JLabel();
        logoutbt = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        onlineta = new javax.swing.JTable();
        okbt = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

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
        jLabel1.setBounds(400, 120, 160, 30);

        logoutbt.setText("LOGOUT");
        logoutbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutbtActionPerformed(evt);
            }
        });
        getContentPane().add(logoutbt);
        logoutbt.setBounds(430, 40, 140, 50);

        onlineta.setModel(tmonlineta);
        jScrollPane4.setViewportView(onlineta);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(400, 150, 160, 240);

        okbt.setText("OK");
        okbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okbtActionPerformed(evt);
            }
        });
        getContentPane().add(okbt);
        okbt.setBounds(410, 420, 130, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendbtActionPerformed
          try {
           // DataOutputStream dos;
           // sock=new Socket(ip,8000);
            dos=new DataOutputStream(sock.getOutputStream());
            //dis=new DataInputStream((sock.getInputStream()));
            if(flag==0)
            {
            dos.writeBytes("chat started\r\n");
            flag=1;
            }
                   String s1 = chattf.getText();
                    chatta.append(s1+"\n");
                    
                    chattf.setText("");
                    sender(s1);
                    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_sendbtActionPerformed

    private void logoutbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutbtActionPerformed
       
        try {
            dos.writeBytes("logoutrequest\r\n");
            System.out.println("logoutrequest sent");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        
    }//GEN-LAST:event_logoutbtActionPerformed

    private void okbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okbtActionPerformed
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
            dos.writeBytes("privaterequest\r\n");
                    System.out.println("1");
            //String k= dis.readLine();
              //      System.out.println(k);
            int index=onlineta.getSelectedRow();
                    System.out.println(username);
                    System.out.println(alonline.get(index));
            dos.writeBytes(username+"\r\n");
            dos.writeBytes(alonline.get(index)+"\r\n");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
            }
        }).start();
        
    }//GEN-LAST:event_okbtActionPerformed

    void onlinelist()
    {


                try 
                {
                    
                    System.out.println("yahan pahunche");
                    
                       System.out.println("firse wahi yr..........................");
                       String s = dis.readLine();
                       int size=Integer.parseInt(s);
                       System.out.println(size);
                       alonline.clear();
                      for(int i=0;i<size;i++)
                      {
                           System.out.println("hello");
                            String abc = dis.readLine();
                            
                            System.out.println(abc);
                            alonline.add(abc);
                      }
                       tmonlineta.fireTableDataChanged();
                } 
                catch (Exception ex)
                {
                    ex.printStackTrace();
                } 
    }
        
   
    
    public void sender(String chat)
    {
        try {
            
            dos.writeBytes(username+": "+chat+"\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
      public void reciever(DataInputStream dis, DataOutputStream dos)
          {
              
              
        try {
            
              
           // while(true)
            {
                System.out.println("in receiver");
            String a1 = dis.readLine();
            chatta.append(a1+"\n");
            }
              
        } 
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
              }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Select a user");
        chatta.setFocusable(false);
        sendbt.setFocusable(false);
        chattf.setFocusable(false);
        okbt.setVisible(true);
    }
      
      class onlineta extends AbstractTableModel
    {

        String title[] =
        {
            "ONLINE SYSTEMS"
        };

        public String getColumnName(int index)
        {
            return title[index];
        }

        public int getRowCount()
        {
            return alonline.size();
        }

        public int getColumnCount()
        {
            return title.length;
        }

        public Object getValueAt(int row, int column)
        {
            return alonline.get(row);
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton logoutbt;
    private javax.swing.JButton okbt;
    private javax.swing.JTable onlineta;
    private javax.swing.JButton sendbt;
    // End of variables declaration//GEN-END:variables
}
