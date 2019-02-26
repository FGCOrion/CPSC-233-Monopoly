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
	
	//Method for the players turn
	private static void playerTurn() {
		Scanner input = new Scanner(System.in);
		//Creates a new scanner
		
		print("\nIt is your turn");
		int x = rollDie();
		print("You rolled a " + String.valueOf(x));
		int oldPosition = player.getPosition();
		player.setPosition(oldPosition + x);
		if (player.getPosition() > board.getLength()) {
			print("You passed GO and collected $200");
			player.setPosition(player.getPosition() - board.getLength());
			player.setMoney(player.getMoney() + 200);
		}
		
		Space newSpace = board.getSpace(player.getPosition() - 1);
		print("You landed on " + newSpace.getName());
		
		//If the space the player lands on is unowned
		if (newSpace.getOwner() == 0) {
			print(newSpace.getName() + " is unowned. Would you like to purchase it for $" + String.valueOf(newSpace.getCost()) + "? (Value of $" + String.valueOf(newSpace.getValue()) + ")");
			print("(y/n only please)");
			char choice = ' ';
			while (choice != 'y' && choice != 'n') {
				choice = input.next().charAt(0);
			}
			if (choice == 'y') {
				
				//If the player has >= money to the spaces cost then they buy it
				if (player.getMoney() >= newSpace.getCost()) {
					print("Success");
					player.setMoney(player.getMoney() - newSpace.getCost());
					newSpace.setOwner(1);
				}
				else {
					print("You cannot afford this space");
				}
			}		
		}
			
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
				
			playerTurn();

			turn += 1;
		}
    }
}
