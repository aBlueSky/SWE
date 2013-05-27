package networking;
import java.util.*;

public class Board {
	Marker grid[][] = new Marker[9][11];
	Marker V = new Marker(false, 0, true);
	Marker N = new Marker(false, 0, true);
	Marker T = new Marker(false, 0, false);
	Marker mOne = new Marker(true, 1, false);
	Marker mTwo = new Marker(true, 2, false);
	public Board(){	
	}
	
	private void createBoard(){
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
		for(int i=8; i<9; i++){
			for(int j=0; j<6; j++){
				grid[j][i]= V;
			}
			for(int j=6; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=9; i<10; i++){
			for(int j=0; j<5; j++){
				grid[j][i]= V;
			}
			for(int j=5; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=10; i<11; i++){
			for(int j=0; j<4; j++){
				grid[j][i]= V;
			}
			for(int j=4; j<9; j++){
				grid[j][i]= N;
			}
		}
		for(int i=11; i<12; i++){
			for(int j=0; j<3; j++){
				grid[j][i]= V;
			}
			for(int j=3; j<9; j++){
				grid[j][i]= N;
			}
		}
	}
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
	private void removeMaker(int i, int j){
		
	}
}