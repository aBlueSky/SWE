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
		Board board1 = new Board(1);
		Board board2 = new Board(2);
		
		//player connections
		player1=server.connect(player1);
		//Create IO for Player 1
		BufferedReader p1Reader = server.connectReader(player1);
		PrintWriter p1Writer = server.connectWriter(player1);
		
		player2=server.connect(player2);
		//Create IO for Player 2
		BufferedReader p2Reader = server.connectReader(player2);
		PrintWriter p2Writer = server.connectWriter(player2);
		
		//Time to play the game
		boolean playing = true;/*A boolean value that will
		 						be true as long as the game
		 						is running and needs to continue
		 						running*/
		System.out.println(board1.printBoard());//Print board for P1
		System.out.println(board2.printBoard());//Print board for P2
		while(playing)
		{
			playerTurn(p1Reader, p1Writer, p2Writer, 1, board1, board2);
							/*player 1's turn, giving both P1 IO ,
							P2 Writer and P1's player number and the board for the players*/
			System.out.println(board1.printBoard());//Print board for P1
			playing=!isGameWon(board1, board2);//check win condition
			if(playing)
			{//if turnA didn't win
				p2Writer.println("go");//Tell next player to go.
				playerTurn(p2Reader, p2Writer, p1Writer, 2, board2, board1);
						/*player 2's turn, giving both P2 IO ,
						P1 Writer and P2's player number and the board for the players*/
				System.out.println(board2.printBoard());//Print board for P2
				playing=!isGameWon(board1, board2);//check win condition
				if(playing)
				{
					p1Writer.println("go");//Tell next player to go.
				}//if
				else
				{
					p2Writer.println("you won");
					p1Writer.println("you lost");
				}//else -- P2 win
			}//if
			else
			{
				p1Writer.println("you won");
				p2Writer.println("you lost");
			}//else -- P1 win
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
			valid = true;
		}
		
		if((2<=b)&&(12>=b)){
			valid = true;
		}
		return valid;
	}
	/* Checks to see if there are temporary markers already placed on the 
	 * given board and returns true if at least one temp marker is placed
	 * and there is a possibility that it was chosen by the user with a 
	 * valid roll.
	 */
	private static boolean checkBusted(Board board){
		boolean busted = false;
		boolean match = false;
		int counter = 0;
		int place1=0;
		int place2=0;
		int place3=0;
		int match0 = 0;
		for(int i=0; i<11; i++){
			for(int j=0; j<9; j++){
				if(board.grid[i][j]==board.T){
					counter ++;
					if(place1==0){
						place1=i+2;
					}//else if
					else if(place2==0){
						place2=i+2;
					}//else if
					else if(place3==0){
						place3=i+2;
					}//else if
				}//if
			}//inner for
		}//outer for -- finds temp markers
		/*
		 * Finds out if the place where it matches the dice roll has a vacant spot.
		 */
		for(int i=0; i<3; i++){
			for(int j=i+1; j<4; j++){
				match0=dice[i]+dice[j];
				if(((match0==place1&&board.isMarkerInFinalSpot(place1)==board.V)||
					(match0==place2&&board.isMarkerInFinalSpot(place2)==board.V)||
					(match0==place3&&board.isMarkerInFinalSpot(place3)==board.V)))
				{
					match=true;
				}//if
			}//inner for
		}//outer for
		if(counter == 3 && match != true){
			busted = true;
		}// 3 temp markers and there is no match can be made with the given rolls.
		return busted;
	}
	private static boolean isGameWon(Board board1, Board board2){
		boolean gameWon = false;
		int counter = 0;
		int counter2 = 0;
		for(int c=0; c<11; c++){
			for(int r=0; r<9; r++){
				if(board1.isMarkerInFinalSpot(c)== board1.m){
					counter++;
				}
				else if(board1.isMarkerInFinalSpot(c)== board2.m){
					counter2++;
				}
			}
		}
		if(counter == 3){
			gameWon = true;
		}
		else if(counter2 == 3){
			gameWon = true;
		}
		return gameWon;
	}
	private static void playerTurn(BufferedReader reader, PrintWriter writer,
									 PrintWriter otherPlayer, int playerNum, Board boardPrimary,
									 Board boardSecondary)
	{/*
	 * Return true if the turn is done for the active player.
	 */
		boolean done=false;/*Marks whether the player's turn is done.*/
		try
		{
			while (!done)
			{
				String line = reader.readLine();
		           if (line == null) { done = true; }
		           else {
		              if (line.trim().equals("stop")) {
		            	  //switch temp markers to PERMANENT 
		            	  boardPrimary=boardPrimary.tempsToPerms(boardPrimary);
		                 done = true;
		              }//if
		              else if (line.trim().equals("roll")) {
		            	  roll();
			              writer.println(""+concatRoll());
			              otherPlayer.println(""+concatRoll());
			          }// else if
		              else
		              {
		            	 if(line.equals("crap"))
		            	 {
		            		//Player crapped out need to add the remove temp markers method.
		            		writer.println("ack");
		            		boardPrimary=boardPrimary.changeTemps(boardPrimary);
		            		done=true;
		            	 }//if
		            	 else
		            	 {/*player should have input 2 numbers delimited by a ','*/
			            	  /*input should be in form "a,b" where
			            	   a and b are int's delimited by ',' */
			            	  String a=null;
		            		  String b=null;
			            	  try
			            	  {
			            		  otherPlayer.println(line);//echo to other player
			            		  String[] list=line.split(",");
			            		  //try the 2 numbers a, b which should be in positions 0 and 1 of list.
			            		  a = list[0];
			            		  b = list[1];
			            		  int num1 = (int)Integer.parseInt(a);
		            			  int num2 = (int)Integer.parseInt(b);
			            		  if (checkRoll(num1,num2)
			            				  /*&&!checkBusted(boardPrimary)
			            				  &&!checkBusted(boardSecondary)*/)
			            		  {
			            			  //Player combination is valid as far as 2<=x<=12
			            			  //add or increment marker positions
			            			  System.out.println("Valid roll and choice.");
			            			  int loc1=num1;
			            			  int loc2=num2;
			            			  if(num1==num2)
			            			  {
			            				  boolean tempExistsAlready=false;
			            				  for(int i=0;i<Board.r;i++)
			            				  {
			            					  if(boardPrimary.grid[i][num1-1]==boardPrimary.T)
			            					  {
			            						  loc1=i;
			            						  tempExistsAlready=true;
			            					  }
			            				  }
			            				  if(tempExistsAlready)
			            				  {
		            						  boardPrimary.moveTemp(loc1,num1-2,2);
			            				  }
			            				  else
		            					  {
		            						  boardPrimary.placeTemp(0,num1);
		            						  boardPrimary.moveTemp(0,num1-2,1);
		            					  }
			            			  }//if -- matching numbers
			            			  else
			            			  {
			            				  boolean tempExistsAlready=false;
			            				  for(int i=0;i<Board.r;i++)
			            				  {
			            					  if(boardPrimary.grid[i][num1-2]==boardPrimary.T)
			            					  {
			            						  loc1=i;
			            						  tempExistsAlready=true;
			            					  }
			            				  }
			            				  if(tempExistsAlready)
			            				  {
		            						  boardPrimary.moveTemp(loc1,num1-2,1);
			            				  }
			            				  else
		            					  {
		            						  boardPrimary.placeTemp(0,num1);
		            					  }
			            				  
			            				  tempExistsAlready=false;
			            				  for(int i=0;i<Board.r;i++)
			            				  {
			            					  if(boardPrimary.grid[i][num2-2]==boardPrimary.T)
			            					  {
			            						  loc2=i;
			            						  tempExistsAlready=true;
			            					  }
			            				  }
			            				  if(tempExistsAlready)
			            				  {
		            						  boardPrimary.moveTemp(loc2,num2-2,1);
			            				  }
			            				  else
		            					  {
		            						  boardPrimary.placeTemp(0,num2);
		            					  }
			            			  }//non matching numbers
			            			  System.out.println(boardPrimary.printBoard());
			            			  System.out.println("Valid 2<=x<=12");
			            		  }//if -- crap check
			            		  else{
			            			//Player crapped out need to add the remove temp markers method.
			            			  System.out.println("Crapping out");
					            		boardPrimary=boardPrimary.changeTemps(boardPrimary);
					            		writer.println("ack");
					            		done=true;
			            		  }//crapped out
			            	  }//try
			            	  catch(Exception e)
			            	  {
			            		  throw e;
			            		  //System.err.println("Error with turn function.");
			            	  }//catch
			            	  System.out.println(boardPrimary.printBoard());
			               }//assume the 2 desired dice combinations were passed.
			           }//inner else
		           }//outer else
			}//while
		}//try
		catch (IOException e) {
			done=true;
	        System.err.println("Unable to read from or write to the client: "
	                           + e.getMessage());
	     }//Catch
	}//method

}
