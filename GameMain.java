import java.util.Random;

class GameMain {
	//Method to roll a single 6 sided die
	private int rollDie() {
		Random result = new Random();
		return result.nextInt(6) + 1;
	}
	
    public static void main(String[] args) {
        Board board = new Board();
        //Constructs a new board	
		
		//totalTurns subtracts by 1 each time it goes through the loop until it hits 0
		int totalTurns = 500;
		
		while (totalTurns > 0) {
			
		}
    }
}
