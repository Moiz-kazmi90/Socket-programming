import java.net.*;
import java.io.*;

public class scraper{
    public static void main(String[] args){
        try{
            String link = ("https://httpbin.org/html");
            URL url = new URL(link);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter out = new BufferedWriter(new FileWriter("web.html"));
            String content;
            while((content = in.readLine()) != null){
                out.write(content);
                out.newLine();
            }
            System.out.println("web page download successfully");
            in.close();
            out.close();
        }catch(Exception e){
            System.out.println("file not found");
        }
    }
}