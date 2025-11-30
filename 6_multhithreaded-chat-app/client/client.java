import java.util.*;
import java.net.*;
import java.io.*;
public class client{
    public static void main(String[] args){
        try{
        Socket soc = new Socket("localhost",9000);
        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

         
        Scanner sc = new Scanner(System.in); 
        //  thread read server message continously
        new Thread(()->{
           try{
           String servermsg;
           while((servermsg = in.readLine()) != null){
            System.out.println(servermsg);
           }
           }catch(Exception e){
            System.out.println("Disconnected from server");
           }
        }).start();

        System.out.println("type the message or (Quit to close)");
        while(true){
            String msg = sc.nextLine();
            out.println(msg);
            if(msg.equalsIgnoreCase("Quit")){
                break;
            }
        }
      
        soc.close();
        sc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}