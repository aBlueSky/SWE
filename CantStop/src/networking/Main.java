package networking;
import java.net.*;
import java.io.*;

public class Main {
	public static void main(String[] args)
	{
		Server server = new Server();
		Socket player1 = null;
		Socket player2 = null;
		
		//player connections
		System.out.println("Waiting for player connections");
		player1=server.connect(player1);
		//Create IO for Player 1
		BufferedReader p1Reader = server.connectReader(player1);
		PrintWriter p1Writer = server.connectWriter(player1);
		System.out.println("End of setting up IO for P1");
		
		player2=server.connect(player2);
		//Create IO for Player 2
		BufferedReader p2Reader = server.connectReader(player2);
		PrintWriter p2Writer = server.connectWriter(player2);
		System.out.println("End of setting up IO for P2");
		
		//close connections
		try {
			p1Reader.close();
		}//try 
		catch (IOException e) {
	         System.err.println("Unable to close reader: "
                     + e.getMessage());
		}//catch
		p1Writer.close();
		try {
			p2Reader.close();
		} catch (IOException e) {
	         System.err.println("Unable to close reader: "
                     + e.getMessage());
		}//catch
		p2Writer.close();
		server.close();
		System.out.println("Connections Closed.");
	}
}
