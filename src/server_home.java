
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


public class server_home extends javax.swing.JFrame implements ActionListener{
    
    
    ArrayList<String> al = new ArrayList<>();
    ArrayList<Socket> sk= new ArrayList<>();
    ArrayList<String> privatechat= new ArrayList<>();
    int flag=0;
    DataInputStream dis;
    DataOutputStream dos;
    ObjectOutputStream oos;
    Socket sock;
    Socket sock1,privatesock;
    onlineta tmonlineta;
    public server_home() {
        
        
        tmonlineta= new onlineta();
        initComponents();
        setSize(700, 700);
        setVisible(true);
     
        
        
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
          outerbar.add(new JLabel("SERVER HOME",JLabel.LEFT));
       outerbar.add(innerbar);
        
        this.setJMenuBar(outerbar);
       this.setSize(700, 700);
        this.setVisible(true);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       chatta.setEditable(false);
       chatta.setFocusable(false);
       onlineta.setFocusable(false);
       item.addActionListener(this);
       
      
       
    }
    
    void createprivatechat()
    {
        try {
            
            System.out.println("here");
            int flag=0;
           // dos.writeBytes("senduser\r\n");
            //System.out.println("2");
            String p=dis.readLine();
            String q=dis.readLine();
            System.out.println(p+ " "+q);
            
            for(String s:privatechat)
            {
                if(s.equals(p+" "+q)||s.equals(q+" "+p))
                {
                    JOptionPane.showMessageDialog(this,"you are already connected");
                    flag=1;
                    break;
                }
            }
            if(flag==0)
            {
                privatechat.add(p+" "+q);
                System.out.println("Inside loop");
                for(int i=0;i<al.size();i++)
                {
                    privatesock=sk.get(i);
                    break;
                }
                
                DataOutputStream dos1= new DataOutputStream(privatesock.getOutputStream());
                DataInputStream dis1= new DataInputStream(privatesock.getInputStream());
                System.out.println("Am ready");
                dos1.writeBytes("allowprivatechat\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onlinelist(String username)
    {
        try 
        {
            al.add(username);
            tmonlineta.fireTableDataChanged();  
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    void listsender()
    {
         for(Socket s:sk)
            {
               
             try 
             {
                 // System.out.println(sock2);
                 dos=new DataOutputStream(s.getOutputStream());
                 System.out.println("ye naya sock hai");
                 dos.writeBytes("newuseradded\r\n");
                 dos.writeBytes(al.size()+"\r\n");
                 for(int i=0;i<al.size();i++)
                 {
                     dos.writeBytes(al.get(i)+"\r\n");
                     
                 }
             }
             catch (Exception ex) {
                 ex.printStackTrace();
             }
        }
    }
    
    
    public void receive_message(Socket sock)
    {
        try
        {
            dis = new DataInputStream(sock.getInputStream());
            
            while(true)
            {
                String chat = dis.readLine();
                chatta.append(chat+"\n");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatta = new javax.swing.JTextArea();
        sendbt = new javax.swing.JButton();
        chattf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        onlineta = new javax.swing.JTable();

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

        onlineta.setModel(tmonlineta);
        jScrollPane3.setViewportView(onlineta);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(370, 160, 190, 340);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void sendersocket(Socket sock)
    {
        try {
             sock1 = sock;
        dos=new DataOutputStream(sock.getOutputStream());
        sk.add(sock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sender(String message)
    {
        for(Socket s:sk)
        {
            try
            {
            dos= new DataOutputStream(s.getOutputStream());
            dos.writeBytes("letschat\r\n");
            
            dos.writeBytes(message+"\r\n");
            }
            
        
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    }
    
    
    public void logout(Socket sock)
    {
        int i=-1;
        System.out.println(sock);
        System.out.println("in logoutfunc");
        for(int y=0;y<sk.size();y++)
        {
            if(sk.get(y)==sock)
            {
                System.out.println(sock);
            i=y;
                System.out.println(i);
            break;
            }
            
           
        }
         if(i!=-1)
            {
                System.out.println("pathuuu");
            sk.remove(i);
            al.remove(i);
                System.out.println(al.size());
            //onlineta.setText("");
//            for(int a=0;a<sk.size();a++)
//            {
//                onlineta.append(al.get(a)+"\n");
//            }
            tmonlineta.fireTableDataChanged();
            }
    }
    private void sendbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendbtActionPerformed
       
        

        String s = chattf.getText();
        chatta.append(s+"\n");
        chattf.setText("");
        
        sender("Server: "+s);
    }//GEN-LAST:event_sendbtActionPerformed

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
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
            return al.size();
        }

        public int getColumnCount()
        {
            return title.length;
        }

        public Object getValueAt(int row, int column)
        {
            return al.get(row);
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable onlineta;
    private javax.swing.JButton sendbt;
    // End of variables declaration//GEN-END:variables
}
