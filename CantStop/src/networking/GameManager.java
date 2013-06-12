package networking;
import java.net.*;
import java.io.*;

public class GameManager 
{
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
				}//if - playing for turn switch.
				else
				{
					p2Writer.println("you won");
					p1Writer.println("you lost");
				}//else -- P2 win
			}//if - playing
			else
			{
				p1Writer.println("you won");
				p2Writer.println("you lost");
			}//else -- P1 win
		}//while - playing for turn A.
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
		} //try - close reader.
		catch (IOException e) {
			System.err.println("Unable to close reader: "
					+ e.getMessage());
		}//catch - close reader.
		p2Writer.close();
		server.close();
	}//Main method

	private static int[] roll(){
		for(int i=0; i<dice.length; i++){
			dice[i]= (int) (6*Math.random()+1);
		}

		return dice;
	}//method - roll
	private static String concatRoll(int[] diceArray)
	{
		String results="";
		for(int i=0;i<diceArray.length;i++)
		{
			results=results+diceArray[i];
			if(i<(diceArray.length-1))
			{
				results=results+",";
			}//if
		}//for
		return results;
	}//method - concat roll

	private static boolean checkRoll(int a, int b){
		boolean valid = false;
		if((2<=a)&&(12>=a)){
			valid = true;
		}

		if((2<=b)&&(12>=b)){
			valid = true;
		}
		return valid;
	}//method - check roll
	/* Checks to see if there are temporary markers already placed on the 
	 * given board and returns true if at least one temp marker is placed
	 * and there is a possibility that it was chosen by the user with a 
	 * valid roll.
	 */
	private static boolean checkCombinations(int[] dice, int a, int b)
	{
		boolean result = false;
		boolean aPrime = false;
		boolean bPrime = false;
		int possibilities[]=new int[12];
		int spot=0;
		for(int i=0;i<dice.length;i++)
		{
			for(int j=0;j<dice.length;j++)
			{
				if(i!=j)
				{
					possibilities[spot++]=(dice[i]+dice[j]);
				}
			}
		}//set up combination possibilities.
		for(int i=0;i<possibilities.length;i++)
		{
			if(a==possibilities[i])
			{
				aPrime=true;
				possibilities[i]=0;
			}
			if(b==possibilities[i])
			{
				bPrime=true;
				possibilities[i]=0;
			}
		}/*if a or b matches a possible combination set the
		 boolean value to true for that variable*/
		if(aPrime && bPrime)
		{
			result=true;
		}/*if both are true then the two desired choices are valid*/
		System.out.println("aPrime: "+aPrime);
		System.out.println("bPrime: "+bPrime);
		System.out.println("Both valid: "+result);
		return result;
	}//method - check combinations.
	public boolean checkBusted(Board board)
	{
		boolean result = false;



		return result;
	}//Return true if the player busted.
	/*
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
	 *//*
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
	}//method - check busted.*/
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
	}//method - isGameWon
	private static void playerTurn(BufferedReader reader, PrintWriter writer,
			PrintWriter otherPlayer, int playerNum, Board boardPrimary,
			Board boardSecondary)
	{/*
	 * Return true if the turn is done for the active player.
	 */
		boolean done=false;/*Marks whether the player's turn is done.*/
		System.out.println(playerNum + ":\n"+boardPrimary.printBoard());//Print board for current player
		try
		{
			int tempDice[]=new int[4];/*only stored during this player's
			 							turn and until the next roll.*/
			boolean freshRoll = false;
			TURN: while (!done)
			{
				int wasAbleToPlaceATemp = 2;/*2 at the start;
											1 if one was not able to be placed;
											0 if neither were placed*/
				String storedConcatRoll = "";
				int turnDice[];
				String line = reader.readLine();
				if (line == null) 
				{ 
					done = false;
				}//Assume turn is not done with empty input.
				else if (line.trim().equals("stop")&&freshRoll==false) 
				{//switch temp markers to PERMANENT 
					boardPrimary.tempsToPerms();
					boardPrimary.removeRepeatedPermanentMarkers();
					done = true;
				}//if - stop, can't stop if you've rolled.
				else if (line.trim().equals("roll")&&freshRoll==false) 
				{
					freshRoll=true;
					turnDice=roll();
					tempDice=turnDice;
					storedConcatRoll=concatRoll(turnDice);
					writer.println(storedConcatRoll);
					otherPlayer.println(storedConcatRoll);
				}// else if, can only roll if you've not rolled without using the roll.
				else if(line.equals("crap")&&freshRoll==true)
				{
					//Player crapped out need to add the remove temp markers method.
					writer.println("ack");//ack means acknowledged
					System.out.println("crap");//debug
					boardPrimary.clearTemps();
					System.out.println(boardPrimary.printBoard());
					done=true;
				}//else if - crap, can only crap out if you've got a fresh roll.
				else if (line.matches("\\d*[,]{1}\\d*")&&freshRoll==true)
				{/*player should have input 2 numbers delimited by a ','*/
					/*input should be in form "a,b" where
   					a and b are int's delimited by ',' */
					String a=null;
					String b=null;
					System.out.println("Combination entered.");//debug
					try
					{
						String[] list=line.split(",");
						//try the 2 numbers a, b which should be in positions 0 and 1 of list.
						a = list[0];	
						b = list[1];
						System.out.println("A: "+a+"; B: "+b);//debug
						int num1 = (int)Integer.parseInt(a);
						int num2 = (int)Integer.parseInt(b);
						System.out.println("A: "+num1+"; B: "+num2);//debug
						System.out.println("CheckRoll and CheckCombination about to be called.");

						if (checkRoll(num1,num2) && checkCombinations(tempDice, num1, num2)
								/*&&!checkBusted(boardPrimary)
  								&&!checkBusted(boardSecondary)*/)
						{/*removed check busted until further notice in this condition on the if*/
							//Player combination is valid as far as 2<=x<=12
							//add or increment marker positions
							System.out.println("Valid roll and choice.");//debug
							writer.println("ack");//acknowledge valid choice
							otherPlayer.println(line);//echo to other player
							int loc1=num1;
							int loc2=num2;
							System.out.println("First Number");//debug
							boolean tempExistsAlready=false;
							boolean found=false;
							for(int i=0;i<Board.r;i++)
							{
								if(boardPrimary.grid[i][num1-2]==boardPrimary.T)
								{
									loc1=i;
									tempExistsAlready=true;
									found = true;
								}//if
								if(boardPrimary.grid[i][num1-2]==boardPrimary.m)
								{
									loc1=i;
									found = true;
								}//if
							}//for
							if(found)
							{
								if(tempExistsAlready)
								{
									System.out.println("-Temporary Marker Exists Already.");//debug
									boardPrimary.moveTemp(loc1,num1-2,1);
								}//Temporary Marker exists in column.
								else
								{
									System.out.println("-Permanent Marker Exists Already.");//debug
									if (boardPrimary.checkForNumTemps()<3) {
										boardPrimary.placeTemp(loc1 + 1, num1);
									}
									else
									{
										//bust
										System.out.println("Bust");//debug
										wasAbleToPlaceATemp--;
										System.out.println("Chances Left: "+ wasAbleToPlaceATemp);//debug
									}
								}//Found but no temp exists.
							}//if - found
							else
							{
								System.out.println("-No Marker Found.");//debug
								if (boardPrimary.checkForNumTemps()<3) {
									boardPrimary.placeTemp(0, num1);
								}//allowed to not be able to place the second temp
								else
								{
									//bust
									System.out.println("Bust");//debug
									wasAbleToPlaceATemp--;
									System.out.println("Chances Left: "+ wasAbleToPlaceATemp);//debug
								}
							}//Nothing found in column.
							System.out.println("Second Number");//debug
							found=false;
							tempExistsAlready=false;
							for(int i=0;i<Board.r;i++)
							{
								if(boardPrimary.grid[i][num2-2]==boardPrimary.T)
								{
									loc2=i;
									tempExistsAlready=true;
									found = true;
								}//if
								if(boardPrimary.grid[i][num2-2]==boardPrimary.m)
								{
									loc2=i;
									found = true;
								}//if
							}
							if(found)
							{
								if(tempExistsAlready)
								{
									System.out.println("-Temporary Marker Exists Already.");//debug
									boardPrimary.moveTemp(loc2,num2-2,1);
								}//found and temp exists in column.
								else
								{
									System.out.println("-Permanent Marker Exists Already.");//debug
									if (boardPrimary.checkForNumTemps()<3) {
										boardPrimary.placeTemp(loc2 + 1, num2);
									}
									else
									{
										//bust
										System.out.println("Bust");//debug
										wasAbleToPlaceATemp--;
										System.out.println("Chances Left: "+ wasAbleToPlaceATemp);//debug
									}
								}//Found but no temp exists.
							}//if - found
							else
							{
								System.out.println("-No Marker Found.");//debug
								if (boardPrimary.checkForNumTemps()<3) {
									boardPrimary.placeTemp(0, num2);
								}
								else
								{
									//bust
									System.out.println("Bust");//debug
									wasAbleToPlaceATemp--;
									System.out.println("Chances Left: "+ wasAbleToPlaceATemp);//debug
								}
							}//Nothing found in column.
						}//end of handling the numbers.
						else
						{
							System.out.println("Crapping out");//debug
							boardPrimary.clearTemps();
							writer.println("ack");
							done=true;
						}//Player "crapped" out
						if(wasAbleToPlaceATemp<1)
						{
							//Bust
							System.out.println("Busted out.");//debug
							boardPrimary.clearTemps();
							writer.println("ack");
							done=true;
						}
					}//try
					catch(Exception e)
					{
						System.err.println("Error with turn function." + e.getMessage());
					}//catch
					System.out.println(boardPrimary.printBoard());//debug
					freshRoll=false;
				}//assume the 2 desired dice combinations were passed. Can't do this until you've rolled.
				else
				{
					System.out.println("Unhandled line: "+line);//debug
					writer.println("err");
				}//unhandled yet, skip to next input.

			}//while
		}//try
		catch (IOException e) 
		{
			done=true;
			System.err.println("Unable to read from or write to the client: "
					+ e.getMessage());
		}//Catch
	}//method
}//class