import java.net.*;
import java.io.*;
public class SkillsClient
{
    public static void main(String[] args)throws IOException
    {
        Socket socket = new Socket("192.168.0.114",1927);
             OutputStream out = socket.getOutputStream();
            PrintWriter p = new PrintWriter(out,true);
            p.println("test");
            
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String response = in.readLine();
         System.out.println(response);
       
       //;lsedfgolp;['lhgfd']
    }
    
}
