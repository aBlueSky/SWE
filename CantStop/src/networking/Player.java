package networking;

public class Player {
	String name;
	int score;
	
	public Player(String plaNam){
		name = plaNam;
		score = 0;
	}
	
	private int getScore(){
		return score;
	}
	
	private void setScore(int newScore){
		score = newScore;
	}
}
