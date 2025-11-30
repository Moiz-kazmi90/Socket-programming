import java.io.*;
import java.net.*;
import java.util.*;


public class htmljavawriter{
public static void main(String[] args){
    String path = "/home/moiz/Desktop/socketprogramming/5_scraper.java/Date Region Rep ID Product.txt";
    File f1 = new File(path);
    try{
     Scanner sc = new Scanner(f1);   
     BufferedWriter out = new BufferedWriter(new FileWriter("/home/moiz/Desktop/socketprogramming/5_scraper.java/sales.html"));
     out.write("<html>");
     out.newLine();
     out.write("<head><title> this is my html page</title></head>");
     out.newLine();
     out.write("<body>");
     out.newLine();
     out.write("<h1>sales report</h1>");                                    
     out.newLine();
    out.write("<table border='1' cellspacing='0' cellpadding='5'>");
    out.newLine();
     
     boolean isFirstline = true;
    //  sc.nextLine();
     while(sc.hasNextLine()){
        String line = sc.nextLine().trim();
        String[] column = line.split("\t"); 
        // write table haeder
        if(isFirstline){
        out.write("<thead><tr>");
        for(String col : column){
            out.write("<td>" + col + "</td>");
        }
        out.write("</tr></thead>");
        out.newLine();
        out.write("</tbody>");
        out.newLine();
        isFirstline = false;      
        }
        // write table row
        else{
            out.write("<tr>");
            for(String col : column){
                out.write("<td>" + col + "</td>");
            }
            out.write("</tr>");
            out.newLine();
        }
     }
    //  close tag
    out.write("</tbody>");
    out.newLine();
    out.write("</table>");
    out.newLine();
     out.write("</body>");
     out.newLine();
     out.write("</html>");
     out.newLine();

     out.close();
     System.out.println("HTML file created successfully: sales.html");
    }catch(Exception e){
        System.out.println("file not found");
    }
} 
}
