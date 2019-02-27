import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

class Player {
    /**
     * Instance variables: "piece" of type Piece, more to follow.
     * Setters and Getters for instance variables
     */
    //instance variables
    private int position = 0;
    private char avatar;
    private int money;
    //private ArrayList<Space> owns= new ArrayList<Space>();

    /* Getters */
    public int getPosition(){
        return position;
    }
    public char getAvatar(){
        return avatar;
    }
    public int getMoney(){
        return money;
    }
    /*manage the place*/
    /*public boolean buyPlace(String p){
        if (owns.contains(p))
            return false;
        owns.add(p);
        return true;
    }
    public boolean sellPlace(String p){
        if (!owns.contains(p))
            return false;
        owns.remove(p);
        return true;
    }
        
    public boolean placeExist(String p){
        if (owns.contains(p))
            return true;
        else
            return false;
    }*/
	//This is broken for now
    
    /* Setters */
    public void setPosition(int newPosition){
        this.position = newPosition;
    }
    public void setAvatar(char avatarType) {
        this.avatar = avatarType;
    }
    public void setMoney(int newMoney){
        this.money = newMoney;
    }
    
   
    /* constructor */
    public Player() {
        position = 0;
        avatar = 'a';
        money = 1500;
    }

    public Player(Player otherPlayer)
    {
        position = otherPlayer.getPosition();
        avatar = otherPlayer.getAvatar();
        money = otherPlayer.getMoney();
    }
	
	//Method to roll a single 7 sided die
	private static int rollDie() {
		Random result = new Random();
		return result.nextInt(7) + 1;
	}
	
	//Method to print text so I don't have to write System.out.print every single time
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
	
	//Method for the players turn
	public void takeTurn(Board board) {
		Scanner input = new Scanner(System.in);
		//Creates a new scanner

		wait(250);
		print("\nIt is your turn");
		int x = rollDie();
		wait(250);
		print("You rolled a " + String.valueOf(x));
		int oldPosition = this.getPosition();
		this.setPosition(oldPosition + x);
		if (this.getPosition() > board.getLength()) {
			wait(250);
			print("You passed GO and collected $200");
			this.setPosition(this.getPosition() - board.getLength());
			this.setMoney(this.getMoney() + 200);
			wait(250);
			print("You now have $" + this.getMoney());
		}
		
		Space newSpace = board.getSpace(this.getPosition() - 1);
		wait(250);
		print("You landed on " + newSpace.getName());
		
		//If the space the player lands on is unowned
		if (newSpace.getOwner() == 0) {
			wait(250);
			print(newSpace.getName() + " is unowned. Would you like to purchase it for $" + String.valueOf(newSpace.getCost()) + "? (Value of $" + String.valueOf(newSpace.getValue()) + ")");
			print("(y/n only please)");
			char choice = ' ';
			while (choice != 'y' && choice != 'n') {
				choice = input.next().charAt(0);
			}
			if (choice == 'y') {

				wait(250);

				//If the player has >= money to the spaces cost then they buy it
				if (this.getMoney() >= newSpace.getCost()) {
					print("Success");
					this.setMoney(this.getMoney() - newSpace.getCost());
					newSpace.setOwner(1);
				}
				else {
					print("You cannot afford this space");
				}
			}		
		}
			
	}

}
