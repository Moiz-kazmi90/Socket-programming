import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class server{
    public void run() throws IOException{
        try{
        System.out.println("waiting for clients....");
    
        ServerSocket ss = new ServerSocket(9000);
        while(true){
            Socket soc = ss.accept();
            // System.out.println("client connected");
            // read data
            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String str = in.readLine();
            System.out.println("client says  " +  str);

            // userinput data
            BufferedReader userinput = new BufferedReader(new InputStreamReader(System.in));
            String stor = userinput.readLine();

            // send data
            PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
            out.println(" server says " + stor);
            

        }

      }catch(Exception e){
        e.printStackTrace();
      }
    }

    public static void main(String[] args){
      try{
       server s= new server();
       s.run();
      }catch(Exception e){
        e.printStackTrace();
      }
    }
}