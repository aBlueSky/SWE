package networking;
public class Marker {
	boolean permanent;
	int playerNum;
	boolean vacant;
	
	public Marker(boolean perma, int playNum, boolean issVacant){
		permanent = perma;
		playerNum = playNum;
		vacant = issVacant;
	}
	
	private boolean getPermanent(){
		return permanent;
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
