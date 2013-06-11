package networking;

public class Board {
	static int c = 11;
	static int r = 9;
	Marker grid[][];
	Marker V;
	Marker N;
	Marker T;
	Marker m;
	public Board(int playerNum){
		V = new Marker(false, playerNum, true);//vacant
		N = new Marker(false, 0, true);//No mans land, not part of grid
		T = new Marker(false, playerNum, false);//Temporary Markers
		m = new Marker(true, playerNum, false);
		grid=createBoard(r,c);
	}
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
	}
	public int checkForNumTemps()
	{
		int count=0;
		
		return count;
	}
	public boolean checkColumn(int num)
	{
		boolean result=true;
		for(int i=0;i<r;i++)
		{
			try
			{
				if(grid[i+1][num-2]==N && (grid[i][num-2]==T || grid[i][num-2]==T))
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

	private boolean isVacant(int i, int j){
		boolean vacant = false;
		if(grid[i][j]==V){
			vacant = true;
		}
		return vacant;
	}
	/*
	 * Given a column number will return what the last available marker
	 * spot is containing. If it is V then it is not closed, anything 
	 * else and it should be a blocked column.
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
	public void removeRepeatedPermanentMarkers()
	{
		for(int i=2;i<13;i++)
		{
			removeRepeatedPermanentMarkers(i);
		}
	}
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
	public void placeTemp(int i, int j){
			grid[i][j-2]=T;
	}
	public void moveTemp(int i, int j, int spot){
		grid[i+spot][j]=T;
		grid[i][j]=V;
	}
	public void placePerma (){
		for(int i=0; i<c; i++){
			for(int j=0; j<r; j++){
				if(grid[j][i]==T){
					grid[j][i]=m;
				}
			}
		}
	}
	private void removeMarker(int i, int j){
		grid[i][j]=V;
	}
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
