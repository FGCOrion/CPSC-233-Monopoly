import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

class GameMain {
	static Player player = new Player();
	static Player computer = new Player();
	
	static Board board = new Board();
	
	//Method to roll a single 6 sided die
	private static int rollDie() {
		Random result = new Random();
		return result.nextInt(6) + 1;
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
	
	//Method for the players turn
	private static void playerTurn() {
		print("It is your turn");
		int x = rollDie();
		print("You rolled a " + String.valueOf(x));
		int oldPosition = player.getPosition();
		player.setPosition(oldPosition + x);
		if (player.getPosition() > board.getLength()) {
			print("You passed GO and collected $200");
			player.setPosition(player.getPosition() - board.getLength());
			player.setMoney(player.getMoney() + 200);
		}
		
		print("You landed on " + board.getSpace(player.getPosition()).getName());
		
		//If the space the player lands on is unowned
		if (board.getSpace(player.getPosition() - 1).getOwner() == 0) {
			
		}
	}
	
	
    public static void main(String[] args) {
        Board board = new Board();
        //Constructs a new board	
		
		//turn increases by 1 every round until it reaches 500
		int turn = 0;
		
		while (turn <= 500) {
			roundStart(turn);
			gameStart();
			
			playerTurn();
			
		turn += 1;
		}
    }
}
