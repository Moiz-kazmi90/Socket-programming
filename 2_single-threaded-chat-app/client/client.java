import java.net.Socket;
import java.io.*;

public class client{

    public void run() throws IOException{
             try{
        
        System.out.println("client started");
        while(true){
        Socket soc = new Socket("localhost" , 9000);
        // userinput data
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        String str = userInput.readLine();
        if(str.equalsIgnoreCase("bye")){
            System.out.println("connection disconnected");
            soc.close();
            break;
        }else{
        // send data 
        PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
        out.println(str);
        }
        // read data 
        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        System.out.println(in.readLine());

        }

        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        try{
         client cli = new client(); 
         cli.run();
          

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}