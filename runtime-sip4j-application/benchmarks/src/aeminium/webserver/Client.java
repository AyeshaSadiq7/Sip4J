package aeminium.webserver;
import java.io.*;
import java.net.*;
import java.util.*;
	 
	public class Client {
	 
	   public static void main(String argv[])
	      {
		   long start = System.nanoTime();
		   try{
			    Socket socketClient = new Socket("localhost",3330);
			    
			    System.out.println("Client: "+"Connection Established");
	 
			    BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
	 
			    BufferedWriter writer= 
		        		new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			    String serverMsg;
			    writer.write("12\r\n");
			    writer.write("10\r\n");
	            writer.flush();
				while((serverMsg = reader.readLine()) != null){
					System.out.println("Client: " + serverMsg);
				}
				long elapsed = System.nanoTime()-start;
				System.out.println("time="+elapsed/1000000);
		   }catch(Exception e){e.printStackTrace();}
	      }
	}
	
