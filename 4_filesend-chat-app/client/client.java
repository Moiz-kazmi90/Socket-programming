import java.io.*;
import java.util.*;
import java.net.*;

public class client {
    public static void main(String[] args) throws IOException {
        Socket soc = new Socket("localhost",9000);
        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
        Scanner sc = new Scanner(System.in);

        while(true) {
        System.out.println("enter the message (or 'get filename' to download file):");
        String str = sc.nextLine();
        out.println(str);

        if(str.equalsIgnoreCase("bye")){
            System.out.println("client disconnected");
            break;
        }
        String line = in.readLine();
        if(line.equals("filecontent")){
            
            //  System.out.println("enter the message (or 'get filename' to download file):");

            BufferedWriter writer = new BufferedWriter(new FileWriter("downloaded" + str.substring(4).trim()));
              while(!(line = in.readLine()).equals("End")){
                System.out.println(line);
                writer.write(line);
                writer.newLine();
              }
               writer.close();
                System.out.println("File saved successfully!");
        }else{
                // normal messages or dir
            System.out.println("server says:");
                while(!line.equals("End")) {
                    System.out.println(line);
                    line = in.readLine();
                }
        }
        }
        soc.close();
    }
}




// import java.io.*;
// import java.util.*;
// import java.net.*;

// public class client {
//     public static void main(String[] args) throws IOException {
//         Socket soc = new Socket("localhost",9000);
//         BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
//         PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
//         Scanner sc = new Scanner(System.in);

//         while(true) {
//             System.out.println("enter the message (or 'get filename' to download file):");
//             String reply = sc.nextLine();
//             out.println(reply);

//             if(reply.equalsIgnoreCase("bye")) {
//                 System.out.println("client disconnected");
//                 break;
//             }

//             String line = in.readLine();
//             if(line.equals("filecontent")) {
//                 // server is sending file content
//                 System.out.println("File received from server. Saving...");
//                 BufferedWriter writer = new BufferedWriter(new FileWriter("downloaded_"+reply.substring(4).trim()));
//                 while(!(line = in.readLine()).equals("End")) {
//                     System.out.println(line); // display content
//                     writer.write(line);
//                     writer.newLine();
//                 }
//                 writer.close();
//                 System.out.println("File saved successfully!");
//             } else {
//                 // normal messages or dir
//                 System.out.println("server says:");
//                 while(!line.equals("End")) {
//                     System.out.println(line);
//                     line = in.readLine();
//                 }
//             }
//         }
//         soc.close();
//     }
// }
