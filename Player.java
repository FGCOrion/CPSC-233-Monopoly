import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import javafx.scene.control.Label;

class Player {
    /**
     * Instance variables: "piece" of type Piece, more to follow.
     * Setters and Getters for instance variables
     */
    //instance variables
    private int position = 1;
    private char avatar;
    private int money;
    private int playerNumber = 1;
    private int propertiesOwned = 0;
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
    public int getPlayerNumber(){
    	return this.playerNumber;
    }
    public int getPropertiesOwned(){
    	return this.propertiesOwned;
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
    public void setPlayerNumber(int newPlayerNumber){
    	this.playerNumber = newPlayerNumber;
	}
	public void setPropertiesOwned(int propertiesOwned){
    	this.propertiesOwned = propertiesOwned;
	}
    
   
    /* constructor */
    public Player() {
        position = 0;
        avatar = '@';
        money = 1500;
    }

    public Player(int playerNumber){
		position = 0;
		avatar = '@';
		money = 1500;
    	setPlayerNumber(playerNumber);
	}

    public Player(Player otherPlayer)
    {
        position = otherPlayer.getPosition();
        avatar = otherPlayer.getAvatar();
        money = otherPlayer.getMoney();
        playerNumber = otherPlayer.getPlayerNumber();
    }
	
	//Method to roll a single 7 sided die
	public static int rollDie() {
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
	
	/**
	*Method for the players turn
	*giving user an output to show the value of dice they rolled, then player walks the equal step. Out put play's current loation.
	*After a player pass the final position, player got 200$, this player's position will be recount.
	**/
	public void takeTurn(Board board, Player comp) {
		Scanner input = new Scanner(System.in);
		//Creates a new scanner

		wait(250);
		print("\nIt is player " + this.getPlayerNumber() + "'s turn");
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
		/**
		*when a player comes to an unowned place, player will have the opinion to determine purchase this very place or not. 
		*if player decide to own the place and have enough money, player will lose the amount of money and own this place.
		**/
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
					newSpace.setOwner(this.getPlayerNumber());
					board.setSpace(this.getPosition() - 1, newSpace);
				}
				else {
					print("You cannot afford this space");
				}
			}		
		}
		//If the player lands on a space they already own
		else if (newSpace.getOwner() == getPlayerNumber()) {
			wait(250);
			print("You own " + newSpace.getName());
		}
		
		//If the player lands on a space owned by the AI
		else if (newSpace.getOwner() != 0 && newSpace.getOwner() != getPlayerNumber() && newSpace.getOwner() != 3) {
			wait(250);
			print(newSpace.getName() + " is owned by player " + comp.getPlayerNumber() + ". You owe them $" + String.valueOf(newSpace.getValue()));
			this.setMoney(this.getMoney() - newSpace.getValue());
			comp.setMoney(comp.getMoney() + newSpace.getValue());
		}
			
	}







	public int takeTurnGui(Board board, Player comp, Label info0, Label info1, Label info2, Label info3, Label info4,
							Label infomoney, Label infoMoneyOpp, int choiceFlag){
		Scanner input = new Scanner(System.in);
		//Creates a new scanner

		wait(250);
		info0.setText("\nIt is player " + this.getPlayerNumber() + "'s turn");
		int x = rollDie();
		wait(250);
		info1.setText("You rolled a " + String.valueOf(x));
		int oldPosition = this.getPosition();
		this.setPosition(oldPosition + x);
		if (this.getPosition() > board.getLength()) {
			wait(250);
			info2.setText("You passed GO and collected $200");
			this.setPosition(this.getPosition() - board.getLength());
			this.setMoney(this.getMoney() + 200);
			wait(250);
			infomoney.setText(" " + this.getMoney());
		}

		Space newSpace = board.getSpace(this.getPosition() - 1);
		wait(250);
		info3.setText("You landed on " + newSpace.getName());

		//If the space the player lands on is unowned
		/**
		 *when a player comes to an unowned place, player will have the opinion to determine purchase this very place or not.
		 *if player decide to own the place and have enough money, player will lose the amount of money and own this place.
		 **/
		if (newSpace.getOwner() == 0) {
			wait(250);
			info4.setText(newSpace.getName() + " is unowned. Would you like to purchase it \nfor $" +
					String.valueOf(newSpace.getCost()) + "? (Value of $" + String.valueOf(newSpace.getValue()) + ")");
			choiceFlag = 1;
			//print("(y/n only please)");
			/*char choice = ' ';
			while (choice != 'y' && choice != 'n') {
				choice = input.next().charAt(0);
			}
			if (choice == 'y') {

				wait(250);

				//If the player has >= money to the spaces cost then they buy it
				if (this.getMoney() >= newSpace.getCost()) {
					print("Success");
					this.setMoney(this.getMoney() - newSpace.getCost());
					newSpace.setOwner(this.getPlayerNumber());
					board.setSpace(this.getPosition() - 1, newSpace);
				}
				else {
					print("You cannot afford this space");
				}
			}*/
		}
		//If the player lands on a space they already own
		else if (newSpace.getOwner() == getPlayerNumber()) {
			wait(250);
			info4.setText("You own " + newSpace.getName());
		}

		//If the player lands on a space owned by the AI
		else if (newSpace.getOwner() != 0 && newSpace.getOwner() != getPlayerNumber() && newSpace.getOwner() < 10) {
			wait(250);
			info4.setText(newSpace.getName() + " is owned by player " + comp.getPlayerNumber() + ". \nYou owe them $" + String.valueOf(newSpace.getValue()));
			this.setMoney(this.getMoney() - newSpace.getValue());
			comp.setMoney(comp.getMoney() + newSpace.getValue());
		}
		
		//If the player lands on chance
		else if (newSpace.getOwner() == 11) {
			wait(250);
			Random chanceRandom = new Random();
			int chanceValue = (chanceRandom.nextInt(550)) / 50;
			chanceValue = chanceValue * 50;
			info4.setText("You gained $" + Integer.toString(chanceValue));
			this.setMoney(this.getMoney() + chanceValue);
		}
		
		//If the player lands on Income Tax
		else if (newSpace.getOwner() == 12) {
			wait(250);
			this.setMoney(this.getMoney() - 100);
			info4.setText("You owe the bank $100");
		}
		
		//If the player lands on community fund
		else if (newSpace.getOwner() == 14) {
			wait(250);
			this.setMoney(this.getMoney() + 400);
			info4.setText("You received $400");
		}
		
		//If the player lands on Go to Jail
		else if (newSpace.getOwner() == 15) {
			wait(250);
			this.setPosition(7);
			info4.setText("You were sent back to jail");
		}
		
		infomoney.setText("Your Money: " + this.getMoney());
		infoMoneyOpp.setText("AI's Money: " + comp.getMoney());
		return choiceFlag;
	}

	public void purchase(Board board, Label info5){
		Space newSpace = board.getSpace(this.getPosition() - 1);
		if (this.getMoney() >= newSpace.getCost()) {
			info5.setText("Success");
			this.setMoney(this.getMoney() - newSpace.getCost());
			newSpace.setOwner(this.getPlayerNumber());
			board.setSpace(this.getPosition() - 1, newSpace);
			this.setPropertiesOwned(this.getPropertiesOwned() + 1);
		}
		else {
			info5.setText("You cannot afford this space");
		}
	}
}
