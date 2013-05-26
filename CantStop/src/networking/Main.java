package networking;
import java.net.*;
import java.io.*;

public class Main {
	public static void main(String[] args)
	{
		Server server = new Server();
		Socket player1 = new Socket();
		Socket player2 = new Socket();
		
		//player connections
		System.out.println("Player connections");
		//IO for player 1
		System.out.println("IO for P1");
		player1 = server.connect(player1);
		BufferedReader p1Reader = server.connectReader(player1);
		PrintWriter p1Writer = server.connectWriter(player1);
		//IO for player 2
		System.out.println("IO for P2");
		//player2 = server.connect(player2);
		//BufferedReader p2Reader = server.connectReader(player2);
		//PrintWriter p2Writer = server.connectWriter(player2);
		
		//First output to playerSystem.out.println("Player Number IO");
		System.out.println("Write lines");
		p1Writer.write("1");
		//p2Writer.write("2");
		
		//close connections
		System.out.println("Close connections");
		player1.close();
		player2.close();
		server.close();
	}
}
