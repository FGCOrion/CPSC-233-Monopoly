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
import javafx.scene.control.ChoiceBox ;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import java.util.ArrayList;

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
	ArrayList<Player> allPlayers = new ArrayList<Player>();

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
  private int rollUnlocked = 1;
  private int choiceUnlocked = 0;
	private int nextTurnUnlocked = 0;

	/**
	* Setup for labels in GUI that give information regarding each players total money, property,
	* whose turn it is and general information about spaces on the board and instance variables
	*/
	private Label Pavatar = new Label("A");
	private Label Pcontrol = new Label("A");
	private Label AIavatar = new Label("B");
	private Label AIcontrol = new Label("B");
	private Label status = new Label();
	private Label playerTurn = new Label("Player 1's turn!");
	private Label gameInfo = new Label("Game Information");
	private Label SpaceInfo = new Label("Space Info");
	private TextArea playerInfo = new TextArea("Information about each player\nwill be displayed here");
	private TextArea GameInfo = new TextArea("Information about each turn\nwill be displayed here");
	private TextArea spaceInfo = new TextArea("Click a space on the board for more information");
	private static GridPane root;
	private static Scene gamePlay;
	String basicText = "";
	int turnCount = 0;

  // Accessor Methods
  /**
  * returns the amount of human players
  * @return totalHumanPlayers
  */
  public int getTotalHumanPlayers(){
    return totalHumanPlayers;
  }

  /**
  * returns the amount of computer players
  * @return totalComputerPLayers
  */
  public int getTotalComputerPlayers(){
    return totalComputerPlayers;
  }

  /**
  * returns the amount of human and computer players
  * @return totalPlayers
  */
  public int getTotalPlayers(){
    return totalPlayers;
  }

  /**
  * sets the total of human players playing
  * @param newHumanPlayers
  */
  public void setTotalHumanPlayers(int newHumanPlayers){
    totalHumanPlayers = newHumanPlayers;
  }

  /**
  * sets the total of computer players playing
  * @param newComputerPlayers
  */
  public void setTotalComputerPlayers(int newComputerPlayers){
    totalComputerPlayers = newComputerPlayers;
  }

  /**
  * sets the total of all players in game, human and computer
  * @param newTotalPlayers
  */
  public void setTotalPlayers(int newTotalPlayers){
    totalPlayers = newTotalPlayers;
  }

  /**
	* sets the instance variable playerFlag which corresponds to either player 1 or player 2
	* @param playerFlag
	*/
  public void setPlayerFlag(int playerFlag) {
    this.playerFlag = playerFlag;
  }

	/**
	* sets the instance variable rollUnlocked
	* @param rollUnlocked
	*/
  public void setRollUnlocked(int rollUnlocked){
    this.rollUnlocked = rollUnlocked;
  }

	/**
	* sets the instance variable choiceUnlocked which allows the player to use "yes" and/or "no" button
	* @param choiceUnlocked
	*/
  public void setChoiceUnlocked(int choiceUnlocked){
    this.choiceUnlocked = choiceUnlocked;
  }

	/**
	* locks or unlocked the nextTurn button
	*/
	public void setNextTurnUnlocked(int turnUnlocked){
		this.nextTurnUnlocked = turnUnlocked;
	}

	/**
	* returns 1 if it is player 1's turn or 2 if it is player 2's turn
	* @return playerFlag
	*/
  public int getPlayerFlag(){
    return playerFlag;
  }

	/**
	* returns value of rollUnlocked
	* @return rollUnlocked
	*/
  public int getRollUnlocked(){
    return rollUnlocked;
  }

	/**
	* returns value of choiceUnlocked
	* @return choiceUnlocked
	*/
  public int getChoiceUnlocked(){
    return choiceUnlocked;
  }

	/**
	* returns value of nextTurnUnlocked
	*/
	public int getNextTurnUnlocked(){
		return nextTurnUnlocked;
	}

  public static void main(String[] args){
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage){

        GridPane root = new GridPane();
        		final int numCols = 15 ;
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

    		Pavatar.setFont(Font.font ("Verdana", 25));
    		AIavatar.setFont(Font.font ("Verdana", 25));
    		Pcontrol.setFont(Font.font ("Verdana", 8));
    		AIcontrol.setFont(Font.font ("Verdana", 8));
    		playerTurn.setTextFill(Color.BLUE);

    		root.add(playerInfo, 1, 1, 4, 4);
    		root.add(GameInfo, 1, 6, 4, 4);
    		root.add(playerTurn, 2, 0, 2, 1);
    		root.add(gameInfo, 2, 5, 2, 1);
    		root.add(spaceInfo, 7, 9, 5, 1);
    		root.add(SpaceInfo, 9, 8, 2, 1);

    		/**
    		* "Yes" button displayed beside "Roll Dice" button to affirm decision to purchase property
    		*/
        Button positive = new Button("Yes");
        positive.setMaxWidth(125);
        positive.setMaxHeight(400);
        root.add(positive, 8, 4);

    		/**
    		* "No" button displayed beside "Roll Dice" button to decline decision to purchase property
    		*/
        Button negative = new Button("No");
        negative.setMaxWidth(125);
        negative.setMaxHeight(600);
        root.add(negative, 10, 4);

    		/**
    		* next turn button to switch tur to other player
    		*/
    		Button nextTurn = new Button("Next\nTurn");
    		nextTurn.setMaxWidth(125);
    		nextTurn.setMaxHeight(400);
    		root.add(nextTurn, 9, 5);

    		/**
    		* "Roll Dice" button which initiates a die roll when clicked on the players turn
    		*/
    		Button roll = new Button("Roll\nDie");
    		roll.setMaxWidth(125);
    		roll.setMaxHeight(400);
    	   root.add(roll, 9, 4);

    		 /**
    		 * Sell property button to sell properties back to the bank for the cost originally paid
    		 */
    		 Button sell = new Button("Sell");
    		 sell.setMaxWidth(125);
    		 sell.setMaxHeight(400);
    		 root.add(sell, 9, 3);



    		 /**
     		* sell property
     		*/
    		sell.setOnAction(new EventHandler<ActionEvent>(){
    			@Override
    			public void handle(ActionEvent event){
    				final Stage sellProperties = new Stage();
    				 sellProperties.initModality(Modality.APPLICATION_MODAL);
    				 sellProperties.initOwner(primaryStage);
    				 VBox sellBox = new VBox(5);
    				 ArrayList<Integer> locations = new ArrayList<Integer>();

    				if (getPlayerFlag() == 1){
    					 ArrayList<Button> buttons = new ArrayList<Button>();
    					 for (int i = 0; i < 24; i++){
    						 if (allPlayers.get(0).getSpaceOwned(i) == true){
    							 buttons.add(new Button(numOfLand.getSpace(i).getName() + ": $" + numOfLand.getSpace(i).getCost()));
    							 locations.add(numOfLand.getSpace(i).getLocation());
    						 }
    					 }

    					 sellBox.getChildren().addAll(buttons);
    					 for (int i = 0; i < buttons.size(); i++){
    						 final Button myButton = buttons.get(i);
    						 final int location = locations.get(i);
    	           myButton.setOnAction(new EventHandler<ActionEvent>() {
              	 		public void handle(ActionEvent event) {
    									allPlayers.get(0).sell(numOfLand, location, GameInfo);
    									sellBox.getChildren().remove(myButton);
                      final Player currentPlayer = allPlayers.get(0);
                      currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
    									myButton.setDisable(true);
    					}
    				});
    			 }
    		 }

    				 if (getPlayerFlag() == 2){
    					 ArrayList<Button> buttons = new ArrayList<Button>();
    					 for (int i = 0; i < 24; i++){
    						 if (allPlayers.get(1).getSpaceOwned(i) == true){
    							 buttons.add(new Button(numOfLand.getSpace(i).getName() + ": $" + numOfLand.getSpace(i).getCost()));
    							 locations.add(numOfLand.getSpace(i).getLocation());
    						 }
    					 }

    					 sellBox.getChildren().addAll(buttons);
    					 for (int i = 0; i < buttons.size(); i++){
    						 final Button myButton = buttons.get(i);
    						 final int location = locations.get(i);
    	           myButton.setOnAction(new EventHandler<ActionEvent>() {
              	 		public void handle(ActionEvent event) {
    									allPlayers.get(1).sell(numOfLand, location, GameInfo);
    									sellBox.getChildren().remove(myButton);
                      final Player currentPlayer = allPlayers.get(1);
                      currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
    									myButton.setDisable(true);
    					}
    				});
    			 }
    		 }
				 if (totalPlayers >= 3){
				 if (getPlayerFlag() == 3){
						ArrayList<Button> buttons = new ArrayList<Button>();
						for (int i = 0; i < 24; i++){
							if (allPlayers.get(2).getSpaceOwned(i) == true){
								buttons.add(new Button(numOfLand.getSpace(i).getName() + ": $" + numOfLand.getSpace(i).getCost()));
								locations.add(numOfLand.getSpace(i).getLocation());
							}
						}

						sellBox.getChildren().addAll(buttons);
						for (int i = 0; i < buttons.size(); i++){
							final Button myButton = buttons.get(i);
							final int location = locations.get(i);
							myButton.setOnAction(new EventHandler<ActionEvent>() {
								 public void handle(ActionEvent event) {
									 allPlayers.get(2).sell(numOfLand, location, GameInfo);
									 sellBox.getChildren().remove(myButton);
                   final Player currentPlayer = allPlayers.get(2);
                   currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
									 myButton.setDisable(true);
					 }
				 });
				}
			}
		}
			if (totalPlayers >= 4){
			if (getPlayerFlag() == 4){
				 ArrayList<Button> buttons = new ArrayList<Button>();
				 for (int i = 0; i < 24; i++){
					 if (allPlayers.get(3).getSpaceOwned(i) == true){
						 buttons.add(new Button(numOfLand.getSpace(i).getName() + ": $" + numOfLand.getSpace(i).getCost()));
						 locations.add(numOfLand.getSpace(i).getLocation());
					 }
				 }

				 sellBox.getChildren().addAll(buttons);
				 for (int i = 0; i < buttons.size(); i++){
					 final Button myButton = buttons.get(i);
					 final int location = locations.get(i);
					 myButton.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent event) {
								allPlayers.get(3).sell(numOfLand, location, GameInfo);
								sellBox.getChildren().remove(myButton);
                final Player currentPlayer = allPlayers.get(3);
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
								myButton.setDisable(true);
				}
			});
		 }
	 }
 }
    					 sellBox.setAlignment(Pos.CENTER);
    					 Scene sellScene = new Scene(sellBox, 300, 200);
    					 sellProperties.setScene(sellScene);
    					 sellProperties.show();
    				 }
    			 });

    		/**
    		* Event Handler for roll button updates labels to represent players position, money, properties owned
    		* and total turns taken
    		*/
        roll.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event){
            if(getRollUnlocked() == 1){
              setRollUnlocked(0);
              if (getPlayerFlag() == 1){
								final Player currentPlayer = allPlayers.get(0);
                turnCount += 1;
                setChoiceUnlocked(allPlayers.get(0).takeTurnGui(numOfLand, currentPlayer, GameInfo, choiceUnlocked, allPlayers));
                if(getChoiceUnlocked() == 0){
    							setNextTurnUnlocked(1);
                }
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
              }
							else if (getPlayerFlag() == 2){
                if (allPlayers.get(1).getIsPlayer() == true){
  								final Player currentPlayer = allPlayers.get(1);
                  setChoiceUnlocked(allPlayers.get(1).takeTurnGui(numOfLand, currentPlayer, GameInfo, choiceUnlocked, allPlayers));
                  if (getChoiceUnlocked() == 0) {
      							setNextTurnUnlocked(1);
                  }
                  currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
              }
            }
							else if (getPlayerFlag() == 3){
                if (allPlayers.get(2).getIsPlayer() == true){
								final Player currentPlayer = allPlayers.get(2);
                setChoiceUnlocked(allPlayers.get(2).takeTurnGui(numOfLand, currentPlayer, GameInfo, choiceUnlocked, allPlayers));
                if (getChoiceUnlocked() == 0) {
    							setNextTurnUnlocked(1);
                }
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
              }
            }
							else if (getPlayerFlag() == 4){
                if (allPlayers.get(3).getIsPlayer() == true){
								final Player currentPlayer = allPlayers.get(3);
                setChoiceUnlocked(allPlayers.get(3).takeTurnGui(numOfLand, currentPlayer, GameInfo, choiceUnlocked, allPlayers));
                if (getChoiceUnlocked() == 0) {
    								setNextTurnUnlocked(1);
                }
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
              }
            }
          }
        }
      });

    		/**
    		* Event Handler for "Nex Turn" button after a players turn is done, the player must click the next turn button
    		* to change turn to other player this changes the player information to update to the current players information
    		* updates label to display whose turn it is
    		*/
    		nextTurn.setOnAction(new EventHandler<ActionEvent>(){
    			@Override
    			public void handle(ActionEvent event){
    				if (getNextTurnUnlocked() == 1){
    					if (getPlayerFlag() == 1){
    						playerTurn.setText("Player 2's Turn");
    						playerTurn.setTextFill(Color.RED);
                if (allPlayers.get(1).getIsPlayer() == true){
                  final Player currentPlayer = allPlayers.get(1);
      						setRollUnlocked(1);
      						setNextTurnUnlocked(0);
      						setPlayerFlag(2);
                  currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
    					}
               else if (allPlayers.get(1).getIsPlayer() == false){
                setRollUnlocked(0);
                final Player currentPlayer = allPlayers.get(1);
                allPlayers.get(1).takeTurnAI(numOfLand, currentPlayer, GameInfo, allPlayers);
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                setNextTurnUnlocked(1);
                setPlayerFlag(2);
              }
            }
    					else if (getPlayerFlag() == 2){
								if (totalPlayers > 2){
                  playerTurn.setText("Player 3's Turn");
      						playerTurn.setTextFill(Color.ORANGE);
                  if (allPlayers.get(2).getIsPlayer() == true){
                    final Player currentPlayer = allPlayers.get(2);
        						setRollUnlocked(1);
        						setNextTurnUnlocked(0);
      							setPlayerFlag(3);
                    currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                } else if (allPlayers.get(2).getIsPlayer() == false){
                  setRollUnlocked(0);
                  final Player currentPlayer = allPlayers.get(2);
                  allPlayers.get(2).takeTurnAI(numOfLand, currentPlayer, GameInfo, allPlayers);
                  currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                  setNextTurnUnlocked(1);
                  setPlayerFlag(3);
                }
								} else {
                  playerTurn.setText("Player 1's Turn");
      						playerTurn.setTextFill(Color.BLUE);
      						setRollUnlocked(1);
      						setNextTurnUnlocked(0);
									setPlayerFlag(1);
                  final Player currentPlayer = allPlayers.get(0);
                  currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
								}
    					}
							else if (getPlayerFlag() == 3){
								if (totalPlayers > 3){
                  playerTurn.setText("Player 4's Turn");
      						playerTurn.setTextFill(Color.PURPLE);
                  if (allPlayers.get(3).getIsPlayer() == true){
                  final Player currentPlayer = allPlayers.get(3);
      						setRollUnlocked(1);
      						setNextTurnUnlocked(0);
    							setPlayerFlag(4);
                  currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);

                } else if (allPlayers.get(3).getIsPlayer() == false){
                  setRollUnlocked(0);
                  final Player currentPlayer = allPlayers.get(3);
                  allPlayers.get(3).takeTurnAI(numOfLand, currentPlayer, GameInfo, allPlayers);
                  currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
                  setNextTurnUnlocked(1);
                  setPlayerFlag(4);
                }
							} else {
                playerTurn.setText("Player 1's Turn");
      					playerTurn.setTextFill(Color.BLUE);
      					setRollUnlocked(1);
      					setNextTurnUnlocked(0);
								setPlayerFlag(1);
                final Player currentPlayer = allPlayers.get(0);
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
								}

							} else if (getPlayerFlag() == 4){
    						playerTurn.setText("Player 1's Turn");
    						playerTurn.setTextFill(Color.BLUE);
    						setRollUnlocked(1);
    						setNextTurnUnlocked(0);
    						setPlayerFlag(1);
                final Player currentPlayer = allPlayers.get(0);
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
    				}
    			}
        }
    		});

    		/**
    		* Event Handler for positive ("Yes") button updates labels to reflect the property purchased by player
    		* and updates players money to reflect the purchase of said property which is (players money - the price of property purchased)
    		*/
        positive.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event){
            if(getChoiceUnlocked() == 1) {
              if (getPlayerFlag() == 1) {
                allPlayers.get(0).purchase(numOfLand, GameInfo);
                final Player currentPlayer = allPlayers.get(0);
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
              }
              else if (getPlayerFlag() == 2) {
                allPlayers.get(1).purchase(numOfLand, GameInfo);
                final Player currentPlayer = allPlayers.get(1);
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
              }
							else if (getPlayerFlag() == 3) {
                allPlayers.get(2).purchase(numOfLand, GameInfo);
                final Player currentPlayer = allPlayers.get(2);
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
              }
							else if (getPlayerFlag() == 4) {
                allPlayers.get(3).purchase(numOfLand, GameInfo);
                final Player currentPlayer = allPlayers.get(3);
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
              }
              setChoiceUnlocked(0);
              setNextTurnUnlocked(1);
              }
            }
          }
        );

    		/**
    		* Event Handler for negative ("No") button declines purchase/sale of property and proceeds to next players turn
    		*/
        negative.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event){
            if(getChoiceUnlocked() == 1) {
              setChoiceUnlocked(0);
    					setNextTurnUnlocked(1);
              }
            }
          }
        );


    		/**
    		* The remainder of this class is the setting up of buttons on the board to represent the spaces and action handlers
    		* to return information on each space and its ownership when the user clicks on that particular space
    		*/
        Button bt01 = new Button("Jail");
        bt01.setMaxWidth(150);
    	bt01.setMaxHeight(400);
        root.add(bt01,6,1);
        bt01.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            spaceInfo.setText("Sends the player to jail");
          }
        }
      );

        Button bt02 = new Button();
        bt02.setMaxWidth(150);
    		bt02.setMaxHeight(400);
    		bt02.setStyle("-fx-background-color: #00F5FF");
    		root.add(bt02,7,1);
    	  bt02.setOnAction(new EventHandler<ActionEvent>()
    	    {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(7);
    			  basicText = "Blue 1, Cost 350, Rent 150";
						newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt03 = new Button();
        bt03.setMaxWidth(150);
    		bt03.setMaxHeight(400);
    		bt03.setStyle("-fx-background-color: #00E5EE");
        root.add(bt03,8,1);
        bt03.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(8);
            basicText = "Blue 2, Cost 400, Rent 175";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt04 = new Button("Rail\nRoad 2");
        bt04.setMaxWidth(150);
    		bt04.setMaxHeight(400);
        root.add(bt04,9,1);
        bt04.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(9);
            basicText = "Railroad, Cost 200, Rent 100";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt05 = new Button();
        bt05.setMaxWidth(150);
    		bt05.setMaxHeight(400);
    		bt05.setStyle("-fx-background-color: #00F5FF");
        root.add(bt05,10,1);
        bt05.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(10);
            basicText = "Blue 3, Cost 450, Rent 200";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt06 = new Button("Tax");
        bt06.setMaxWidth(150);
    		bt06.setMaxHeight(400);
        root.add(bt06,11,1);
        bt06.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            spaceInfo.setText("Pay the bank a $100 tax");
          }
        }
      );

        Button bt07 = new Button("Free\nPark");
        bt07.setMaxWidth(150);
    		bt07.setMaxHeight(400);
        root.add(bt07,12,1);
        bt07.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {

            spaceInfo.setText("Receive a random amount of money");
          }
        }
      );

        //vertical
        Button bt11 = new Button("Chance");
        bt11.setMaxWidth(150);
    		bt11.setMaxHeight(400);
        root.add(bt11,6,2);
        bt11.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {

            spaceInfo.setText("Receive a random amount of money \nbetween -300 and 250");
          }
        }
      );

        Button bt21 = new Button();
        bt21.setMaxWidth(150);
    		bt21.setMaxHeight(400);
    		bt21.setStyle("-fx-background-color: #FFFF00");
        root.add(bt21,6,3);
        bt21.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(4);
            basicText = "Yellow 3, Cost 250, Rent 100";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt31 = new Button();
        bt31.setMaxWidth(150);
    		bt31.setMaxHeight(400);
    		bt31.setStyle("-fx-background-color: #EEEE00");
        root.add(bt31,6,4);
        bt31.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(3);
            basicText = "Yellow 2, Cost 200, Rent 75";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt41 = new Button("Rail\nRoad 1");
        bt41.setMaxWidth(150);
    		bt41.setMaxHeight(400);
        root.add(bt41,6,5);
        bt41.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(2);
            basicText = "Railroad, Cost 200, Rent 100";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt51 = new Button();
        bt51.setMaxWidth(150);
    		bt51.setMaxHeight(400);
        root.add(bt51,6,6);
        bt51.setStyle("-fx-background-color: #FFFF00");
        bt51.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(1);
            basicText = "Yellow 1, Cost 150, Rent 50";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt61 = new Button("Go");
        bt61.setMaxWidth(150);
    		bt61.setMaxHeight(400);
        root.add(bt61,6,7);
        bt61.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            spaceInfo.setText("Game starts from here");
          }
        }
      );

        //vertical
        Button bt17 = new Button();
        bt17.setMaxWidth(150);
    		bt17.setMaxHeight(200);
    		bt17.setStyle("-fx-background-color: #FF0000");
        root.add(bt17,12,2);
        bt17.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(13);
            basicText = "Red 1, Cost 550, Rent 250";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt27 = new Button();
        bt27.setMaxWidth(150);
    		bt27.setMaxHeight(400);
    		bt27.setStyle("-fx-background-color: #EE0000");
        root.add(bt27,12,3);
        bt27.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(15);
            basicText = "Red 2, Cost 600, Rent 275";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt37 = new Button("Rail\nRoad 3");
        bt37.setMaxWidth(150);
    		bt37.setMaxHeight(400);
        root.add(bt37,12,4);
        bt37.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(14);
            basicText = "Railroad, Cost 200, Rent 100";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);

          }
        }
      );

        Button bt47 = new Button();
        bt47.setMaxWidth(150);
    		bt47.setMaxHeight(400);
    		bt47.setStyle("-fx-background-color: #FF0000");
        root.add(bt47,12,5);
        bt47.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(16);
            basicText = "Red 3, Cost 650, Rent 300";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt57 = new Button("Chance");
        bt57.setMaxWidth(150);
    		bt57.setMaxHeight(400);
        root.add(bt57,12,6);
        bt57.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
          	spaceInfo.setText("Receive a random amount of money \nbetween -300 and 250");
          }
        }
      );

        Button bt67 = new Button("Go To\nJail");
        bt67.setMaxWidth(150);
    		bt67.setMaxHeight(400);
        root.add(bt67,12,7);
        bt67.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            spaceInfo.setText("Sends you to Jail");
          }
        }
      );
        //horizontal

        Button bt72 = new Button();
        bt72.setMaxWidth(150);
    		bt72.setMaxHeight(400);
    		bt72.setStyle("-fx-background-color: #7CFC00");
        root.add(bt72,7,7);
        bt72.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(23);
            basicText = "Green 2, Cost 1000, Rent 500";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt73 = new Button();
        bt73.setMaxWidth(150);
    		bt73.setMaxHeight(400);
        root.add(bt73,8,7);
        bt73.setStyle("-fx-background-color: #00FA9A");
        bt73.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(22);
    				basicText = "Green 1, Cost 900, Rent 425";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt74 = new Button("Comm\nFund");
        bt74.setMaxWidth(150);
    		bt74.setMaxHeight(400);
        root.add(bt74,9,7);
        bt74.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            spaceInfo.setText("Funding here");
          }
        }
      );

        Button bt75 = new Button();
        bt75.setMaxWidth(150);
    		bt75.setMaxHeight(400);
    		bt75.setStyle("-fx-background-color: #FFD700");
        root.add(bt75,10,7);
        bt75.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            basicText = "Orange 2, Cost 800, Rent 375";
            Space newSpace = numOfLand.getSpace(20);
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

        Button bt76 = new Button();
        bt76.setMaxWidth(150);
    		bt76.setMaxHeight(400);
    		bt76.setStyle("-fx-background-color: #EEC900");
        root.add(bt76,11,7);
        bt76.setOnAction(new EventHandler<ActionEvent>()
        {
          @Override
          public void handle(ActionEvent event) {
            Space newSpace = numOfLand.getSpace(19);
            basicText = "Orange 1, Cost 750, Rent 350";
            newSpace.setSpaceInfo(newSpace, spaceInfo, basicText);
          }
        }
      );

    	/**
    	* Exit button to prematurely end game without meeting end condition and pop up window to confirm
    	* if you'd like to quit or not
    	*/
    	Button btClose = new Button("Exit");
    	btClose.setMaxWidth(75);
    	btClose.setMaxHeight(75);
    	root.add(btClose,14,0);
    	btClose.setOnAction(new EventHandler<ActionEvent>()
    	{
    		@Override
    		public void handle(ActionEvent event) {
    			final Stage exitChoice = new Stage();
    			 exitChoice.initModality(Modality.APPLICATION_MODAL);
    			 exitChoice.initOwner(primaryStage);
    			 HBox exitBox = new HBox(20);
    			 Button close = new Button("Exit");
    			 Button doNotClose = new Button("Keep Playing");
    			 exitBox.setAlignment(Pos.CENTER);

    			 close.setOnAction(new EventHandler<ActionEvent>(){
    				 @Override
    				 public void handle(ActionEvent event){
    					 primaryStage.close();
    				 }
    			 });

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
    	}
    	);

      //Set up for menu screen (LAYOUT ONE)
      GridPane start = new GridPane();
          final int rnumCols = 11;
          final int rnumRows = 7;
          for (int i = 0; i < rnumCols; i++) {
              ColumnConstraints colConst = new ColumnConstraints();
              colConst.setPercentWidth(100.0 / rnumCols);
              start.getColumnConstraints().add(colConst);
          }
          for (int i = 0; i < rnumRows; i++) {
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

          //Event handlers for all buttons, set the amount of players in game
          //in accordance with buttons clicked
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
              totalPlayers = getTotalHumanPlayers() + getTotalComputerPlayers();
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

          begin.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
							for (int i = 0; i < totalHumanPlayers; i++){
								allPlayers.add(new Player(i, true));
							}

							for (int i = 0; i < totalComputerPlayers; i++){
								allPlayers.add(new ComputerAI(i, false));
							}

							for (int i = 0; i < allPlayers.size(); i++){
								allPlayers.get(i).setPlayerNumber(i + 1);
							}

              Scene gamePlay = new Scene(root, 1000, 600);
              primaryStage.setTitle("Mono-Poly");
              primaryStage.setScene(gamePlay);
              primaryStage.show();
            }
          });

        Scene menu = new Scene(start, 1000, 600);
      	primaryStage.setTitle("Mono-Poly");
      	primaryStage.setScene(menu);
      	primaryStage.show();
      }
  }
