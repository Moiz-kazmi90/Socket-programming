import java.util.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;

public class webcrawler{
    public static void main(String[] args){
        ArrayList<String> tovisited = new ArrayList<>();
        ArrayList<String> visited = new ArrayList<>();
        tovisited.add("https://books.toscrape.com/");
         int number = 0;
        while(!tovisited.isEmpty() && tovisited.size() < 10){
              String currenturl = tovisited.remove(0);
              if(visited.contains(currenturl)) continue;
              visited.add(currenturl);
        System.out.println("Downloading :- " + currenturl);

        try{
          URL url = new URL(currenturl);
          BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
          BufferedWriter out = new BufferedWriter(new FileWriter("/home/moiz/Desktop/socketprogramming/5_scraper.java/4_webcrawler/savedpages" + number + ".html"));

            Pattern linkPattern = Pattern.compile("href=\"(https?://[^\"]+)\"", Pattern.CASE_INSENSITIVE);
            String line;
            while((line=in.readLine()) != null){
                out.write(line);
                out.newLine();
                Matcher matcher = linkPattern.matcher(line);
                while(matcher.find()){
                    String found = matcher.group(1);

                    if(!visited.contains(found) && !tovisited.contains(found)){
                        tovisited.add(found);
                    }
                }
            }
            in.close();
            out.close();
            number++;
        }catch(Exception e){
            System.out.println("website not found");
        }
        System.out.println("Links collected: " + tovisited.size());
        }
         System.out.println("\nCrawling finished!");
        System.out.println("Total pages visited: " + visited.size());
    }
}

