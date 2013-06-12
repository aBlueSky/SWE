package networking;
/**
 * Marker.java
 * Marker for a board.
 * @author Kelsey LaPointe
 * @author Matthew Koval
 */
public class Marker {
	boolean permanent;
	int playerNum;
	boolean vacant;
	
	/** create a marker
	 * @param perma
	 * @param playNum
	 * @param issVacant
	 */
	public Marker(boolean perma, int playNum, boolean issVacant){
		permanent = perma;
		playerNum = playNum;
		vacant = issVacant;
	}
	
	/**get if the marker is permanent
	 * @return
	 */
	private boolean getPermanent(){
		return permanent;
	}
	
	/**get the player number for the marker
	 * @return
	 */
	private int getPlayerNumber(){
		return playerNum;
	}
	
	/**
	 * Flip the vacancy value
	 */
	private void setVacancy(){
		if(vacant == true){
			vacant = false;
		}
		else{
			vacant = true;
		}
	}
	
	/**Return vacancy
	 * @return
	 */
	private boolean isVacant(){
		return vacant;
	}
	
}
