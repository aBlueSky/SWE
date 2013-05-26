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
		server.connect(player1);
		server.connect(player2);
		//IO for player 1
		System.out.println("IO for P1");
		BufferedReader p1Reader = server.connectReader(player1);
		PrintWriter p1Writer = server.connectWriter(player1);
		//IO for player 2
		System.out.println("IO for P2");
		BufferedReader p2Reader = server.connectReader(player2);
		PrintWriter p2Writer = server.connectWriter(player2);
		
		//First output to player
		System.out.println("Player Number IO");
		p1Writer.write("1");
		p2Writer.write("2");
	}
}
