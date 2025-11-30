import java.io.*;
import java.util.*;
import java.net.*;

public class client{
     public static void main(String[] args) throws IOException{
      Socket soc = new Socket("localhost",9000);
      BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
      PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
      Scanner sc = new Scanner(System.in);
      while(true){
         System.out.println("enter the message");
         String reply = sc.nextLine();
         if(reply.equalsIgnoreCase("bye")){
            out.println(reply);
            System.out.println("client disconnected");
            break;
         }
         out.println(reply);
         System.out.println("server say");
         String line;
         while(!(line=in.readLine()).equals("End")){
            System.out.println(line);
         }
      }
      soc.close();
     }
}