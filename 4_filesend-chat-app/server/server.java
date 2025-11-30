import java.io.*;
import java.util.*;
import java.net.*;

public class server {
    public static void main(String[] args) throws IOException {

        String[] files = {"name.txt","number.txt","sales.txt","Text.txt"};
        ServerSocket server = new ServerSocket(9000);
        System.out.println("waiting for clients ....");

        while(true) {
            Socket soc = server.accept();
            System.out.println("client connected");

            BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

            String str;
            while(true){
                str=in.readLine();
                System.out.println("client says :- " + str);

                if(str.equalsIgnoreCase("dir")){
                    out.println("dir");
                    for(String f : files){
                        out.println(f);
                    }
                    out.println("End");
                }else if(str.equalsIgnoreCase("bye")){
                    System.out.println("client disconnected");
                    break;
                }else if(str.startsWith("get ")){
                     String filename = str.substring(4).trim();
                     File f = new File(filename);
                     if(f.exists() && f.isFile()){
                        out.println("filecontent");
                        BufferedReader filereader = new BufferedReader(new FileReader(f));
                        String line;
                        while((line=filereader.readLine()) != null){
                            out.println(line);
                        }
                        filereader.close();
                        out.println("End");
                     }else{
                         out.println("Error: File not found");
                        out.println("End");
                     }

                }else{
                     out.println("server received :- " + str);
                    out.println("End");
                }
            }
            soc.close();
            System.out.println("client disconnected");
        }
    }
}
