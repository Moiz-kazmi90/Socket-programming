import java.io.*;
import java.util.*;
import java.net.*;

public class server{
     public static void main(String[] args) throws IOException{
      String[] files = {"file1.txt","file2.txt","file3.txt","file4.txt","file5.txt"};
       ServerSocket server = new ServerSocket(9000);
       System.out.println("waiting for clients ....");
       while(true){
         Socket soc = server.accept();
         System.out.println("client connected");
         BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
         PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
         String str;
         while((str=in.readLine()) != null){
         System.out.println("client say :- " + str);

         if(str.equalsIgnoreCase("dir")){
            out.println("files");
            for(String f: files){
               out.println(f);
            }
         out.println("End");
         }else if(str.equalsIgnoreCase("bye")){
               out.println("bye");
               System.out.println("client disconnecetd");
               break;
         }else{
            System.out.println("server received :- " + str);
         }

         }
           soc.close();
           System.out.println("client disconnected");
       }
     }
}