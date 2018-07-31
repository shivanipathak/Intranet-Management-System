
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class server 
{
    
    DataOutputStream dos;
    Socket sock;
    ServerSocket sersock;
    server_home obj1;
    int flag=0;
    
    server() 
    {
        
        try
        {
            sersock = new ServerSocket(8000);
            obj1= new server_home(sock);
            while(true)
            {
                sock= sersock.accept();
                clienthandler obj = new clienthandler(sock);
                new Thread(obj).start();
            }
        } 
        catch (Exception ex) 
        {
          ex.printStackTrace();
        }
    }
    
    class clienthandler implements Runnable
    {
        Socket sock;
        DataInputStream dis;
        DataOutputStream dos;
        clienthandler(Socket sock)
        {
            this.sock=sock;
        }

        @Override
        public void run() 
        {
            try 
            {
                dis= new DataInputStream(sock.getInputStream());
                dos= new DataOutputStream(sock.getOutputStream());
                
                String s= dis.readLine();
//                System.out.println(s);
                
                if(s.equals("signuprequest"))
                {
                    System.out.println("pahunch gyi");
                    signup(dis,dos);
                }
                
                else if(s.equals("loginrequest"))
                {
                    login(dis,dos);
                }
                
                else if(s.equals("chat started"))
                {

                    chat(dis,dos);
                
                }
            }
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
            
           
        }
    }
    void signup(DataInputStream dis,DataOutputStream dos)
    {
        try 
        {
            System.out.println("chlta hai");
            dos.writeBytes("send data\r\n");
            String name=dis.readLine();
            String age=dis.readLine();
            String sex=dis.readLine();
            String email=dis.readLine();
            String phone=dis.readLine();
            String username=dis.readLine();
            String password=dis.readLine();
            
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/management_system", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from signup where email='" + email + "'");
            
            Statement stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs1 = stmt1.executeQuery("select * from signup where username='"+username+"'");
            
            if(rs.next())
            {
                dos.writeBytes("failed\r\n");
            }
            else if(rs1.next())
            {
                dos.writeBytes("sameusername\r\n");
            }
            else
            {
                rs.moveToInsertRow();
                rs.updateString("name", name);
                rs.updateString("age", age);
                rs.updateString("sex", sex);
                rs.updateString("email", email);
                rs.updateString("phoneno", phone);
                rs.updateString("username", username);
                rs.updateString("password", password);           
                rs.insertRow();
                dos.writeBytes("successful\r\n");
            }
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();        }
    }
    
    void login(DataInputStream dis,DataOutputStream dos)
    {
        try 
        {
            dos.writeBytes("senddata\r\n");
            
            String username= dis.readLine();
            String password = dis.readLine();
            
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/management_system", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from signup where username='" + username + "'and password='"+password+"'");
            
            if(rs.next())
            {
                dos.writeBytes("successful\r\n");
                
                    obj1.onlinelist(username);
            }
            
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        
    }
    void chat(DataInputStream dis, DataOutputStream dos)
    {
       
                try 
        {
            
            while(true)
            {
            String s1 = dis.readLine();     
            obj1.receive_message(s1);
            }
        }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
    }
    
   
        
    
    
    public static void main(String[] args) 
    {
       server obj = new server();
       
    }
    
}
