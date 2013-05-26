
public class GameManager {
	int[] dice = new int[4];
	
	private int[] roll(){
		for(int i=0; i<dice.length; i++){
			dice[i]= 6*(int)Math.random();
		}
		
		return dice;
		
	}
	
	private boolean checkRoll(int a, int b){
		boolean valid = false;
		if((2<=a)&&(12>=a)){
			for(int i=0; i<dice.length; i++){
				if(dice[i]==a){
					valid = true;
				}
			}
		}
		
		if((2<=b)&&(12>=b)){
			for(int i=0; i<dice.length; i++){
				if(dice[i]==b){
					valid = true;
				}
			}
		}
		return valid;
	}

}
