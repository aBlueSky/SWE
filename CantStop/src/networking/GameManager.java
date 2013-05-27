package networking;
import java.net.*;
import java.io.*;

public class GameManager {
	static int[] dice = new int[4];
	
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
		
		//Time to play the game
		boolean playing = true;/*A boolean value that will
		 						be true as long as the game
		 						is running and needs to continue
		 						running*/
		while(playing)
		{
			boolean turnA = playerTurn(p1Reader, p1Writer);
			boolean turnB = playerTurn(p2Reader, p2Writer);
		}//while
		
		//Game is finished, close connections
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
		} //try
		catch (IOException e) {
	         System.err.println("Unable to close reader: "
                     + e.getMessage());
		}//catch
		p2Writer.close();
		server.close();
		System.out.println("Connections Closed.");
	}//Main method
	
	private static int[] roll(){
		for(int i=0; i<dice.length; i++){
			dice[i]= (int) (6*Math.random()+1);
		}
		
		return dice;
		
	}
	private static String concatRoll()
	{
		String results="";
		for(int i=0;i<dice.length;i++)
		{
			results=results+dice[i];
			if(i<(dice.length-1))
			{
				results=results+",";
			}//if
		}//for
		System.out.println(""+results);//debug code
		return results;
	}//method
	
	private static boolean checkRoll(int a, int b){
		boolean valid = false;
		if((2<=a)&&(12>=a)){
			for(int i=0; i<dice.length; i++){
				if(dice[i]==a){
					valid = true;
				}
			}
		}
		
		if((2<=b)&&(12>=b)){
			for(int i=0; i<dice.length; i++){
				if(dice[i]==b){
					valid = true;
				}
			}
		}
		return valid;
	}
	private static boolean playerTurn(BufferedReader reader, PrintWriter writer)
	{
		boolean done=false;/*Marks whether the player's turn is done.*/
		try
		{
			while (!done)
			{
				String line = reader.readLine();
		           if (line == null) { done = true; }
		           else {
		              writer.println("Echo: " + line);//debug code
		              if (line.trim().equals("stop")) {
		                 done = true;
		              }//if
		              else if (line.trim().equals("roll")) {
		            	  roll();
			              writer.println(""+concatRoll()); 
			              done = false;
			          }// else if
		              else
		              {
		            	  /*input should be in form "a,b" where
		            	   a and b are int's delimited by ',' */
		            	  String a=null;
	            		  String b=null;
		            	  try
		            	  {
		            		  String[] list=line.split(",");
		            		  /*ArrayList<String> list = (ArrayList<String>)
		            				  					(Arrays.asList(line.split(",")));
		            		  							/*To ArrayList of Strings*/
		            		  System.out.println(""+list.toString());
		            		  //try the 2 numbers a, b which should be in positions 0 and 1 of list.
		            		  a = list[0];
		            		  b = list[1];
		            		  if (checkRoll(Integer.parseInt(a),Integer.parseInt(b)))
		            		  {
		            			  //Player combination is valid as far as 2<=x<=12
		            			  System.out.println("Valid 2<=x<=12");
		            		  }//if
		            		  System.out.println(""+a+","+b);
		            	  }//try
		            	  catch(Exception e)
		            	  {
		            		  System.err.println("Invalid input.");
		            		  System.out.println(""+a+","+b);
		            	  }//catch
		              }//assume the 2 desired dice combinations were passed.
		           }//else
			}//while
			System.out.println("End of turn.");
		}//try
		catch (IOException e) {
			done=true;
	        System.err.println("Unable to read from or write to the client: "
	                           + e.getMessage());
	     }//Catch
		return done;
	}//method

}
