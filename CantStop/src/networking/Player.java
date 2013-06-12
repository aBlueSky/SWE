package networking;

/**
 * Player.java
 * @author Kelsey LaPointe
 * @author Matthew Koval
 */
public class Player {
	String name;
	int score;
	
	/**create a player
	 * @param plaNam
	 */
	public Player(String plaNam){
		name = plaNam;
		score = 0;
	}
	
	/**Return the players score
	 * @return
	 */
	private int getScore(){
		return score;
	}
	
	/** set the players score
	 * @param newScore
	 */
	private void setScore(int newScore){
		score = newScore;
	}
}
