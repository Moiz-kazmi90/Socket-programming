import java.io.*;
import java.util.*;
import java.net.*;
class clientconnection{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public clientconnection(Socket socket ) throws IOException{
        this.socket=socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public String readmessage() throws Exception{
        return in.readLine(); 
    }

    public void sendmessage(String msg){
        out.println(msg);
    }
}
public class server{
    private int port;
    public server(int port){
        this.port = port;
    }

    public void startserver(){
         try{ 
            System.out.println("waiting fro client.....");
            ServerSocket ss = new ServerSocket(port);
            while(true){
                Socket soc = ss.accept();
                clientconnection client = new clientconnection(soc);

                String msg = client.readmessage();
                System.out.println("client says :- " + msg);

                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter the reply :- ");
                String str = in.readLine();
                

                client.sendmessage("server says :- " + str);
              

            }

        }catch(Exception e){
            System.out.println("server is crash");
        }
    }
    public static void main(String[] args){
       server ser = new server(9000);
       ser.startserver();
    }
}