
public class Marker {
	boolean permanate;
	int playerNum;
	
	private Marker(boolean perma, int playnum){
		permanate = perma;
		playerNum = playnum;
	}
	
	private boolean getPermanate(){
		return permanate;
	}
	
	private int getPlayerNumber(){
		return playerNum;
	}
	
}
