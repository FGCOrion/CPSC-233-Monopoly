import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import java.util.ArrayList;
import java.util.Collections;

public class Gui extends Application{

  //instance varaibles for amount of players
  private int totalHumanPlayers = 0;
  private int totalComputerPlayers = 0;
  private int totalPlayers = 0;

  //Labels for messages on start menu
  private Label welcome = new Label("Welcome to Monopoly!");
  private Label humanPlayers = new Label("How many human players?");
  private Label AIPlayers = new Label("How many computer players?");
  private Label totalPlayersPlaying = new Label("Total players: " + totalPlayers);
  private Label errorMessage = new Label();
	private ArrayList<Player> allPlayers = new ArrayList<Player>();

  //buttons to choose how many human players in start menu
  private Button player1 = new Button("1");
  private Button player2 = new Button("2");
  private Button player3 = new Button("3");
  private Button player4 = new Button("4");

  //buttons to choose how many computer players in start menu
  private Button computer0 = new Button("0");
  private Button computer1 = new Button("1");
  private Button computer2 = new Button("2");
  private Button computer3 = new Button("3");

  //button to start gameplay in start menu
  private Button begin = new Button("Start Game!");

  /**
	* board set up variables
	*/
	private Board numOfLand = new Board();
	private int playerFlag = 1;
  private boolean endGame = false;


	/**
	* Setup for labels in GUI that give information regarding each players total money, property,
	* whose turn it is and general information about spaces on the board and instance variables
	*/
	private Label status = new Label();
	private Label playerTurn = new Label("Player 1's turn!");
	private Label gameInfo = new Label("Game Information");
	private Label SpaceInfo = new Label("Space Info");
	private TextArea playerInfo = new TextArea("Information about each player\nwill be displayed here");
	private TextArea GameInfo = new TextArea("Information about each turn\nwill be displayed here");
	private TextArea spaceInfo = new TextArea("Click a space on the board for more information");
  private TextArea playerStandings = new TextArea("Player standings will be\ndisplayed here");
	private String basicText = "";
	private int turnCount = 0;

  // Accessor Methods
  /**
  * returns the amount of human players
  * @return totalHumanPlayers
  */
  public int getTotalHumanPlayers(){
    return totalHumanPlayers;
  }

  /**
  * returns true if end conditions have been met
  * returns false otherwise
  * @return endGame
  */
  public boolean getEndGame(){
      return endGame;
  }


  /**
  * returns the amount of computer players
  * @return totalComputerPLayers
  */
  public int getTotalComputerPlayers(){
    return totalComputerPlayers;
  }

	/**
	* returns 1 if it is player 1's turn or 2 if it is player 2's turn
	* @return playerFlag
	*/
  public int getPlayerFlag(){
    return playerFlag;
  }

  //Mutator Methods

  /**
  * sets the varibale endGame to true if end conditions have been met
  * sets to false if end conditions have not been met
  * @param isEndGame
  */
  public void setEndGame(boolean isEndGame){
    endGame = isEndGame;
  }

  /**
  * sets the instance variable playerFlag which corresponds to each player
  * @param playerFlag
  */
  public void setPlayerFlag(int playerFlag) {
    this.playerFlag = playerFlag;
  }


  public static void main(String[] args){
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage){

        GridPane root = new GridPane();
        		final int numCols = 18 ;
            final int numRows = 11 ;
            for (int i = 0; i < numCols; i++) {
                ColumnConstraints colConst = new ColumnConstraints();
                colConst.setPercentWidth(100.0 / numCols);
                root.getColumnConstraints().add(colConst);
            }
            for (int i = 0; i < numRows; i++) {
                RowConstraints rowConst = new RowConstraints();
                rowConst.setPercentHeight(100.0 / numRows);
                root.getRowConstraints().add(rowConst);
            }

    		playerTurn.setTextFill(Color.BLUE);
    		root.add(playerInfo, 1, 1, 4, 4);
    		root.add(GameInfo, 1, 6, 4, 4);
    		root.add(playerTurn, 2, 0, 2, 1);
    		root.add(gameInfo, 2, 5, 2, 1);
    		root.add(spaceInfo, 7, 9, 5, 1);
    		root.add(SpaceInfo, 9, 8, 2, 1);
        root.add(playerStandings, 14, 1, 3, 9);

    		/**
    		* "Yes" button displayed beside "Roll Dice" button to affirm decision to purchase property
    		*/
        Button positive = new Button("Yes");
        positive.setMaxWidth(125);
        positive.setMaxHeight(400);
        root.add(positive, 8, 4);
        positive.setDisable(true);

    		/**
    		* "No" button displayed beside "Roll Dice" button to decline decision to purchase property
    		*/
        Button negative = new Button("No");
        negative.setMaxWidth(125);
        negative.setMaxHeight(400);
        root.add(negative, 10, 4);
        negative.setDisable(true);

    		/**
    		* next turn button to switch turn to other player
    		*/
    		Button nextTurn = new Button("Next\nTurn");
    		nextTurn.setMaxWidth(125);
    		nextTurn.setMaxHeight(400);
    		root.add(nextTurn, 9, 5);
        nextTurn.setDisable(true);

    		/**
    		* "Roll Dice" button which initiates a die roll when clicked on the players turn
    		*/
    		Button roll = new Button("Roll\nDie");
    		roll.setMaxWidth(125);
    		roll.setMaxHeight(400);
    	  root.add(roll, 9, 4);

    		/**
    		* Sell property button to sell properties back to the bank for half of the cost originally paid
    		*/
    		Button sell = new Button("Sell");
    		sell.setMaxWidth(125);
    		sell.setMaxHeight(400);
    		root.add(sell, 9, 3);


    		 /**
     		 * When player selects the sell property button, method will loop through the properties the player owns
         * and add a button to a pop up window for each property owned. The player is then able to select a button
         * and sell the property represented, at which point the button is removed from the pop up window
     		 */
    		sell.setOnAction(new EventHandler<ActionEvent>(){
    			@Override
    			public void handle(ActionEvent event){
            if (allPlayers.get(getPlayerFlag() - 1).getIsPlayer() == true){
    				final Stage sellProperties = new Stage();
    				 sellProperties.initModality(Modality.APPLICATION_MODAL);
    				 sellProperties.initOwner(primaryStage);
    				 VBox sellBox = new VBox(5);

    				 ArrayList<Integer> locations = new ArrayList<Integer>();
    				 ArrayList<Button> buttons = new ArrayList<Button>();

    				 for (int i = 0; i < 24; i++){
    					if (allPlayers.get(getPlayerFlag() - 1).getSpaceOwned(i) == true){
    						buttons.add(new Button(numOfLand.getSpace(i).getName() + ": $" + numOfLand.getSpace(i).getSaleValue()));
    						locations.add(numOfLand.getSpace(i).getLocation());
    						 }
    					 }

    					 sellBox.getChildren().addAll(buttons);
    					 for (int i = 0; i < buttons.size(); i++){
    						 final Button myButton = buttons.get(i);
    						 final int location = locations.get(i);

                 //set up of buttons for each property the player owns
    	           myButton.setOnAction(new EventHandler<ActionEvent>() {
              	 		public void handle(ActionEvent event) {
    									allPlayers.get(getPlayerFlag() - 1).sell(numOfLand, location, GameInfo);
    									sellBox.getChildren().remove(myButton);
                      final Player currentPlayer = allPlayers.get(getPlayerFlag() - 1);
                      currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                      currentPlayer.checkPlayerFunds(GameInfo, nextTurn, allPlayers, numOfLand);
    									myButton.setDisable(true);
                      if (allPlayers.get(getPlayerFlag() - 1).getMoney() > 0){
                        nextTurn.setDisable(false);
                  }}});
                }

    					 sellBox.setAlignment(Pos.CENTER);
    					 Scene sellScene = new Scene(sellBox, 300, 500);
    					 sellProperties.setScene(sellScene);
    					 sellProperties.show();
    				 }}});

    		/**
    		* Event Handler for roll button updates labels to represent players position, money, properties owned
    		* and total turns taken, only allows roll if no end conditions have been met
    		*/
        roll.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event){
            if (getEndGame() == false){
              roll.setDisable(true);
							final Player currentPlayer = allPlayers.get(getPlayerFlag() - 1);
              currentPlayer.takeTurnGui(numOfLand, currentPlayer, GameInfo, positive, negative, nextTurn, allPlayers);
              currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);

              if (getPlayerFlag() == 1){
                turnCount += 1;
            } else if (getEndGame() == true){
              GameInfo.appendText("\nPlease press the End Game button to see who won!");
              nextTurn.setDisable(true);
              roll.setDisable(true);
              sell.setDisable(true);
            }
              }}});

    		/**
    		* Event Handler for "Next Turn" button after a players turn is done, the player must click the next turn button
    		* to change turn to next player. This changes the player information to update to the current players information
    		* updates label to display whose turn it is, and passes over any players who may have been previously eliminated
    		*/
    		nextTurn.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event){
            if (getEndGame() == false){

              //If it is player ones turn and player two is not eliminated
              if (getPlayerFlag() == 1 && allPlayers.get(1).getEliminated() == false){
                playerTurn.setText("Player 2's Turn");
                playerTurn.setTextFill(Color.RED);

                //If player two is a human player
                if (allPlayers.get(1).getIsPlayer() == true){
                  final Player currentPlayer = allPlayers.get(1);
                  roll.setDisable(false);
                  nextTurn.setDisable(true);
                  setPlayerFlag(2);
                  currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                  currentPlayer.updatePlayerStandings(playerStandings, allPlayers, numOfLand);
                }

                //If player two is a computer player
                else if (allPlayers.get(1).getIsPlayer() == false){
                  roll.setDisable(true);
                  final Player currentPlayer = allPlayers.get(1);
                  allPlayers.get(1).takeTurnAI(numOfLand, currentPlayer, GameInfo, allPlayers, nextTurn);
                  currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                  currentPlayer.updatePlayerStandings(playerStandings, allPlayers, numOfLand);
                  nextTurn.setDisable(false);
                  setPlayerFlag(2);
                }
              }

              //If player two has been eliminated
              else if (getPlayerFlag() == 1 && allPlayers.get(1).getEliminated() == true && totalPlayers == 2){
                GameInfo.setText("Player 2 has been eliminated\nPlayer 1 is the winner!");
                GameInfo.appendText("\nPlease press the End Game button to see\nthe player standings for all players!");
                roll.setDisable(true);
                nextTurn.setDisable(true);
                sell.setDisable(true);
              }

              //if there are more than two players and it is player ones turn and player two has been eliminated
              else if (getPlayerFlag() == 1 && allPlayers.get(1).getEliminated() == true && totalPlayers > 2){
                GameInfo.setText("Player 2 has been eliminated\nPress Next Turn to continue play");
                  final Player currentPlayer = allPlayers.get(0);
                  currentPlayer.checkPlayersEliminated(numOfLand, GameInfo, allPlayers);
                  roll.setDisable(true);
                  nextTurn.setDisable(false);
                  setPlayerFlag(2);
              }

              //If it is player twos turn and player one has not been eliminated
              //Player one is always human, so we don't need to worry about an AI situation
              else if (getPlayerFlag() == 2){
                if (totalPlayers == 2 && allPlayers.get(0).getEliminated() == false){
                  playerTurn.setText("Player 1's Turn");
                  playerTurn.setTextFill(Color.BLUE);
                  roll.setDisable(false);
                  nextTurn.setDisable(true);
                  setPlayerFlag(1);
                  final Player currentPlayer = allPlayers.get(0);
                  currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                  currentPlayer.updatePlayerStandings(playerStandings, allPlayers, numOfLand);
              }

                //If player one has been eliminated in a two player game, player 2 wins
                else if (totalPlayers == 2 && allPlayers.get(0).getEliminated() == true){
                  GameInfo.setText("Player 1 has been eliminated\nPlayer 2 is the winner!");
                  GameInfo.appendText("\nPlease press the End Game button to see\nthe player standings for all players!");
                  roll.setDisable(true);
                  nextTurn.setDisable(true);
                  sell.setDisable(true);
                  turnCount++;
              }

                //If there are more than 2 players in a game and player 3 is not eliminated
                else if (totalPlayers > 2 && allPlayers.get(2).getEliminated() == false){
                  playerTurn.setText("Player 3's Turn");
                  playerTurn.setTextFill(Color.ORANGE);

                  //If player three is a human player
                  if (allPlayers.get(2).getIsPlayer() == true){
                    final Player currentPlayer = allPlayers.get(2);
                    roll.setDisable(false);
                    nextTurn.setDisable(true);
                    setPlayerFlag(3);
                    currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                    currentPlayer.updatePlayerStandings(playerStandings, allPlayers, numOfLand);
                  }

                  //If player three is a computer player
                  else if (allPlayers.get(2).getIsPlayer() == false){
                    roll.setDisable(true);
                    final Player currentPlayer = allPlayers.get(2);
                    allPlayers.get(2).takeTurnAI(numOfLand, currentPlayer, GameInfo, allPlayers, nextTurn);
                    currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                    currentPlayer.updatePlayerStandings(playerStandings, allPlayers, numOfLand);
                    nextTurn.setDisable(false);
                    setPlayerFlag(3);
                  }
                }

                //If there are more than 2 players in a game and player 3 has been eliminated
                else if (totalPlayers > 2 && allPlayers.get(2).getEliminated() == true){
                  GameInfo.setText("Player 3 has been eliminated\nPress Next Turn to continue play");
                   final Player currentPlayer = allPlayers.get(0);
                   currentPlayer.checkPlayersEliminated(numOfLand, GameInfo, allPlayers);
                    roll.setDisable(true);
                    nextTurn.setDisable(false);
                    setPlayerFlag(3);
                }
              }

              else if (getPlayerFlag() == 3){
                //if in a three player game, player one is not eliminated
                if (totalPlayers == 3 && allPlayers.get(0).getEliminated() == false){
                  playerTurn.setText("Player 1's Turn");
                  playerTurn.setTextFill(Color.BLUE);
                  roll.setDisable(false);
                  nextTurn.setDisable(true);
                  setPlayerFlag(1);
                  final Player currentPlayer = allPlayers.get(0);
                  currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                  currentPlayer.updatePlayerStandings(playerStandings, allPlayers, numOfLand);
                }

                //if in a three player game player one is eliminated
                else if (totalPlayers == 3 && allPlayers.get(0).getEliminated() == true){
                  GameInfo.setText("Player 1 has been eliminated\nPress Next Turn to continue play");
                  final Player currentPlayer = allPlayers.get(0);
                  currentPlayer.checkPlayersEliminated(numOfLand, GameInfo, allPlayers);
                  roll.setDisable(true);
                  nextTurn.setDisable(false);
                  setPlayerFlag(1);
                  turnCount++;
                }

                //if in a four player game , player four is not eliminated
                else if (totalPlayers == 4 && allPlayers.get(3).getEliminated() == false){
                  playerTurn.setText("Player 4's Turn");
                  playerTurn.setTextFill(Color.PURPLE);

                  //If player four is a human player
                  if (allPlayers.get(3).getIsPlayer() == true){
                    final Player currentPlayer = allPlayers.get(3);
                    roll.setDisable(false);
                    nextTurn.setDisable(true);
                    setPlayerFlag(4);
                    currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                    currentPlayer.updatePlayerStandings(playerStandings, allPlayers, numOfLand);
                  }

                  //If player four is a computer player
                  else if (allPlayers.get(3).getIsPlayer() == false){
                    roll.setDisable(true);
                    final Player currentPlayer = allPlayers.get(3);
                    allPlayers.get(3).takeTurnAI(numOfLand, currentPlayer, GameInfo, allPlayers, nextTurn);
                    currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                    currentPlayer.updatePlayerStandings(playerStandings, allPlayers, numOfLand);
                    nextTurn.setDisable(false);
                    setPlayerFlag(4);
                  }
                }

                //if in a four player game, player four is eliminated
                else if (totalPlayers == 4 && allPlayers.get(3).getEliminated() == true){
                  GameInfo.setText("Player 4 has been eliminated\nPress Next Turn to continue play");
                  final Player currentPlayer = allPlayers.get(0);
                  currentPlayer.checkPlayersEliminated(numOfLand, GameInfo, allPlayers);
                  roll.setDisable(true);
                  nextTurn.setDisable(false);
                  setPlayerFlag(4);
                }
              }

              //four player game
              else if (getPlayerFlag() == 4){

                //if player one has not been eliminated
                if (allPlayers.get(0).getEliminated() == false){
                  playerTurn.setText("Player 1's Turn");
                  playerTurn.setTextFill(Color.BLUE);
                  roll.setDisable(false);
                  nextTurn.setDisable(true);
                  setPlayerFlag(1);
                  final Player currentPlayer = allPlayers.get(0);
                  currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                  currentPlayer.updatePlayerStandings(playerStandings, allPlayers, numOfLand);
                }

                //if player one has been eliminated
                else if (allPlayers.get(0).getEliminated() == true){
                  GameInfo.setText("Player 1 has been eliminated\nPlease press Next Turn button to advance play");
                  final Player currentPlayer = allPlayers.get(0);
                  currentPlayer.checkPlayersEliminated(numOfLand, GameInfo, allPlayers);
                  roll.setDisable(true);
                  nextTurn.setDisable(false);
                  setPlayerFlag(1);
                  turnCount++;
                }}}

          //if end conditions for the game have been met, then ends the game
          else if (getEndGame() == true){
            GameInfo.appendText("\nPlease press the End Game button to see who won!");
            nextTurn.setDisable(true);
            roll.setDisable(true);
            sell.setDisable(true);
        }}});


    		/**
    		* Event Handler for positive ("Yes") button updates labels to reflect the property purchased by player
    		* and updates players money to reflect the purchase of said property which is (players money - the price of property purchased)
    		*/
        positive.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event){
              final Player currentPlayer = allPlayers.get(getPlayerFlag() - 1);
              currentPlayer.purchase(numOfLand, GameInfo, nextTurn);
              currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
              positive.setDisable(true);
              negative.setDisable(true);
              currentPlayer.checkBoardSoldOut(numOfLand, GameInfo, nextTurn);
              }
            });

    		/**
    		* Event Handler for negative ("No") button declines purchase/sale of property and proceeds to next players turn
    		*/
        negative.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event){
              negative.setDisable(true);
              positive.setDisable(true);
    					nextTurn.setDisable(false);
              }
          });

    		/**
    		* Set up of buttons for the main gameboard
    		*/
        ArrayList<Button> boardButtons = new ArrayList<Button>();
        int space = 1;
        int leftspace = 12;

        //initializes 24 buttons (one for each space on the board)
        for (int s = 0; s < 24; s++){
          boardButtons.add(new Button());
        }

        //sets the size of each button
        for (int i = 0; i < boardButtons.size(); i++){
          boardButtons.get(i).setMaxWidth(150);
          boardButtons.get(i).setMaxHeight(400);

          //assigns each button to its appropriate spot on the board
          if (i <= 6){
            root.add(boardButtons.get(i), 6, 7 - i);
          } else if (i >= 7 && i <= 12){
            root.add(boardButtons.get(i), i, 1);
          } else if (i >= 13 && i <= 18){
            root.add(boardButtons.get(i), 12, (space + 1));
            space++;
          } else if (i >= 19 && i <= 23){
            root.add(boardButtons.get(i), (leftspace - 1) ,7);
            leftspace--;
          }
        }

        /**
        * set up of event handlers for all 24 buttons on the board. If the space is a property that players are able to purchase
        * then the name, cost, rent and owner information is listed when the player clicks on the space. If the space is not able to be
        * purchased, then when a player clicks on the space information about what that space is/does is listed
        */
        for (int i = 0; i < 24; i++){
          final Space newSpace = numOfLand.getSpace(i);
          final String spaceName = numOfLand.getSpace(i).getName();
          if (i == 1||i == 3||i == 4||i == 7||i == 8||i == 10||i == 13||i == 14||i == 16||i == 19||i == 20||i == 22||i == 23){
            final int spaceCost = numOfLand.getSpace(i).getCost();
            final int spaceRent = numOfLand.getSpace(i).getValue();
            boardButtons.get(i).setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
              basicText = spaceName + ", Cost " + spaceCost + ", Rent " + spaceRent;
              newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
            }
            });
        } else if (i==0){
          boardButtons.get(i).setText(spaceName);
          boardButtons.get(i).setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event) {
            spaceInfo.setText("Players start game here");
            }});

        } else if (i==6){
          boardButtons.get(i).setText(spaceName);
          boardButtons.get(i).setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event) {
            spaceInfo.setText("Player is sent here when\nthey land on go to jail");
            }});

        } else if (i==2||i==9||i==15){
          final int spaceCost = numOfLand.getSpace(i).getCost();
          final int spaceRent = numOfLand.getSpace(i).getValue();
          boardButtons.get(i).setText("Rail\nRoad");
          boardButtons.get(i).setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event) {
            basicText = spaceName + ", Cost " + spaceCost + ", Rent " + spaceRent;
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }});

        } else if (i==5||i==17){
          boardButtons.get(i).setText(spaceName);
          boardButtons.get(i).setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event) {
            spaceInfo.setText("Receive a random amount of money\nbetween -300 and 250");
        }});

        } else if (i==11){
          boardButtons.get(i).setText("Income\nTax");
          boardButtons.get(i).setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event) {
            spaceInfo.setText("Pay the bank $100");
          }});

        } else if (i==12){
          boardButtons.get(i).setText("Free\nParking");
          boardButtons.get(i).setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event) {
            spaceInfo.setText("Receive a random amount of money");
          }});

        } else if (i==18){
          boardButtons.get(i).setText("Go to\nJail");
          boardButtons.get(i).setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event) {
            spaceInfo.setText("Sends you to jail");
          }});

        } else if (i==21){
          boardButtons.get(i).setText("Comm\nFund");
          boardButtons.get(i).setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event) {
            spaceInfo.setText("Funding here");
          }});
        }

        //Sets the colours of buyable spaces to match their names
        boardButtons.get(1).setStyle("-fx-background-color: #FFFF00");
        boardButtons.get(3).setStyle("-fx-background-color: #EEEE00");
        boardButtons.get(4).setStyle("-fx-background-color: #FFFF00");
        boardButtons.get(7).setStyle("-fx-background-color: #00F5FF");
        boardButtons.get(8).setStyle("-fx-background-color: #00E5EE");
        boardButtons.get(10).setStyle("-fx-background-color: #00F5FF");
        boardButtons.get(13).setStyle("-fx-background-color: #FF0000");
        boardButtons.get(14).setStyle("-fx-background-color: #EE0000");
        boardButtons.get(16).setStyle("-fx-background-color: #FF0000");
        boardButtons.get(19).setStyle("-fx-background-color: #EEC900");
        boardButtons.get(20).setStyle("-fx-background-color: #FFD700");
        boardButtons.get(22).setStyle("-fx-background-color: #00FA9A");
        boardButtons.get(23).setStyle("-fx-background-color: #7CFC00");

        /**
      	* Exit button to prematurely end game without meeting end condition and pop up window to confirm
      	* if you'd like to quit or not
      	*/
      	Button btClose = new Button("End\nGame");
      	btClose.setMaxWidth(75);
      	btClose.setMaxHeight(75);
      	root.add(btClose, 17, 0);
      	btClose.setOnAction(new EventHandler<ActionEvent>(){
      		@Override
      		public void handle(ActionEvent event) {
      			 final Stage exitChoice = new Stage();
      			 exitChoice.initModality(Modality.APPLICATION_MODAL);
      			 exitChoice.initOwner(primaryStage);
      			 HBox exitBox = new HBox(20);
      			 Button close = new Button("End Game");
      			 Button doNotClose = new Button("Keep Playing");
      			 exitBox.setAlignment(Pos.CENTER);

      			 close.setOnAction(new EventHandler<ActionEvent>(){
      				 @Override
      				 public void handle(ActionEvent event){
      					 primaryStage.close();
  						 //displays player stands
  						 final Stage endGameBox = new Stage();
  						 final HBox endGame = new HBox(50);
  						 endGameBox.initModality(Modality.APPLICATION_MODAL);
  						 endGameBox.initOwner(primaryStage);
  						 endGameBox.setTitle("Game Over!");

  						 /**
  						 * If you select that you would like to exit the game then this determines who the winner is
               * based on their net worth. The main GUI is then closed, and a new window is opened which displays
               * the rankings of each player from first to last, who won and how much money/property they had.
  						 */
  						 TextArea playerStats = new TextArea();
  						 playerStats.appendText("Player Stats: ");

  						 ArrayList<Integer> netWorths = new ArrayList<Integer>();

              //sets up an array with the players networths
  						 for (int i = 0; i < allPlayers.size(); i++){
                netWorths.add(allPlayers.get(i).getNetWorth(numOfLand));
  							playerStats.appendText("\n\nPlayer " + allPlayers.get(i).getPlayerNumber() + " :");
  							playerStats.appendText("\nMoney: $" + allPlayers.get(i).getMoney() + "\nProperties owned: " + allPlayers.get(i).getPropertiesOwned());
  							playerStats.appendText("\nNetworth: $" + allPlayers.get(i).getNetWorth(numOfLand));
  						 }

              //sorts the networths from lowest to highest, then inverts the list to be from first to last place
  						 Collections.sort(netWorths);
               Collections.reverse(netWorths);

  						 //finds which players networth matches the array index
  						 ArrayList<Player> ranks = new ArrayList<Player>();
  						 for (int i = 0; i < netWorths.size(); i++) {
  							 for (int x = 0; x < allPlayers.size(); x++) {
  								 if (netWorths.get(i) == allPlayers.get(x).getNetWorth(numOfLand)) {
  									 ranks.add(allPlayers.get(x));
  								 }
  							 }
  						 }

  						 //displays player standings, who won and who lost
  						 TextArea whoWon = new TextArea();
  						 whoWon.appendText("Who won?");
               whoWon.appendText("\n\nPlayer " + ranks.get(0).getPlayerNumber() + " wins first place!\nwith a networth of $" + ranks.get(0).getNetWorth(numOfLand));
               whoWon.appendText("\n\nPlayer " + ranks.get(1).getPlayerNumber() + " wins second place!\nwith a networth of $" + ranks.get(1).getNetWorth(numOfLand));
               if (ranks.size() == 3){
                 whoWon.appendText("\n\nPlayer " + ranks.get(2).getPlayerNumber() + " wins third place!\nwith a networth of $" + ranks.get(2).getNetWorth(numOfLand));
                }
               if (ranks.size() == 4){
                 whoWon.appendText("\n\nPlayer " + ranks.get(2).getPlayerNumber() + " wins third place!\nwith a networth of $" + ranks.get(2).getNetWorth(numOfLand));
                 whoWon.appendText("\n\nPlayer " + ranks.get(3).getPlayerNumber() + " wins fourth place!\nwith a networth of $" + ranks.get(3).getNetWorth(numOfLand));
                }

  						 endGame.getChildren().addAll(playerStats, whoWon);
  						 endGameBox.setScene(new Scene(endGame, 600, 500));
  						 endGameBox.show();
      				 }
      			 });

             //if player decides to keep playing
      			 doNotClose.setOnAction(new EventHandler<ActionEvent>(){
      				 @Override
      				 public void handle(ActionEvent event){
      					 exitChoice.close();
      				 }
      			 });

      			 exitBox.getChildren().addAll(close, doNotClose);
      			 Scene exitScene = new Scene(exitBox, 300, 200);
      			 exitChoice.setScene(exitScene);
      			 exitChoice.show();
      		   }
      	   });

      //Set up for the main menu
      GridPane start = new GridPane();
          final int rnumCols = 11;
          final int rnumRows = 7;
          for (int w = 0; w < rnumCols; w++) {
              ColumnConstraints colConst = new ColumnConstraints();
              colConst.setPercentWidth(100.0 / rnumCols);
              start.getColumnConstraints().add(colConst);
          }
          for (int w = 0; w < rnumRows; w++) {
              RowConstraints rowConst = new RowConstraints();
              rowConst.setPercentHeight(100.0 / rnumRows);
              start.getRowConstraints().add(rowConst);
          }

          // adding buttons and labels to start menu
          welcome.setFont(Font.font ("Verdana", 40));
          humanPlayers.setFont(Font.font ("Veranda", 20));
          AIPlayers.setFont(Font.font ("Veranda", 20));
          start.add(welcome, 3, 1, 6, 1);
          start.add(humanPlayers, 1, 2, 5, 1 );
          start.add(AIPlayers, 7, 2, 5, 1);
          start.add(errorMessage, 4, 6, 3, 1);
          start.add(player1, 1, 3, 1, 1);
          start.add(player2, 2, 3, 1, 1);
          start.add(player3, 3, 3, 1, 1);
          start.add(player4, 4, 3, 1, 1);
          start.add(computer0, 7, 3, 1, 1);
          computer0.setDisable(true);
          start.add(computer1, 8, 3, 1, 1);
          computer1.setDisable(true);
          start.add(computer2, 9, 3, 1, 1);
          computer2.setDisable(true);
          start.add(computer3, 10, 3, 1, 1);
          computer3.setDisable(true);
          start.add(begin, 5, 5, 2, 1);
          start.add(totalPlayersPlaying, 5, 4, 2, 1);

          //Event handlers for all buttons, sets the amount of players in game in accordance with buttons clicked
          player1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
              totalHumanPlayers = 1;
              totalPlayers = getTotalHumanPlayers() + getTotalComputerPlayers();
              totalPlayersPlaying.setText("Total players: " + totalPlayers);
              computer0.setDisable(true);
              computer1.setDisable(false);
              computer2.setDisable(false);
              computer3.setDisable(false);
            }
          });

          player2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
              totalHumanPlayers = 2;
              totalPlayers = getTotalHumanPlayers() + getTotalComputerPlayers();
              totalPlayersPlaying.setText("Total players: " + totalPlayers);
              computer0.setDisable(false);
              computer1.setDisable(false);
              computer2.setDisable(false);
              computer3.setDisable(true);
            }
          });

          player3.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
              totalHumanPlayers = 3;
              totalPlayers = getTotalHumanPlayers() + getTotalComputerPlayers();
              totalPlayersPlaying.setText("Total players: " + totalPlayers);
              computer0.setDisable(false);
              computer1.setDisable(false);
              computer2.setDisable(true);
              computer3.setDisable(true);
            }
          });

          player4.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
              totalHumanPlayers = 4;
              totalPlayers = getTotalHumanPlayers();
              totalPlayersPlaying.setText("Total players: " + totalPlayers);
              computer0.setDisable(false);
              computer1.setDisable(true);
              computer2.setDisable(true);
              computer3.setDisable(true);
            }
          });

          computer0.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
              totalComputerPlayers = 0;
              totalPlayers = getTotalHumanPlayers() + getTotalComputerPlayers();
              totalPlayersPlaying.setText("Total players: " + totalPlayers);
            }
          });

          computer1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
              totalComputerPlayers = 1;
              totalPlayers = getTotalHumanPlayers() + getTotalComputerPlayers();
              totalPlayersPlaying.setText("Total players: " + totalPlayers);
            }
          });

          computer2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
              totalComputerPlayers = 2;
              totalPlayers = getTotalHumanPlayers() + getTotalComputerPlayers();
              totalPlayersPlaying.setText("Total players: " + totalPlayers);
            }
          });

          computer3.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
              totalComputerPlayers = 3;
              totalPlayers = getTotalHumanPlayers() + getTotalComputerPlayers();
              totalPlayersPlaying.setText("Total players: " + totalPlayers);
            }
          });

          //After the amount of human and computer players has been selected, the begin button launches the main gameplay GUI to begin play
          begin.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
              if (totalPlayers >= 2 && totalPlayers <= 4){
  							for (int i = 0; i < totalHumanPlayers; i++){
  								allPlayers.add(new Player(i, true));
  							}

  							for (int i = 0; i < totalComputerPlayers; i++){
  								allPlayers.add(new ComputerAI(i, false));
  							}

  							for (int i = 0; i < allPlayers.size(); i++){
  								allPlayers.get(i).setPlayerNumber(i + 1);
  							}

                Scene gamePlay = new Scene(root, 1200, 600);
                primaryStage.setTitle("Mono-Poly");
                primaryStage.setScene(gamePlay);
                primaryStage.show();
              }
              else if (totalPlayers < 2 || totalPlayers > 4){
                errorMessage.setText("Choose between 1 - 4 Players");
              }
            }});

        Scene menu = new Scene(start, 1200, 600);
      	primaryStage.setTitle("Mono-Poly");
      	primaryStage.setScene(menu);
      	primaryStage.show();
      }
  }
}
