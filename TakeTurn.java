import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import java.util.Collections;

class TakeTurn extends Player{

  public TakeTurn(){
    Player p = new Player();

  }

  /**
  * this is the method that allows the human players to take their turn, updated information on all players as play progresses
  * @param board, player, GameInfo, positive, negative, nextTurn, allPlayers
  */
  public void takeTurnGui(Board board, Player player, TextArea GameInfo, Button positive, Button negative, Button nextTurn, ArrayList<Player> allPlayers){
    Scanner input = new Scanner(System.in);

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
            GameInfo.appendText("\nYou lost $" + Integer.toString(Math.abs(chanceValue)));
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
        GameInfo.appendText("\nPlayer " + player.getPlayerNumber() + " lost $" + Integer.toString(Math.abs(chanceValue)));
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

}
