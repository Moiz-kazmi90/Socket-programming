import java.util.regex.*;
import java.io.*;
import java.net.*;

public class extract{
    public static void main(String[] args){
      try{
       BufferedReader in = new BufferedReader(new FileReader("/home/moiz/Desktop/socketprogramming/5_scraper.java/3_extracturlfromhtmlfile/web.html"));

    //    regex to match url
    Pattern pattern = Pattern.compile("href\\s*=\\s*[\"'](https?://[^\"'>\\s]+)[\"']",Pattern.CASE_INSENSITIVE);
    String line;
    while((line = in.readLine()) != null){
        Matcher matcher = pattern.matcher(line);
        while(matcher.find()){
            System.out.println(matcher.group(1));
        }
    }
      }catch(Exception e){
        System.out.println("file not found");
      }
    }
}