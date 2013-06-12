package networking;
/**
 * Board.java
 * Board for a player.
 * @author Kelsey LaPointe
 * @author Matthew Koval
 */
public class Board {
	static int c = 11;
	static int r = 9;
	Marker grid[][];
	Marker V;
	Marker N;
	Marker T;
	Marker m;
	/**Constructor for the board for player of playerNumber.
	 * @param playerNum
	 */
	public Board(int playerNum){
		V = new Marker(false, playerNum, true);//vacant
		N = new Marker(false, 0, true);//No mans land, not part of grid
		T = new Marker(false, playerNum, false);//Temporary Markers
		m = new Marker(true, playerNum, false);
		grid=createBoard(r,c);
	}
	/**Remove all temporary markers and replace them with vacant.
	 * 
	 */
	public void clearTemps()
	{
		for(int i = 0; i<r;i++)
		{
			for(int j=0;j<c;j++)
			{
				if(grid[i][j]==T)
				{
					grid[i][j]=V;
				}
			}
		}
	}//method
	/**change all temporary markers to permanent
	 * 
	 */
	public void tempsToPerms()
	{
		for(int i = 0; i<r;i++)
		{
			for(int j=0;j<c;j++)
			{
				if(grid[i][j]==T)
				{
					grid[i][j]=m;
				}
			}
		}
	}//method
	/**check to see if the game is won.
	 * @return
	 */
	public boolean checkBoardForWin()
	{
		boolean result = false;
		int count=0;
		for(int i=0;i<c;i++)
		{
			if(!checkColumn(i+2))
			{
				count++;
			}
		}
		if(count>2)
		{
			result=true;
		}
		return result;
	}//Should return true if the game has been won by the player who owns the board.
	/**Find out how many temporary markers are on the grid.
	 * @return
	 */
	public int checkForNumTemps()
	{
		int count=0;
		for(int i=0;i<r;i++)
		{
			for(int j=0;j<c;j++)
			{
				if(grid[i][j]==T)
				{
					count++;
				}
			}
		}
		return count;
	}//return the number of temp markers found on the board.
	/**check the column to see if there is a marker in the last spot in column num-2
	 * @param num
	 * @return
	 */
	public boolean checkColumn(int num)
	{
		boolean result=true;
		for(int i=0;i<r;i++)
		{
			try
			{
				if(grid[i+1][num-2]==N && (grid[i][num-2]==m || grid[i][num-2]==T))
				{
					result = false;
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.err.println("Traversed to far."+e.getMessage());
			}
		}
		return result;
	}/*return false if the column isn't valid.*/
	/**Get a string thats formatted in a 2d grid that represents the board.
	 * @return
	 */
	public String printBoard()
	{
		String board="";
		for(int i=0;i<r;i++)
		{
			for(int j=0;j<c;j++)
			{
				String temp;
				if(grid[i][j]==T)
				{
					temp="T";/*T for Temporary*/
				}//if
				else if(grid[i][j]==N)
				{/*Not Possible spot*/
					temp=("N");
				}//else if
				else if(grid[i][j]==m)
				{/*Not Possible spot*/
					temp=("M");
				}//else if
				else/*(grid[i][j].vacant==true)*/
				{
					temp="V";/*V for vacant */
				}//if
				board=board+" "+temp;
			}//inner for loop (c)
			board=board+"\n";
		}//outer for loop (r)
		return board;
	}//method
	/**Create a board for a player with row length r and col length c
	 * @param r
	 * @param c
	 * @return
	 */
	private Marker[][] createBoard(int r,int c){
		grid = new Marker[r][c];
		for(int i=0; i<1; i++){
			for(int j=0; j<3; j++){
				grid[j][i]= V;
			}
			for(int j=3; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=1; i<2; i++){
			for(int j=0; j<4; j++){
				grid[j][i]= V;
			}
			for(int j=4; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=2; i<3; i++){
			for(int j=0; j<5; j++){
				grid[j][i]= V;
			}
			for(int j=5; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=3; i<4; i++){
			for(int j=0; j<6; j++){
				grid[j][i]= V;
			}
			for(int j=6; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=4; i<5; i++){
			for(int j=0; j<7; j++){
				grid[j][i]= V;
			}
			for(int j=7; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=5; i<6; i++){
			for(int j=0; j<8; j++){
				grid[j][i]= V;
			}
			for(int j=8; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=6; i<7; i++){
			for(int j=0; j<7; j++){
				grid[j][i]= V;
			}
			for(int j=7; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=7; i<8; i++){
			for(int j=0; j<6; j++){
				grid[j][i]= V;
			}
			for(int j=6; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=8; i<9; i++){
			for(int j=0; j<5; j++){
				grid[j][i]= V;
			}
			for(int j=5; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=9; i<10; i++){
			for(int j=0; j<4; j++){
				grid[j][i]= V;
			}
			for(int j=4; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=10; i<11; i++){
			for(int j=0; j<3; j++){
				grid[j][i]= V;
			}
			for(int j=3; j<9; j++){
				grid[j][i]= N;
			}
		}
		return grid;
	}//method
	/*
	 * Given a column number will return what the last available marker
	 * spot is containing. If it is V then it is not closed, anything 
	 * else and it should be a blocked column.
	 */
	/**Check to see if there is a marker in the final spot in the column j
	 * @param j
	 * @return
	 */
	public Marker isMarkerInFinalSpot(int j){
		Marker fin=null;
		for(int i=0;i<r;i++)
		{
			try
			{
				if(grid[i][j]==N&&grid[i-1][j]!=N){
					fin = grid[i][j];
				}//if
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.err.println("Traversed to far.");
			}
		}//for
		return fin;
	}
	/**call the removeRepeatedPermanentMarkers(int j) so that it hits every column.
	 * 
	 */
	public void removeRepeatedPermanentMarkers()
	{
		for(int i=2;i<13;i++)
		{
			removeRepeatedPermanentMarkers(i);
		}
	}
	/**Remove all permanent markers from a column j except for the highest permanent marker.
	 * @param j
	 */
	private void removeRepeatedPermanentMarkers(int j)
	{
		boolean foundFirstM = false;
		for(int i = (r-1);i>=0;i--)
		{
			if(grid[i][j-2]==m)
			{
				if(foundFirstM)
				{
					System.out.println("Found an M at position: "+(i+1));//debug
					grid[i][j-2]=V;
				}
				else
				{
					System.out.println("Found the highest M at position: "+(i+1));//debug
					foundFirstM=true;
				}
			}
		}
		System.out.println("All unnecessary M's should be removed for the number: " + j);//debug
	}
	/**place a temporary marker at position [i][j]
	 * @param i
	 * @param j
	 */
	public void placeTemp(int i, int j){
			grid[i][j-2]=T;
	}
	/**Move a temp marker up spot amounts at position [i][j] 
	 * @param i
	 * @param j
	 * @param spot
	 */
	public void moveTemp(int i, int j, int spot){
		grid[i+spot][j]=T;
		grid[i][j]=V;
	}
	/** Switch all temporary markers to permanent markers
	 * 
	 */
	public void placePerma (){
		for(int i=0; i<c; i++){
			for(int j=0; j<r; j++){
				if(grid[j][i]==T){
					grid[j][i]=m;
				}
			}
		}
	}
	/**Remove a marker at position [i][j]
	 * @param i
	 * @param j
	 */
	private void removeMarker(int i, int j){
		grid[i][j]=V;
	}
	/**swap out all instances of look for with swap in the grid.
	 * @param lookFor
	 * @param swap
	 */
	public void swapOut(Marker lookFor, Marker swap)
	{
		for(int i=0;i<r;i++)
		{
			for(int j=0;j<c;j++)
			{
				if(grid[i][j]==(lookFor));
				{
					grid[i][j]=swap;
				}//if
			}//inner for
		}//outer for
	}//method
}
