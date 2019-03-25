import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

class Player extends Gui{

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
    private boolean isPlayer;
	  private ArrayList<Integer> owns = new ArrayList<Integer>();
    private ArrayList<Player> allPlayers = new ArrayList<Player>();


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

    public boolean getIsPlayer(){
      return isPlayer;
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

    public void setIsPlayer(boolean newIsPlayer){
      this.isPlayer = newIsPlayer;
    }

    public boolean getInJail(){
      return inJail;
    }


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

    public Player(int playerNumber, boolean isPlayer){
  		position = 0;
  		avatar = '@';
  		money = 1500;
      inJail = false;
      setPlayerNumber(playerNumber);
      setIsPlayer(isPlayer);
	  }

    public Player(Player otherPlayer){
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

  	public int takeTurnGui(Board board, Player comp, TextArea GameInfo, int choiceFlag, ArrayList<Player> allPlayers, int forceSale){
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
      			GameInfo.appendText("\nYou own " + newSpace.getName());
      		}

      		//If the player lands on a space owned by another player
      		else if (newSpace.getOwner() != 0 && newSpace.getOwner() != getPlayerNumber() && newSpace.getOwner() < 10 && newSpace.getOwner() != -1) {
          	GameInfo.appendText("\n" + newSpace.getName() + " is owned by player " + newSpace.getOwner() + ". \nYou owe them $" + String.valueOf(newSpace.getValue()));
            this.setMoney(this.getMoney() - newSpace.getValue());
            allPlayers.get(newSpace.getOwner() - 1).setMoney(allPlayers.get(newSpace.getOwner() - 1).getMoney() + newSpace.getValue());
            GameInfo.appendText("\nPlayer " + newSpace.getOwner() + "'s new balance: $" + allPlayers.get(newSpace.getOwner() - 1).getMoney());
            GameInfo.appendText("\nYour new balance is: $" + this.getMoney());

            if (this.getMoney() < 0 && this.getPropertiesOwned() >= 1){
              GameInfo.appendText("\nYou are out of money.\nSell a property or you will forfeit the game.");
              setForceSale(1);
            }
            else if (this.getMoney() < 0 && this.getPropertiesOwned() <= 0){
              GameInfo.appendText("\nYou are out of money and have no properties to sell.\nYou lose.");
              allPlayers.remove(this.getPlayerNumber() - 1);
              if (allPlayers.size() == 1){
                GameInfo.appendText("Congratulations Player " + allPlayers.get(0).getPlayerFlag() + "\nYou are the winner!");
                setNextTurnUnlocked(0);
                setChoiceUnlocked(0);
                setRollUnlocked(0);
              }
            }
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
            if (this.getMoney() < 0 && this.getPropertiesOwned() >= 1){
              GameInfo.appendText("\nYou are out of money.\nSell a property or you will forfeit the game.");
              setForceSale(1);
            }
            else if (this.getMoney() < 0 && this.getPropertiesOwned() <= 0){
              GameInfo.appendText("\nYou are out of money and have no properties to sell.\nYou lose.");
              allPlayers.remove(this.getPlayerNumber() - 1);
              if (allPlayers.size() == 1){
                GameInfo.setText("Congratulations Player " + allPlayers.get(0).getPlayerFlag() + "\nYou are the winner!");
                setNextTurnUnlocked(0);
                setChoiceUnlocked(0);
                setRollUnlocked(0);
              }
            }
      		}

      		//If the player lands on Income Tax
      		else if (newSpace.getOwner() == 12) {
      			this.setMoney(this.getMoney() - 100);
      			GameInfo.appendText("\nYou owe the bank $100");
            if (this.getMoney() < 0 && this.getPropertiesOwned() >= 1){
              GameInfo.appendText("\nYou are out of money.\nSell a property or you will forfeit the game.");
              setForceSale(1);
            }
            else if (this.getMoney() < 0 && this.getPropertiesOwned() <= 0){
              GameInfo.appendText("\nYou are out of money and have no properties to sell.\nYou lose.");
              allPlayers.remove(this.getPlayerNumber() - 1);
              if (allPlayers.size() == 1){
                GameInfo.setText("Congratulations Player " + allPlayers.get(0).getPlayerFlag() + "\nYou are the winner!");
                setNextTurnUnlocked(0);
                setChoiceUnlocked(0);
                setRollUnlocked(0);
              }
            }
      		}

      		//If the player lands on community fund
      		else if (newSpace.getOwner() == 14) {
      			this.setMoney(this.getMoney() + 200);
      			GameInfo.appendText("\nYou received $200");
      		}

      		//If the player lands on Go to Jail
      		else if (newSpace.getOwner() == 15) {
      			this.setPosition(6);
      			GameInfo.appendText("\nYou were sent back jail");
            this.setInJail(true);
      		}

          //If the player lands on Free Parking
          else if (newSpace.getOwner() == 16) {
            Random parkingRandom = new Random();
		        int parkingValue = (parkingRandom.nextInt(350)) / 50;
		        parkingValue = parkingValue * 50;
		        this.setMoney(this.getMoney() + parkingValue);
            GameInfo.appendText("\nYou gained $" + Integer.toString(parkingValue));
        }

        } else if (this.getInJail()){
          int x = rollDie();
          if (x == 6){
      		GameInfo.appendText("\nYou rolled a " + String.valueOf(x));
          GameInfo.appendText("\nYou have been released from jail!");
          this.setInJail(false);
        } else {
          GameInfo.appendText("\nYou rolled a " + String.valueOf(x));
          GameInfo.appendText("\nYou must roll a 6 to be\nreleased from jail.");
          GameInfo.appendText("\nTry again next turn");
        }
        }
    		return choiceFlag;
    }

    public void takeTurnAI(Board board, Player player, TextArea GameInfo, ArrayList<Player> allPlayers){

      GameInfo.setText("It is player " + this.getPlayerNumber() + "'s turn");
      if (this.getInJail() == false){
        int x = rollDie();
        GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " rolled a " + String.valueOf(x));
        int oldPosition = this.getPosition();
        this.setPosition(oldPosition + x);
        if (this.getPosition() >= board.getLength()) {
          GameInfo.appendText("\nYou passed GO and collected $200");
          this.setPosition(this.getPosition() - board.getLength());
          this.setMoney(this.getMoney() + 200);
        }

        Space newSpace = board.getSpace(this.getPosition());
        GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " landed on " + newSpace.getName());

        //If computer player lands on an unowned space
        if (newSpace.getOwner() == 0){
          GameInfo.appendText("\n" + newSpace.getName() + " is unowned and costs $" + String.valueOf(newSpace.getCost()) + ".");
          GameInfo.appendText("\nRent of $" + String.valueOf(newSpace.getValue()));

          //Computer buys the space if they have enough money
          if (this.getMoney() >= newSpace.getCost()){
            GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " bought " + newSpace.getName());
            newSpace.setOwner(this.getPlayerNumber());
            board.setSpace(this.getPosition(), newSpace);
            this.setPropertiesOwned(this.getPropertiesOwned() + 1);
            this.owns.add(this.getPosition());
            this.setMoney(this.getMoney() - newSpace.getCost());

            //Computer does not buy the property if they do not have enough money
          } else {
            GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " cannot afford this space.");
          }
        }

        //If the computer player lands on a space they own
        else if (newSpace.getOwner() == getPlayerNumber()) {
          GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " owns " + newSpace.getName());
        }

        //If the computer player lands on a space owned by another player
        else if (newSpace.getOwner() != 0 && newSpace.getOwner() != getPlayerNumber() && newSpace.getOwner() < 10 && newSpace.getOwner() != -1) {
          GameInfo.appendText("\n" + newSpace.getName() + " is owned by player " + newSpace.getOwner() + ". \nPlayer " + this.getPlayerNumber() +  " owes them $" + String.valueOf(newSpace.getValue()));
          this.setMoney(this.getMoney() - newSpace.getValue());
          allPlayers.get(newSpace.getOwner() - 1).setMoney(allPlayers.get(newSpace.getOwner() - 1).getMoney() + newSpace.getValue());
          GameInfo.appendText("\nPlayer " + newSpace.getOwner() + "'s new balance: $" + allPlayers.get(newSpace.getOwner() - 1).getMoney());
          GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + "'s new balance is: $" + this.getMoney());

          if (this.getMoney() < 0 && this.getPropertiesOwned() >= 1){
            GameInfo.appendText("\nPlayer " + this.getPlayerFlag() + " is out of money.\nThey must sell a property or forfeit the game.");

            ArrayList<Integer> locations = new ArrayList<Integer>();
            for (int i = 0; i < 24; i++){
            if (allPlayers.get(getPlayerFlag() - 1).getSpaceOwned(i) == true){
              locations.add(board.getSpace(i).getLocation());
            }
          }
          final int location = locations.get(0);
          allPlayers.get(getPlayerFlag() - 1).sell(board, location, GameInfo);
          }

          else if (this.getMoney() < 0 && this.getPropertiesOwned() <= 0){
            GameInfo.appendText("\nPlayer " +this.getPlayerFlag() + " is out of money and have no properties to sell.\nThey lose.");
            allPlayers.remove(this.getPlayerNumber() - 1);

            if (allPlayers.size() == 1){
              GameInfo.appendText("Congratulations Player " + allPlayers.get(0).getPlayerFlag() + "\nYou are the winner!");
              setNextTurnUnlocked(0);
              setChoiceUnlocked(0);
              setRollUnlocked(0);
            }
          }
        }

        //If the computer lands on chance
        else if (newSpace.getOwner() == 11) {
          Random chanceRandom = new Random();
          int newAdd = (chanceRandom.nextInt(600)) / 50;
          newAdd = newAdd * 50;
          int chanceValue = newAdd - 300;
          if (chanceValue >= 0)
            GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " gained $" + Integer.toString(chanceValue));
          if (chanceValue < 0)
            GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " lost $" + Integer.toString(chanceValue));
          this.setMoney(this.getMoney() + chanceValue);
        }

        //If the  computer player lands on Income Tax
        else if (newSpace.getOwner() == 12) {
          this.setMoney(this.getMoney() - 100);
          GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " owes the bank $100");
        }

        //If the player lands on community fund
        else if (newSpace.getOwner() == 14) {
          this.setMoney(this.getMoney() + 200);
          GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " received $200");
        }

        //If the player lands on Go to Jail
        else if (newSpace.getOwner() == 15) {
          this.setPosition(6);
          GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " was sent to jail");
          this.setInJail(true);
        }

        else if (newSpace.getOwner() == 16) {
          Random parkingRandom = new Random();
          int parkingValue = (parkingRandom.nextInt(350)) / 50;
          parkingValue = parkingValue * 50;
          this.setMoney(this.getMoney() + parkingValue);
          GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " gained $" + Integer.toString(parkingValue));
        }

      } else if (this.getInJail()){
          int x = rollDie();
          if (x == 6){
        	GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " rolled a " + String.valueOf(x));
          GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " has been released from jail!");
          this.setInJail(false);
        } else {
          GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " rolled a " + String.valueOf(x));
          GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " must roll a 6 to be\nreleased from jail.");
          GameInfo.appendText("\nTry again next turn");
        }
      }
    }

    /**
    * purchases property player is on when positive button is clicked in Gui
    */
  	public void purchase(Board board, TextArea GameInfo){
  		Space newSpace = board.getSpace(this.getPosition());
  		if (this.getMoney() >= newSpace.getCost()) {
  			GameInfo.appendText("\nSuccess");
  			this.setMoney(this.getMoney() - newSpace.getCost());
  			newSpace.setOwner(this.getPlayerNumber());
  			board.setSpace(this.getPosition(), newSpace);
  			this.setPropertiesOwned(this.getPropertiesOwned() + 1);
  			this.owns.add(this.getPosition());
  		}
  		else {
  			GameInfo.appendText("\nYou cannot afford this space");
  		}
  	}


    /**
    * sells selected property
    */
    public void sell(Board board, int location, TextArea GameInfo){
      Space newSpace = board.getSpace(location);
      this.setMoney(this.getMoney() + newSpace.getSaleValue());
      newSpace.setOwner(0);
      board.setSpace(location, newSpace);
      this.owns.remove(new Integer(location));
      if (this.getIsPlayer() == true){
      GameInfo.appendText("\nYou have sold your property.");
    } else {
      GameInfo.appendText("\nPlayer" + this.getPlayerFlag() + "has sold " + board.getSpace(location));


    }
    }

    /**
    * updates player info in TextArea in Gui
    */
    public void updatePlayerInfo(TextArea playerInfo, Player currentPlayer, Board board, int turnCount){
      playerInfo.setText("Current Position: " + board.getSpace(currentPlayer.getPosition()).getName());
      playerInfo.appendText("\nMoney: $" + currentPlayer.getMoney());
      playerInfo.appendText("\nProperties owned: " + currentPlayer.getPropertiesOwned());
      playerInfo.appendText("\nCurrent Turn: " + turnCount);
    }

  }
