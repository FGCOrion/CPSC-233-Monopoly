import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

class GameMain {
	static Player player = new Player();
	static ComputerAI computer = new ComputerAI();
	
	static Board board = new Board();
	
	/**
	*Method to roll a single 7 sided die
	*@return result of the Dice
	**/
	
	private static int rollDie() {
		Random result = new Random();
		return result.nextInt(7) + 1;
	}
	
	/**
	*Method to print text so I don't have to write System.out.print every single time
	*Take text as a String parameter to output messages
	*@param text
	**/
	
	public static void print(String text) {
		System.out.println(text);
	}

	/**
	 * Method to make the program wait x amount of milliseconds, so I don't have to write the code every time
	 * @param milliseconds
	 */

	public static void wait(int milliseconds){
		try {Thread.sleep(milliseconds);} catch(InterruptedException intrx) {/* handle the exception */}
	}

	/**
	*Method to print a bunch of stuff at the start
	*output the WELCOME MESSAGE
	**/
	
	private static void gameStart() {
		print("Welcome to Mono-Poly!");
	}

	/**
	*Prints a bunch of info at the start of each round (like money and stuff)
	*take turn as a parameter, will output the information of the player in each turn
	*@param turn
	**/
	
	private static void roundStart(int turn) {
		Player.wait(250);
		print("\nTurn " + String.valueOf(turn));
		Player.wait(250);
		print("Player:   $" + String.valueOf(player.getMoney()));
		Player.wait(250);
		print("Computer: $" + String.valueOf(computer.getMoney()));
		Player.wait(250);
	}
	
	/**
	*Figures out if the game has ended or not
	*take turn,player,computer as a parameter
	*when people plays more than 500 turns, or any of the players' asset become negative, the game ends.
	*@param turn;
	*@param player;
	*@param computer;
	**/
	
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
		//determine whether the game ends or not
		while (endConditions(turn, player, computer) == false) {
			roundStart(turn);
			wait(250);
				
			player.takeTurn(board, computer);
			computer.takeTurn(board, player);

			turn += 1;
		}
		
		//Once the game is over, figures out who won
		if (player.getMoney() < 0)
			print("You Lose!");
		else if (computer.getMoney() < 0)
			print("You Win!");
		else if (turn > 500)
			print("Its a Draw! (so far)");
    }
}
