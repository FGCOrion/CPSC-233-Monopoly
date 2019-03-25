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
  private int forceSale = 0;

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
	private static GridPane root;
	private static Scene gamePlay;
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
  * returns the amount of computer players
  * @return totalComputerPLayers
  */
  public int getTotalComputerPlayers(){
    return totalComputerPlayers;
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

  public void setForceSale(int forceSale){
    this.forceSale = forceSale;
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

  public int getForceSale(){
    return forceSale;
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
        negative.setMaxHeight(400);
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
                      if (allPlayers.get(0).getMoney() > 0){
                        setNextTurnUnlocked(1);
                      }
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
                      if (allPlayers.get(1).getMoney() > 0){
                        setNextTurnUnlocked(1);
                }
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
                   if (allPlayers.get(2).getMoney() > 0){
                     setNextTurnUnlocked(1);
                   }
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
                if (allPlayers.get(3).getMoney() > 0){
                  setNextTurnUnlocked(1);
                }
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
                setChoiceUnlocked(allPlayers.get(0).takeTurnGui(numOfLand, currentPlayer, GameInfo, choiceUnlocked, allPlayers, forceSale));
                if (getChoiceUnlocked() == 0 && getForceSale() == 0){
    							setNextTurnUnlocked(1);
                }
                else if (getForceSale() == 1){
                  setNextTurnUnlocked(0);
                }
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
              }
							else if (getPlayerFlag() == 2){
                if (allPlayers.get(1).getIsPlayer() == true){
  								final Player currentPlayer = allPlayers.get(1);
                  setChoiceUnlocked(allPlayers.get(1).takeTurnGui(numOfLand, currentPlayer, GameInfo, choiceUnlocked, allPlayers, forceSale));
                  if (getChoiceUnlocked() == 0 && getForceSale() == 0){
      							setNextTurnUnlocked(1);
                  }
                  else if (getForceSale() == 1){
                    setNextTurnUnlocked(0);
                  }
                  currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
              }
            }
							else if (getPlayerFlag() == 3){
                if (allPlayers.get(2).getIsPlayer() == true){
								final Player currentPlayer = allPlayers.get(2);
                setChoiceUnlocked(allPlayers.get(2).takeTurnGui(numOfLand, currentPlayer, GameInfo, choiceUnlocked, allPlayers, forceSale));
                if (getChoiceUnlocked() == 0 && getForceSale() == 0){
    							setNextTurnUnlocked(1);
                }
                else if (getForceSale() == 1){
                  setNextTurnUnlocked(0);
                }
                currentPlayer.updatePlayerInfo(playerInfo, currentPlayer, numOfLand, turnCount);
              }
            }
							else if (getPlayerFlag() == 4){
                if (allPlayers.get(3).getIsPlayer() == true){
								final Player currentPlayer = allPlayers.get(3);
                setChoiceUnlocked(allPlayers.get(3).takeTurnGui(numOfLand, currentPlayer, GameInfo, choiceUnlocked, allPlayers, forceSale));
                if (getChoiceUnlocked() == 0 && getForceSale() == 0){
    								setNextTurnUnlocked(1);
                }
                else if (getForceSale() == 1){
                  setNextTurnUnlocked(0);
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
          });

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
          });

    		/**
    		* Set up of buttons for the main gameboard
    		*/
        ArrayList<Button> boardButtons = new ArrayList<Button>();
        int space = 1;
        int leftspace = 12;

        for (int s = 0; s < 24; s++){
          boardButtons.add(new Button());
        }

        for (int i = 0; i < boardButtons.size(); i++){
          boardButtons.get(i).setMaxWidth(150);
          boardButtons.get(i).setMaxHeight(400);

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

        //Set up of event handlers to eturn information about each spaces when clicked/selected
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
        //Sets the colours of buyables spaces to match their names
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
    	Button btClose = new Button("Exit");
    	btClose.setMaxWidth(75);
    	btClose.setMaxHeight(75);
    	root.add(btClose,14,0);
    	btClose.setOnAction(new EventHandler<ActionEvent>(){
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
    	   });

      //Set up for menu screen (LAYOUT ONE)
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

          //Event handlers for all buttons, set the amount of players in game in accordance with buttons clicked
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

                Scene gamePlay = new Scene(root, 1000, 600);
                primaryStage.setTitle("Mono-Poly");
                primaryStage.setScene(gamePlay);
                primaryStage.show();
              }
              else if (totalPlayers < 2 || totalPlayers > 4){
                errorMessage.setText("Choose between 1 - 4 Players");
              }
            }
          });

        Scene menu = new Scene(start, 1000, 600);
      	primaryStage.setTitle("Mono-Poly");
      	primaryStage.setScene(menu);
      	primaryStage.show();
      }
  }
}
