import java.net.Socket;
import java.io.*;

class serverconnection{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public serverconnection(Socket socket) throws IOException{
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out =  new PrintWriter(socket.getOutputStream(),true);
    }
    
    public String readmessage() throws IOException{
        return in.readLine();
    }

    public void sendmessage(String msg){
        out.println(msg);
    }
}

public class client{
    private int port;
    public client(int port){
        this.port = port;
    }

    public void startclient(){
     try{
        
        System.out.println("client started");
        while(true){
        Socket soc = new Socket("localhost" , port);
        serverconnection client = new serverconnection(soc);
        // userinput data
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the reply :- ");
        String str = userInput.readLine();
        if(str.equalsIgnoreCase("bye")){
            System.out.println("connection disconnected");
            soc.close();
            break;
        }else{
        // send data 
        client.sendmessage("client say :- " + str);
        }
        // read data 
         String msg = client.readmessage();
         System.out.println( msg);

        }

        }catch(Exception e){
            e.printStackTrace();
        }   
    }

    public static void main(String[] args){
     client c = new client(9000);
     c.startclient();
    }
}