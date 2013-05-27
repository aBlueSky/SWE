package networking;
public class Marker {
	boolean permanate;
	int playerNum;
	boolean vacant;
	
	public Marker(boolean perma, int playnum, boolean issVacant){
		permanate = perma;
		playerNum = playnum;
		vacant = issVacant;
	}
	
	private boolean getPermanate(){
		return permanate;
	}
	
	private int getPlayerNumber(){
		return playerNum;
	}
	
	private void setVacancy(){
		if(vacant == true){
			vacant = false;
		}
		else{
			vacant = true;
		}
	}
	
	private boolean isVacant(){
		return vacant;
	}
	
}
