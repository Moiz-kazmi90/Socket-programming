import java.util.*;
import java.net.*;
import java.io.*;

class ClientHandler implements Runnable{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public static Set<ClientHandler> Clienthandler = Collections.synchronizedSet(new HashSet<>());
    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
        
        Clienthandler.add(this);
        String msg;
        while((msg = in.readLine()) != null){
            if(msg.equalsIgnoreCase("Quit")){
                out.println("discoonected from chat app");
                break;
            }
        System.out.println("client say :- " + msg);
         broadcast(msg,this);
        }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                Clienthandler.remove(this);
                socket.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    // sab clients ko message chordega
    private void broadcast(String msg,ClientHandler sender){
        synchronized(Clienthandler){
            for(ClientHandler ch : Clienthandler){
                if(ch != sender){
                  ch.out.println("Message from another client " + msg);
                }
            }
        }
    }

}

public class server{
    public static void main(String[] args) throws IOException{
    ServerSocket server = new ServerSocket(9000);
    System.out.println("waiting for clients ....");

    while(true){
        Socket soc = server.accept();
        System.out.println("client is connected");

        ClientHandler handler = new ClientHandler(soc); 
        Thread t = new Thread(handler);
        t.start();
    }
    }
}