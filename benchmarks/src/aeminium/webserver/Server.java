package aeminium.webserver;
import java.io.*;
import java.net.*;
public class Server {
	
	public static String ROOT = "/Users/asad22/Research/test/";
	
	public static Socket client = null;
	
	public static void main(String argv[]) throws Exception
    {

 		 System.out.println(" Server is Running  " );
       ServerSocket mysocket = new ServerSocket(5559);
       
       
       while(true)
       {
          client = mysocket.accept();

          BufferedReader reader =
          		new BufferedReader(new InputStreamReader(client.getInputStream()));
          BufferedWriter writer= 
          		new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

          writer.write("*** Welcome to the Calculation Server (Addition Only) ***\r\n");    
          
          writer.write("*** Please type in the first number and press Enter : \n");
          writer.flush();
          String data1 = reader.readLine().trim();
          writer.write("*** Please type in the second number and press Enter : \n");
          writer.flush();
          String data2 = reader.readLine().trim();

          int num1=Integer.parseInt(data1);
          int num2=Integer.parseInt(data2);

          int result=num1+num2;            
          System.out.println("Addition operation done " );

          writer.write("\r\n=== Result is  : "+result);
          writer.flush();
  			client.close();
       }
    }
}

