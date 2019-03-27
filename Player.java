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
    private int playersEliminated = 0;
    private Player winner;


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

    public boolean getEliminated(){
      return this.eliminated;
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

    public void setEliminated(boolean isEliminated){
       eliminated = isEliminated;
     }

    public void setIsPlayer(boolean newIsPlayer){
      this.isPlayer = newIsPlayer;
    }

    public boolean getInJail(){
      return inJail;
    }

    public int getPlayersEliminated(){
      return playersEliminated;
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
      eliminated = false;
    }

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

  	public void takeTurnGui(Board board, Player player, TextArea GameInfo, Button positive, Button negative, Button nextTurn, ArrayList<Player> allPlayers){
  		Scanner input = new Scanner(System.in);
  		//Creates a new scanner
    		GameInfo.setText("It is player " + player.getPlayerNumber() + "'s turn");
        if (player.getInJail() == false){
      		int x = rollDie();
      		GameInfo.appendText("\nYou rolled a " + String.valueOf(x));
      		int oldPosition = player.getPosition();
      		player.setPosition(oldPosition + x);

      		if (player.getPosition() >= board.getLength()) {
      			GameInfo.appendText("\nYou passed GO and collected $200");
      			player.setPosition(player.getPosition() - board.getLength());
      			player.setMoney(player.getMoney() + 200);
            nextTurn.setDisable(false);
      		}

      		Space newSpace = board.getSpace(player.getPosition());
      		GameInfo.appendText("\nYou landed on " + newSpace.getName());

      		/**
      		 *when a player comes to an unowned place, player will have the opinion to determine purchase this very place or not.
      		 *if player decide to own the place and have enough money, player will lose the amount of money and own this place.
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

      		//If the player lands on a space owned by another player
      		else if (newSpace.getOwner() != 0 && newSpace.getOwner() != getPlayerNumber() && newSpace.getOwner() < 10 && newSpace.getOwner() != -1) {
          	GameInfo.appendText("\n" + newSpace.getName() + " is owned by player " + newSpace.getOwner() + ". \nYou owe them $" + String.valueOf(newSpace.getValue()));
            player.setMoney(player.getMoney() - newSpace.getValue());
            allPlayers.get(newSpace.getOwner() - 1).setMoney(allPlayers.get(newSpace.getOwner() - 1).getMoney() + newSpace.getValue());
            GameInfo.appendText("\nPlayer " + newSpace.getOwner() + "'s new balance: $" + allPlayers.get(newSpace.getOwner() - 1).getMoney());
            GameInfo.appendText("\nYour new balance is: $" + player.getMoney());
            nextTurn.setDisable(true);
            player.checkPlayerFunds(GameInfo, nextTurn);
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
            player.checkPlayerFunds(GameInfo, nextTurn);

      		}

      		//If the player lands on Income Tax
      		else if (newSpace.getOwner() == 12) {
      			player.setMoney(player.getMoney() - 100);
      			GameInfo.appendText("\nYou owe the bank $100");
            player.checkPlayerFunds(GameInfo, nextTurn);

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
            player.checkPlayerFunds(GameInfo, nextTurn);
        }

        //If the player is stuck in jail
        } else if (player.getInJail()){
          int x = rollDie();
          if (x == 6){
      		GameInfo.appendText("\nYou rolled a " + String.valueOf(x));
          GameInfo.appendText("\nYou have been released from jail!");
          player.setInJail(false);
          nextTurn.setDisable(false);
        } else {
          GameInfo.appendText("\nYou rolled a " + String.valueOf(x));
          GameInfo.appendText("\nYou must roll a 6 to be\nreleased from jail.");
          GameInfo.appendText("\nTry again next turn");
          nextTurn.setDisable(false);
        }
        }
    }

    public void takeTurnAI(Board board, Player player, TextArea GameInfo, ArrayList<Player> allPlayers, Button nextTurn){

      GameInfo.setText("It is player " + player.getPlayerNumber() + "'s turn");
      if (player.getInJail() == false){
        int x = rollDie();
        GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " rolled a " + String.valueOf(x));
        int oldPosition = player.getPosition();
        player.setPosition(oldPosition + x);
        if (player.getPosition() >= board.getLength()) {
          GameInfo.appendText("\nYou passed GO and collected $200");
          player.setPosition(player.getPosition() - board.getLength());
          player.setMoney(player.getMoney() + 200);
        }

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

        //If the computer player lands on a space owned by another player
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

      //If the computer is stuck in jail
      } else if (player.getInJail()){
          int y = rollDie();
          if (y == 6){
        	GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " rolled a " + String.valueOf(y));
          GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " has been released from jail!");
          player.setInJail(false);
        } else {
          GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " rolled a " + String.valueOf(y));
          GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " must roll a 6 to be\nreleased from jail.");
          GameInfo.appendText("\nTry again next turn");
        }
      }
    }


    /**
    * purchases property player is on when positive button is clicked in Gui
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
    * sells selected property
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
    * updates player info in TextArea in Gui
    */
    public void updatePlayerInfo(TextArea playerInfo, Player currentPlayer, Board board, int turnCount){
      playerInfo.setText("Current Position: " + board.getSpace(currentPlayer.getPosition()).getName());
      playerInfo.appendText("\nMoney: $" + currentPlayer.getMoney());
      playerInfo.appendText("\nProperties owned: " + currentPlayer.getPropertiesOwned());
      playerInfo.appendText("\nCurrent Turn: " + turnCount);
    }


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
          playersEliminated++;
          checkPlayersEliminated(GameInfo);
          break;
        }}}

    public void checkPlayersEliminated(TextArea GameInfo){
      if ((getTotalHumanPlayers() + getTotalComputerPlayers()) - getPlayersEliminated() == 1){
        for (int i = 0; i < allPlayers.size(); i++){
          if (allPlayers.get(i).getEliminated() == false){
            winner = allPlayers.get(i);
          }
        }
        GameInfo.appendText("\nPlayer " + winner.getPlayerNumber() + " is the winner!");
      }
    }

    public void checkAIFunds(Board board, TextArea GameInfo, ArrayList<Player> allPlayers, Player player){
      if (this.getMoney() < 0 && this.getPropertiesOwned() > 0){
        GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " is out of money.\nThey must sell a property or forfeit the game.");
        this.forceAISale(board, GameInfo, allPlayers, player);
      }
      else if (this.getMoney() < 0 && this.getPropertiesOwned() == 0){
        GameInfo.appendText("\nPlayer " + this.getPlayerNumber() + " is out of money and has no properties to sell.\nThey lose.");
        this.setEliminated(true);
        playersEliminated++;
        checkPlayersEliminated(GameInfo);
      }
    }

    public void checkPlayerFunds(TextArea GameInfo, Button nextTurn){

      if (this.getMoney() < 0 && this.getPropertiesOwned() > 0){
        GameInfo.appendText("\nYou are out of money\nSell a property or you will forfeit the game.");
        nextTurn.setDisable(true);
      }
      else if (this.getMoney() < 0 && this.getPropertiesOwned() == 0){
        GameInfo.appendText("\nYou are out of money and have no properties to sell.\nYou have been eliminated.");
        this.setEliminated(true);
        playersEliminated++;
        checkPlayersEliminated(GameInfo);
        nextTurn.setDisable(false);

      } else if (this.getMoney() >= 0){
        nextTurn.setDisable(false);
      }
    }

    public void checkBoardSoldOut(Board board, TextArea GameInfo, Button nextTurn){
      int unownedSpaces = 0;
      for (int i = 0; i < 24; i++){
        if (board.getSpace(i).getOwner() == 0){
          unownedSpaces++;
        }
      }
      if (unownedSpaces == 0){
        GameInfo.appendText("\nAll spaces have been sold. Game Over.");
        nextTurn.setDisable(true);
      }
      else if (unownedSpaces >= 1){
        nextTurn.setDisable(false);
      }
    }

    public void updatePlayerStandings(TextArea playerStandings, ArrayList<Player> allPlayers, Board board){
        playerStandings.setText("Player Standings");
      for (int i = 0; i < allPlayers.size(); i++){
        playerStandings.appendText("\n\nPlayer " + allPlayers.get(i).getPlayerNumber() + " :");
        playerStandings.appendText("\nCurrent Position: " + board.getSpace(allPlayers.get(i).getPosition()).getName());
        playerStandings.appendText("\nMoney: $" + allPlayers.get(i).getMoney() + "\nProperties owned: " + allPlayers.get(i).getPropertiesOwned());
      }}

    //method to calculate netWorth
    public int getNetWorth(Board board) {
      int netWorth = getMoney();
      	for (int i = 0; i < board.getLength(); i++) {
      		if (board.getSpace(i).getOwner() == getPlayerNumber()) {
      			netWorth += board.getSpace(i).getCost();
      		}
      	}
      	return netWorth;
      }
  }
