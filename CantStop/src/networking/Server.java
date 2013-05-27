package networking;
import java.io.*;
import java.net.*;

public class Server {
	//Instance variables
	ServerSocket s = null;
	Socket player = null;
	int portNumber;
	BufferedReader in = null;
    PrintWriter out = null;
    static int numConnected=0;
	
	//Initialize Server and ServerSocket
	public Server()	{
		portNumber=2043;
		try{
			s=new ServerSocket(portNumber);
		}//try
		catch(IOException e){
			System.err.println("Could not listen on port: "+portNumber+". " + e.getMessage());
	        System.exit(-1);
		}//catch
	}//Method
	//incoming
	public Socket connect(Socket player)
	{
		try {
	         player = s.accept( );
	         System.out.println("Player connected.");
	         out = new PrintWriter(player.getOutputStream(), true);
	         out.println(++numConnected);
	      }//try
	      catch (IOException e) {
	         System.err.println("Accept failed: " + e.getMessage());
	         System.exit(-1);
	      }//catch
		return player;
	}//method
	public BufferedReader connectReader(Socket socket)
	{
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        System.out.println("Reader Connected");
	     }//try
	     catch (IOException e) {
	        System.err.println("Unable to read from or write to the client: "
	                           + e.getMessage());
	     }//Catch
		return in;
	}//Method
	public PrintWriter connectWriter(Socket socket)
	{
		try{
			out = new PrintWriter
	                (socket.getOutputStream(), true /* autoFlush */);
	        System.out.println("Writer connected");
	        
	        /*out.println( "Hello! Enter BYE to exit." );
	        boolean done = false;
	        while (!done) {
	           String line = in.readLine();
	           if (line == null) { done = true; }
	           else {
	              out.println("Echo: " + line);
	              if (line.trim().equals("BYE")) {
	                 done = true;
	              }//if
	           }//else
	        }//while*/
		}//try
        catch (IOException e) {
	        System.err.println("Unable to read from or write to the client: "
	                           + e.getMessage());
	     }//Catch
		return out;
	}//method
	//close the connections
	public void close()
	{
		try {
			 out.close();
	         in.close();
	         s.close();
	      }//try
	      catch (IOException e) {
	         System.err.println("Unable to close writer, reader, or socket: "
	                            + e.getMessage());
	      }//catch
		System.out.println("Everything closed.");
	}//method
}//class
