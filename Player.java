import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import java.util.Collections;

class Player extends Gui{

    //instance variables
    private int position = 1;
    private char avatar;
    private int money;
    private int playerNumber = 1;
    private int propertiesOwned = 0;
    private boolean inJail = false;
    private boolean isPlayer;
    private boolean eliminated = false;
	  private ArrayList<Integer> owns = new ArrayList<Integer>();
    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    private Player winner;

    //Accessor Methods

    /**
    * returns position of player
    * @return position
    */
    public int getPosition(){
        return position;
    }

    /**
    * returns amount of money a player has
    * @return money
    */
    public int getMoney(){
        return money;
    }

    /**
    * returns true if the player is human
    * retuns false if the player is AI
    * @return isPlayer
    */
    public boolean getIsPlayer(){
      return isPlayer;
    }

    /**
    * returns true if the player has been eliminated from the game
    * returns false if the player is still playing and has not been eliminated
    * @return eliminated
    */
    public boolean getEliminated(){
      return this.eliminated;
    }

    /**
    * returns which player it is (player 1 or 2 or 3 or 4)
    * @return playerNumber
    */
    public int getPlayerNumber(){
    	return this.playerNumber;
    }

    /**
    * returns the amount of properties a player owns
    * @return propertiesOwned
    */
    public int getPropertiesOwned(){
    	return this.propertiesOwned;
	   }

     /**
     * returns true if a property is owned by the specified player
     * returns false if it is not owned by the specified player
     * @return tf
     */
	  public boolean getSpaceOwned(int space){
  		boolean tf = false;
  		if (this.owns.contains(space))
  			tf = true;
  		return tf;
	   }

     /**
     * returns true if the player is in jail
     * retuns false if the player is not in jail
     * @return inJail
     */
    public boolean getInJail(){
      return inJail;
    }


    /* Setters */

    /**
    * sets the player status to eliminated when true
    * sets the player status to not eliminated when false
    * @param isEliminated
    */
    public void setEliminated(boolean isEliminated){
       eliminated = isEliminated;
     }

     /**
     * sets the player status to human when true
     * sets the player status to AI when false
     * @param newIsPlayer
     */
    public void setIsPlayer(boolean newIsPlayer){
      this.isPlayer = newIsPlayer;
    }

    /**
    * sets the position of the player on the board
    * @param newPosition
    */
    public void setPosition(int newPosition){
        this.position = newPosition;
    }

    /**
    * sets the amount of money a player has
    * @param newMoney
    */
    public void setMoney(int newMoney){
        this.money = newMoney;
    }

    /**
    * sets the players number (1-4)
    * @param newPlayerNumber
    */
    public void setPlayerNumber(int newPlayerNumber){
    	this.playerNumber = newPlayerNumber;
  	}

    /**
    * sets the amount of properties a player owns
    * @param propertiesOwned
    */
  	public void setPropertiesOwned(int propertiesOwned){
      	this.propertiesOwned = propertiesOwned;
  	}

    /**
    * sets if the player is in jail or not
    * if true, the player is in Jail
    * if false, the player is not in jail
    * @param inJail
    */
    public void setInJail(boolean inJail){
      this.inJail = inJail;
    }

    //Constructors for player

    //default constructor
    public Player() {
      position = 0;
      avatar = '@';
      money = 1500;
      inJail = false;
      eliminated = false;
    }

    /**
    * constructor that takes a player number and boolen isPlayer as an argument
    * this constructor called from the main menu when setting up human and computer players
    * @param playerNumber, isPLayer
    */
    public Player(int playerNumber, boolean isPlayer){
  		position = 0;
  		avatar = '@';
  		money = 1500;
      inJail = false;
      eliminated = false;
      setPlayerNumber(playerNumber);
      setIsPlayer(isPlayer);
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
  			print(newSpace.getName() + " is owned by player " + this.getPlayerNumber() + ". You owe them $" + String.valueOf(newSpace.getValue()));
  			this.setMoney(this.getMoney() - newSpace.getValue());
  			this.setMoney(this.getMoney() + newSpace.getValue());
  		}
  	}


    /**
    * this is the method that allows the human players to take their turn, updated information on all players as play progresses
    * @param board, player, GameInfo, positive, negative, nextTurn, allPlayers
    */
  	public void takeTurnGui(Board board, Player player, TextArea GameInfo, Button positive, Button negative, Button nextTurn, ArrayList<Player> allPlayers){

    		GameInfo.setText("It is player " + player.getPlayerNumber() + "'s turn");

        //Start of turn if the player is not stuck in jail
        if (player.getInJail() == false){
      		int x = rollDie();
      		GameInfo.appendText("\nYou rolled a " + String.valueOf(x));
      		int oldPosition = player.getPosition();
      		player.setPosition(oldPosition + x);

          //If the player passes or lands on GO they collect $200
      		if (player.getPosition() >= board.getLength()) {
      			GameInfo.appendText("\nYou passed GO and collected $200");
      			player.setPosition(player.getPosition() - board.getLength());
      			player.setMoney(player.getMoney() + 200);
            nextTurn.setDisable(false);
      		}

          //updates gameinfo to let the player know where they have landed
      		Space newSpace = board.getSpace(player.getPosition());
      		GameInfo.appendText("\nYou landed on " + newSpace.getName());

      		/**
      		 * when a player comes to an unowned place they will be given the option to purchase the space or not
      		 * if they deicde to purchase the property, their money will be adjusted, less the cost of the property
      		 **/
      		if (newSpace.getOwner() == 0) {
      			GameInfo.appendText("\n" + newSpace.getName() + " is unowned. \nWould you like to purchase it for $" +
      					String.valueOf(newSpace.getCost()) + "? \n(Rent of $" + String.valueOf(newSpace.getValue()) + ")");
                positive.setDisable(false);
                negative.setDisable(false);
                nextTurn.setDisable(true);
      		}

          //If the player lands on Jail
          else if (newSpace.getOwner() == -1){
            nextTurn.setDisable(false);
          }

      		//If the player lands on a space they already own
      		else if (newSpace.getOwner() == getPlayerNumber()) {
      			GameInfo.appendText("\nYou own " + newSpace.getName());
            nextTurn.setDisable(false);
      		}

      		//If the player lands on a space owned by another player they will pay rent to that player, the system then updates
          // gameinfo to display the players new total money and the property owners new total money
      		else if (newSpace.getOwner() != 0 && newSpace.getOwner() != getPlayerNumber() && newSpace.getOwner() < 10 && newSpace.getOwner() != -1) {
          	GameInfo.appendText("\n" + newSpace.getName() + " is owned by player " + newSpace.getOwner() + ". \nYou owe them $" + String.valueOf(newSpace.getValue()));
            player.setMoney(player.getMoney() - newSpace.getValue());
            allPlayers.get(newSpace.getOwner() - 1).setMoney(allPlayers.get(newSpace.getOwner() - 1).getMoney() + newSpace.getValue());
            GameInfo.appendText("\nPlayer " + newSpace.getOwner() + "'s new balance: $" + allPlayers.get(newSpace.getOwner() - 1).getMoney());
            GameInfo.appendText("\nYour new balance is: $" + player.getMoney());
            nextTurn.setDisable(true);
            player.checkPlayerFunds(GameInfo, nextTurn, allPlayers, board);
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
      			player.setMoney(player.getMoney() + chanceValue);
            player.checkPlayerFunds(GameInfo, nextTurn, allPlayers, board);

      		}

      		//If the player lands on Income Tax
      		else if (newSpace.getOwner() == 12) {
      			player.setMoney(player.getMoney() - 100);
      			GameInfo.appendText("\nYou owe the bank $100");
            player.checkPlayerFunds(GameInfo, nextTurn, allPlayers, board);

      		}

      		//If the player lands on community fund
      		else if (newSpace.getOwner() == 14) {
      			player.setMoney(player.getMoney() + 200);
      			GameInfo.appendText("\nYou received $200");
            nextTurn.setDisable(false);
      		}

      		//If the player lands on Go to Jail
      		else if (newSpace.getOwner() == 15) {
      			player.setPosition(6);
      			GameInfo.appendText("\nYou were sent back jail");
            player.setInJail(true);
            nextTurn.setDisable(false);
      		}

          //If the player lands on Free Parking
          else if (newSpace.getOwner() == 16) {
            Random parkingRandom = new Random();
		        int parkingValue = (parkingRandom.nextInt(350)) / 50;
		        parkingValue = parkingValue * 50;
		        player.setMoney(player.getMoney() + parkingValue);
            GameInfo.appendText("\nYou gained $" + Integer.toString(parkingValue));
            player.checkPlayerFunds(GameInfo, nextTurn, allPlayers, board);
        }

        //Start of player turn if they are stuck in jail
        //the player must roll a 3 or a 6 to be released from jail
        } else if (player.getInJail()){
          int x = rollDie();
          if (x == 3 || x == 6){
      		GameInfo.appendText("\nYou rolled a " + String.valueOf(x));
          GameInfo.appendText("\nYou have been released from jail!");
          player.setInJail(false);
          nextTurn.setDisable(false);
        } else {
          GameInfo.appendText("\nYou rolled a " + String.valueOf(x));
          GameInfo.appendText("\nYou must roll a 3 pr a 6 to be\nreleased from jail.");
          GameInfo.appendText("\nTry again next turn");
          nextTurn.setDisable(false);
        }
        }
    }

    /**
    * this is the method that allows the human players to take their turn, updated information on all players as play progresses
    * @param board, player, GameInfo, allPlayers, nextTurn
    */
    public void takeTurnAI(Board board, Player player, TextArea GameInfo, ArrayList<Player> allPlayers, Button nextTurn){

      GameInfo.setText("It is player " + player.getPlayerNumber() + "'s turn");

      //Start of the AIs turn if they are not stuck in jail
      if (player.getInJail() == false){
        int x = rollDie();
        GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " rolled a " + String.valueOf(x));
        int oldPosition = player.getPosition();
        player.setPosition(oldPosition + x);

        //if the AI passes or lands on GO they collect $200 dollars
        if (player.getPosition() >= board.getLength()) {
          GameInfo.appendText("\nYou passed GO and collected $200");
          player.setPosition(player.getPosition() - board.getLength());
          player.setMoney(player.getMoney() + 200);
        }

        //updates the AI info to display where they have landed
        Space newSpace = board.getSpace(player.getPosition());
        GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " landed on " + newSpace.getName());

        //If computer player lands on an unowned space
        if (newSpace.getOwner() == 0){
          GameInfo.appendText("\n" + newSpace.getName() + " is unowned and costs $" + String.valueOf(newSpace.getCost()) + ".");
          GameInfo.appendText("\nRent of $" + String.valueOf(newSpace.getValue()));

          //Computer buys the space if they have enough money
          if (player.getMoney() >= newSpace.getCost()){
            GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " bought " + newSpace.getName());
            newSpace.setOwner(player.getPlayerNumber());
            board.setSpace(player.getPosition(), newSpace);
            player.setPropertiesOwned(player.getPropertiesOwned() + 1);
            player.owns.add(player.getPosition());
            player.setMoney(player.getMoney() - newSpace.getCost());
            checkBoardSoldOut(board, GameInfo, nextTurn);

            //Computer does not buy the property if they do not have enough money
          } else {
            GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " cannot afford this space.");
          }
        }

        //If the computer player lands on a space they own
        else if (newSpace.getOwner() == getPlayerNumber()) {
          GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " owns " + newSpace.getName());
        }

        //If the computer player lands on a space owned by another player they pay rent to the owner
        //they gameinfo is then updated to display the AI's new balance and the property owners new balance
        else if (newSpace.getOwner() != 0 && newSpace.getOwner() != getPlayerNumber() && newSpace.getOwner() < 10 && newSpace.getOwner() != -1) {
          GameInfo.appendText("\n" + newSpace.getName() + " is owned by player " + newSpace.getOwner() + ". \nPlayer " + player.getPlayerNumber() +  " owes them $" + String.valueOf(newSpace.getValue()));
          player.setMoney(player.getMoney() - newSpace.getValue());
          allPlayers.get(newSpace.getOwner() - 1).setMoney(allPlayers.get(newSpace.getOwner() - 1).getMoney() + newSpace.getValue());
          GameInfo.appendText("\nPlayer " + newSpace.getOwner() + "'s new balance: $" + allPlayers.get(newSpace.getOwner() - 1).getMoney());
          GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + "'s new balance is: $" + player.getMoney());
          player.checkAIFunds(board, GameInfo, allPlayers, player);
        }


        //If the computer lands on chance
        else if (newSpace.getOwner() == 11) {
          Random chanceRandom = new Random();
          int newAdd = (chanceRandom.nextInt(600)) / 50;
          newAdd = newAdd * 50;
          int chanceValue = newAdd - 300;
          if (chanceValue >= 0)
            GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " gained $" + Integer.toString(chanceValue));
          if (chanceValue < 0)
            GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " lost $" + Integer.toString(chanceValue));
          player.setMoney(player.getMoney() + chanceValue);
          player.checkAIFunds(board, GameInfo, allPlayers, player);
        }


        //If the  computer player lands on Income Tax
        else if (newSpace.getOwner() == 12) {
          player.setMoney(player.getMoney() - 100);
          GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " owes the bank $100");
          player.checkAIFunds(board, GameInfo, allPlayers, player);

        }

        //If the computer lands on community fund
        else if (newSpace.getOwner() == 14) {
          player.setMoney(player.getMoney() + 200);
          GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " received $200");
        }

        //If the computer lands on Go to Jail
        else if (newSpace.getOwner() == 15) {
          player.setPosition(6);
          GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " was sent to jail");
          player.setInJail(true);
        }

        //If the computer lands on free parking
        else if (newSpace.getOwner() == 16) {
          Random parkingRandom = new Random();
          int parkingValue = (parkingRandom.nextInt(350)) / 50;
          parkingValue = parkingValue * 50;
          player.setMoney(player.getMoney() + parkingValue);
          GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " gained $" + Integer.toString(parkingValue));
          player.checkAIFunds(board, GameInfo, allPlayers, player);
        }

      //Start of AIs turn if they are stuck in jail
      } else if (player.getInJail()){
          int y = rollDie();
          if (y == 3 || y == 6){
        	GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " rolled a " + String.valueOf(y));
          GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " has been released from jail!");
          player.setInJail(false);
        } else {
          GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " rolled a " + String.valueOf(y));
          GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " must roll a 3 or 6 to be\nreleased from jail.");
          GameInfo.appendText("\nTry again next turn");
        }
      }
    }


    /**
    * method to purchase a propety when they player choose "Yes" after they have landed on
    * on an unowned property
    * @param board, GameInfo, nextTurn
    */
  	public void purchase(Board board, TextArea GameInfo, Button nextTurn){
    		Space newSpace = board.getSpace(this.getPosition());
    		if (this.getMoney() >= newSpace.getCost()) {
    			GameInfo.appendText("\nSuccess");
    			this.setMoney(this.getMoney() - newSpace.getCost());
    			newSpace.setOwner(this.getPlayerNumber());
    			board.setSpace(this.getPosition(), newSpace);
    			this.setPropertiesOwned(this.getPropertiesOwned() + 1);
    			this.owns.add(this.getPosition());
          checkBoardSoldOut(board, GameInfo, nextTurn);
          }
  		  else {
  			 GameInfo.appendText("\nYou cannot afford this space");
  		}}


    /**
    * If the player chooses the sell button and selects a property button from the pop up window
    * this method handles the sale of the property by adding the sale cost to the players balance
    * setting the spaces ownership to zero (which represents an unowned space) and updating the
    * GUI GameInfo to communicate that the property was successfully sold
    * @param board, location, GameInfo
    */
    public void sell(Board board, int location, TextArea GameInfo){
      Space newSpace = board.getSpace(location);
      this.setMoney(this.getMoney() + newSpace.getSaleValue());
      newSpace.setOwner(0);
      board.setSpace(location, newSpace);
      this.owns.remove(new Integer(location));
      this.setPropertiesOwned(getPropertiesOwned() - 1);

      if (this.getIsPlayer() == true){
        GameInfo.appendText("\nYou have sold your property.");
      }
     else if (this.getIsPlayer()== false) {
      GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " has sold " + board.getSpace(location).getName());
    }}

    /**
    * updates player info in TextArea playerInfo
    * @param playerInfo, currentPlayer, board, turnCount
    */
    public void updatePlayerInfo(TextArea playerInfo, Player currentPlayer, Board board, int turnCount){
      playerInfo.setText("Current Position: " + board.getSpace(currentPlayer.getPosition()).getName());
      playerInfo.appendText("\nMoney: $" + currentPlayer.getMoney());
      playerInfo.appendText("\nProperties owned: " + currentPlayer.getPropertiesOwned());
      playerInfo.appendText("\nCurrent Turn: " + turnCount);
    }

    /**
    * if an AI players balance falls below 0, they will be forced to sell properties until the balance is greater than
    * or equal to zero. If they run out of properties to sell and their balance is still below 0 the AI player will be
    * eliminated from the game
    * @param board, GameInfo, allPlayers, player
    */
    public void forceAISale(Board board, TextArea GameInfo, ArrayList<Player> allPlayers, Player player){
      ArrayList<Integer> locations = new ArrayList<Integer>();
      for (int i = 0; i < 24; i++){
        if (player.getSpaceOwned(i) == true){
          locations.add(board.getSpace(i).getLocation());
        }
      }

      int leftToSell = locations.size();
      while (this.getMoney() < 0){
        if (leftToSell > 0){
          int location = locations.get(leftToSell - 1);
          player.sell(board, location, GameInfo);
          leftToSell--;
        }
        else if (leftToSell <= 0 && this.getMoney() < 0){
          GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " is out of money and has no properties to sell.\nThey lose.");
          player.setEliminated(true);
          checkPlayersEliminated(board, GameInfo, allPlayers);
          break;
        }}}



    /**
    * method to check if all but one player has been eliminated. If more than one player is not eliminated then gameplay will
    * continur as normal. If only one player remains not eliminated, then gameplay will come to an end and that player will
    * be delcared the winner
    * @param board, GameInfo, allPlayers
    */
    public void checkPlayersEliminated(Board board, TextArea GameInfo, ArrayList<Player> allPlayers){
      int eliminatedPlayers = 0;
      for (int i = 0; i < allPlayers.size(); i++){
        if (allPlayers.get(i).getEliminated() == true){
          eliminatedPlayers++;
        }
      }


      if (allPlayers.size() - eliminatedPlayers == 1){
        for(int i = 0; i < allPlayers.size(); i++){
          if (allPlayers.get(i).getEliminated() == false){
            winner = allPlayers.get(i);
          }
        }
        GameInfo.setText("There is only one player left standing!");
        GameInfo.appendText("\nPlayer " + winner.getPlayerNumber() + " is the winner!");
        GameInfo.appendText("\nPlayer " + winner.getPlayerNumber() + " won with a networth of $" + winner.getNetWorth(board));
        GameInfo.appendText("\nPlease press the End Game button to see\nthe player standings for all players!");
        setEndGame(true);
      }
    }

    /**
    * Method to check if the AI's balance has fallen below 0. If so, will call the forceAISale method until
    * balance is above zero or the AI is eliminated from gameplay
    * @param board, GameInfo, allPlayers, player
    */
    public void checkAIFunds(Board board, TextArea GameInfo, ArrayList<Player> allPlayers, Player player){
      if (this.getMoney() < 0 && this.getPropertiesOwned() > 0){
        GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " is out of money.\nThey must sell a property or forfeit the game.");
        this.forceAISale(board, GameInfo, allPlayers, player);
      }
      else if (this.getMoney() < 0 && this.getPropertiesOwned() == 0){
        GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " is out of money and has no properties to sell.\nThey lose.");
        this.setEliminated(true);
        checkPlayersEliminated(board, GameInfo, allPlayers);
      }
    }

    /**
    * Method to check if the human players balance has fallen below zero. If so, play will not be able to proceed
    * until the player sells enough property to being their balance above zero, or until they run out of property
    * at which point they will be eliminated
    * @param GameInfo, nexgtTurn, allPlayers, board
    */
    public void checkPlayerFunds(TextArea GameInfo, Button nextTurn, ArrayList<Player> allPlayers, Board board){

      if (this.getMoney() < 0 && this.getPropertiesOwned() > 0){
        GameInfo.appendText("\nYou are out of money\nSell a property or you will forfeit the game.");
        nextTurn.setDisable(true);
      }

      else if (this.getMoney() < 0 && this.getPropertiesOwned() == 0){
        GameInfo.appendText("\nYou are out of money and have no properties to sell.\nYou have been eliminated.");
        this.setEliminated(true);
        checkPlayersEliminated(board, GameInfo, allPlayers);
        nextTurn.setDisable(false);

      } else if (this.getMoney() >= 0){
        nextTurn.setDisable(false);
      }
    }

    /**
    * Method to check if there are any spaces left on the board to purchase.
    * If not, gameplay will be stopped and players will be propmted to End Game to see who the winner is
    * If there are spaces still left available for purchase, gameplay will continue as normal
    * @param board, GameInfo, nextTurn
    */
    public void checkBoardSoldOut(Board board, TextArea GameInfo, Button nextTurn){

      int unownedSpaces = 0;
      for (int i = 0; i < 24; i++){
        if (board.getSpace(i).getOwner() == 0){
          unownedSpaces++;
        }
      }

      if (unownedSpaces == 0){
        GameInfo.setText("All spaces have been sold. Game Over.");
        GameInfo.appendText("\nPlease press the End Game button to see\nthe player standings for all players");
        nextTurn.setDisable(true);
        setEndGame(true);
      }
      else if (unownedSpaces >= 1){
        nextTurn.setDisable(false);
      }
    }

    /**
    * updates the current position, balance, properties owned for each player in the game
    * also reports which players are eliminated (if any)
    * @param playerStandings, allPLayers, board
    */
    public void updatePlayerStandings(TextArea playerStandings, ArrayList<Player> allPlayers, Board board){
        playerStandings.setText("Player Standings");
      for (int i = 0; i < allPlayers.size(); i++){
        playerStandings.appendText("\n\nPlayer " + allPlayers.get(i).getPlayerNumber() + " :");
        playerStandings.appendText("\nCurrent Position: " + board.getSpace(allPlayers.get(i).getPosition()).getName());
        playerStandings.appendText("\nMoney: $" + allPlayers.get(i).getMoney() + "\nProperties owned: " + allPlayers.get(i).getPropertiesOwned());
        if (allPlayers.get(i).getEliminated() == true){
        playerStandings.appendText("\nELIMINATED");
}
      }}

      /**
      * Method to calculate the networth of a player, which is the total amount of money they have
      * plus the purchase price of any properties they own
      * @param board
      */
    public int getNetWorth(Board board) {
      int netWorth = 0;
      	for (int i = 0; i < 24; i++) {
      		if (board.getSpace(i).getOwner() == this.getPlayerNumber()){
      			netWorth += board.getSpace(i).getCost();
      		}
      	}
      	return netWorth + this.getMoney();
      }
  }
