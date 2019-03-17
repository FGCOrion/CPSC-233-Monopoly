
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


class Player{
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
    private boolean inJail = false;

    //private ArrayList<Space> owns= new ArrayList<Space>();
	private ArrayList<Integer> owns = new ArrayList<Integer>();

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
	public boolean getSpaceOwned(int space){
		boolean tf = false;
		if (this.owns.contains(space))
			tf = true;
		return tf;
	}

  public boolean getInJail(){
    return inJail;
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

  public void setInJail(boolean inJail){
    this.inJail = inJail;
  }

    /* constructor */
    public Player() {
        position = 0;
        avatar = '@';
        money = 1500;
        inJail = false;
    }

    public Player(int playerNumber){
  		position = 0;
  		avatar = '@';
  		money = 1500;
      inJail = false;
      setPlayerNumber(playerNumber);
	}

    public Player(Player otherPlayer)
    {
        position = otherPlayer.getPosition();
        avatar = otherPlayer.getAvatar();
        money = otherPlayer.getMoney();
        playerNumber = otherPlayer.getPlayerNumber();
        inJail = otherPlayer.getInJail();
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

		print("\nIt is player " + this.getPlayerNumber() + "'s turn");
		int x = rollDie();
		print("You rolled a " + String.valueOf(x));
		int oldPosition = this.getPosition();
		this.setPosition(oldPosition + x);
		if (this.getPosition() > board.getLength()) {
			print("You passed GO and collected $200");
			this.setPosition(this.getPosition() - board.getLength());
			this.setMoney(this.getMoney() + 200);
			print("You now have $" + this.getMoney());
		}

		Space newSpace = board.getSpace(this.getPosition() - 1);
		print("You landed on " + newSpace.getName());

		//If the space the player lands on is unowned
		/**
		*when a player comes to an unowned place, player will have the opinion to determine purchase this very place or not.
		*if player decide to own the place and have enough money, player will lose the amount of money and own this place.
		**/
		if (newSpace.getOwner() == 0) {
			print(newSpace.getName() + " is unowned. Would you like to purchase it for $" + String.valueOf(newSpace.getCost()) + "? (Value of $" + String.valueOf(newSpace.getValue()) + ")");
			print("(y/n only please)");
			char choice = ' ';
			while (choice != 'y' && choice != 'n') {
				choice = input.next().charAt(0);
			}
			if (choice == 'y') {


				//If the player has >= money to the spaces cost then they buy it
				if (this.getMoney() >= newSpace.getCost()) {
					print("\nSuccess");
					this.setMoney(this.getMoney() - newSpace.getCost());
					newSpace.setOwner(this.getPlayerNumber());
					board.setSpace(this.getPosition() - 1, newSpace);
				}
				else {
					print("\nYou cannot afford this space");
				}
			}
		}
		//If the player lands on a space they already own
		else if (newSpace.getOwner() == getPlayerNumber()) {
			print("You own " + newSpace.getName());
		}

		//If the player lands on a space owned by the AI
		else if (newSpace.getOwner() != 0 && newSpace.getOwner() != getPlayerNumber() && newSpace.getOwner() != 3) {
			print(newSpace.getName() + " is owned by player " + comp.getPlayerNumber() + ". You owe them $" + String.valueOf(newSpace.getValue()));
			this.setMoney(this.getMoney() - newSpace.getValue());
			comp.setMoney(comp.getMoney() + newSpace.getValue());
		}

	}










	public int takeTurnGui(Board board, Player comp, TextArea GameInfo, int choiceFlag){
		Scanner input = new Scanner(System.in);
		//Creates a new scanner

		GameInfo.setText("It is player " + this.getPlayerNumber() + "'s turn");
    if (this.getInJail() == false){
  		int x = rollDie();
  		GameInfo.appendText("\nYou rolled a " + String.valueOf(x));
  		int oldPosition = this.getPosition();
  		this.setPosition(oldPosition + x);
  		if (this.getPosition() >= board.getLength()) {
  			GameInfo.appendText("\nYou passed GO and collected $200");
  			this.setPosition(this.getPosition() - board.getLength());
  			this.setMoney(this.getMoney() + 200);
  		}


  		Space newSpace = board.getSpace(this.getPosition());
  		GameInfo.appendText("\nYou landed on " + newSpace.getName());

  		/**
  		 *when a player comes to an unowned place, player will have the opinion to determine purchase this very place or not.
  		 *if player decide to own the place and have enough money, player will lose the amount of money and own this place.
  		 **/
  		if (newSpace.getOwner() == 0) {
  			GameInfo.appendText("\n" + newSpace.getName() + " is unowned. \nWould you like to purchase it for $" +
  					String.valueOf(newSpace.getCost()) + "? \n(Rent of $" + String.valueOf(newSpace.getValue()) + ")");
  			choiceFlag = 1;


  		}
  		//If the player lands on a space they already own
  		else if (newSpace.getOwner() == getPlayerNumber()) {
  			GameInfo.appendText("\n You own " + newSpace.getName());
  		}

  		//If the player lands on a space owned by the AI
  		else if (newSpace.getOwner() != 0 && newSpace.getOwner() != getPlayerNumber() && newSpace.getOwner() < 10 && newSpace.getOwner() != -1) {
  			GameInfo.appendText("\n" + newSpace.getName() + " is owned by player " + comp.getPlayerNumber() + ". \nYou owe them $" + String.valueOf(newSpace.getValue()));
  			this.setMoney(this.getMoney() - newSpace.getValue());
  			comp.setMoney(comp.getMoney() + newSpace.getValue());

  		}

  		//If the player lands on chance
  		else if (newSpace.getOwner() == 11) {
  			Random chanceRandom = new Random();
  			int newAdd = (chanceRandom.nextInt(600)) / 50;
  			newAdd = newAdd * 50;
  			int chanceValue = newAdd - 300;
  			if (chanceValue >= 0)
  				GameInfo.appendText("\nYou gained $" + Integer.toString(chanceValue));
  			if (chanceValue < 0)
  				GameInfo.appendText("\nYou lost $" + Integer.toString(chanceValue));
  			this.setMoney(this.getMoney() + chanceValue);
  		}

  		//If the player lands on Income Tax
  		else if (newSpace.getOwner() == 12) {
  			this.setMoney(this.getMoney() - 100);
  			GameInfo.appendText("\nYou owe the bank $100");
  		}

  		//If the player lands on community fund
  		else if (newSpace.getOwner() == 14) {
  			this.setMoney(this.getMoney() + 200);
  			GameInfo.appendText("\nYou received $200");
  		}

  		//If the player lands on Go to Jail
  		else if (newSpace.getOwner() == 15) {
  			this.setPosition(6);
  			GameInfo.appendText("\nYou were sent back to jail");
        this.setInJail(true);
  		}

    } else if (this.getInJail()){
      int x = rollDie();
      if (x == 6){
  		GameInfo.appendText("\nYou rolled a " + String.valueOf(x));
      GameInfo.appendText("\n You have been released from jail!");
      this.setInJail(false);
    } else {
      GameInfo.appendText("\nYou rolled a " + String.valueOf(x));
      GameInfo.appendText("\nYou must roll a 6 to be\nreleased from jail.");
      GameInfo.appendText("\nTry again next turn");
    }
    }
		return choiceFlag;
	}

	public void purchase(Board board, TextArea GameInfo){
		Space newSpace = board.getSpace(this.getPosition());
		if (this.getMoney() >= newSpace.getCost()) {
			GameInfo.appendText("\nSuccess");
			this.setMoney(this.getMoney() - newSpace.getCost());
			newSpace.setOwner(this.getPlayerNumber());
			board.setSpace(this.getPosition(), newSpace);
			this.setPropertiesOwned(this.getPropertiesOwned() + 1);
			this.owns.add(this.getPosition());
			for (int i = 0; i < owns.size(); i++) {
				System.out.println(Integer.toString(owns.get(i)));
			}
		}
		else {
			GameInfo.appendText("\nYou cannot afford this space");
		}
	}

  public void sell(Board board, int location, TextArea GameInfo){
    Space newSpace = board.getSpace(location);
    this.setMoney(this.getMoney() + newSpace.getCost());
    newSpace.setOwner(0);
    board.setSpace(location, newSpace);
    this.owns.remove(new Integer(location));
    GameInfo.appendText("\nYou have sold your property.");
  }
}
