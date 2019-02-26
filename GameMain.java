import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

class GameMain {
	static Player player = new Player();
	static Player computer = new Player();
	
	static Board board = new Board();
	
	//Method to roll a single 7 sided die
	private static int rollDie() {
		Random result = new Random();
		return result.nextInt(7) + 1;
	}
	
	//Method to print text so I don't have to write System.out.print every single time
	public static void print(String text) {
		System.out.println(text);
	}
	
	//Method to print a bunch of stuff at the start
	private static void gameStart() {
		print("Welcome to Mono-Poly!");
	}

	//Prints a bunch of info at the start of each round (like money and stuff)
	private static void roundStart(int turn) {
		print("Turn " + String.valueOf(turn));
		print("Player:   $" + String.valueOf(player.getMoney()));
		print("Computer: $" + String.valueOf(computer.getMoney()));
	}
	
	//Figures out if the game has ended or not
	private static boolean endConditions(int turn, Player player, Player computer) {
		if (turn > 500)
			return true;
		if (player.getMoney() < 0)
			return true;
		if (computer.getMoney() < 0)
			return true;
		else
			return false;
	}
	
    public static void main(String[] args) {
		
        Board board = new Board();
        //Constructs a new board	
		
		//turn increases by 1 every round until it reaches 500
		int turn = 0;
		
		//Reads the starting text
		gameStart();
		
		while (endConditions(turn, player, computer) == false) {
			roundStart(turn);
				
			player.takeTurn(board);

			turn += 1;
		}
    }
}
