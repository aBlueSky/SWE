package networking;

public class Board {
	int c = 11;
	int r = 9;
	Marker grid[][];
	Marker V = new Marker(false, 0, true);//vacant
	Marker N = new Marker(false, 0, true);//No mans land, not part of grid
	Marker T = new Marker(false, 0, false);//Temporary Markers
	Marker mOne = new Marker(true, 1, false);
	Marker mTwo = new Marker(true, 2, false);
	public Board(){
		grid=createBoard(r,c);
	}
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
				{/*player number of zero means no player*/
					temp=(""+grid[i][j].playerNum);
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
		Marker grid[][] = new Marker[r][c];
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
	private boolean addMarker(int playerNum, int i, int j){
		boolean added = false;
		if(playerNum==1){
			grid[i][j]=mOne;
			added = true;
		}
		else if(playerNum==2){
			grid[i][j]=mTwo;
			added = true;
		}
		return added;
	}
	private boolean isVacant(int i, int j){
		boolean vacant = false;
		if(grid[i][j]==V){
			vacant = true;
		}
		return vacant;
	}
	private boolean isMarkerInFinalSpot(int i, int j){
		boolean fin = false;
		if(grid[i+1][j]==N){
			fin = true;
		}
		return fin;
	}
	private void removeMarker(int i, int j){
		
	}
}