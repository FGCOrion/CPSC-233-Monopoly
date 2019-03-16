
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
import javafx.stage.Modality;


public class Gui extends Application {

	/**
	* setup for two human players and board set up
	*/
	private Player gameGui = new Player(1);
	private ComputerAI AI = new ComputerAI(2,false);
	private Board numOfLand = new Board();
	private GameMain turns = new GameMain();
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
	String basicText = "";

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


  public static void main(String[] args) {
		Application.launch(args);
		}

  int turnCount = 0;

	@Override
	public void start(Stage primaryStage) {
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
		* "Yes" button displayed beside "Roll Dice" button to affirm decision to purchase or sell property
		*/
    Button positive = new Button("yes");
    positive.setMaxWidth(125);
    root.add(positive,8,4);

		/**
		* "No" button displayed beside "Roll Dice" button to decline decision to purchase or sell property
		*/
    Button negative = new Button("no");
    negative.setMaxWidth(125);
    root.add(negative,10,4);

		/**
		* next turn button
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
	   root.add(roll,9,4);

		/**
		* Event Handler for roll button updates labels to represent players position, money, properties owned
		* and total turns taken
		*/
    roll.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent event){
        if(getRollUnlocked() == 1){
          setRollUnlocked(0);
          if(getPlayerFlag() == 1){
            turnCount += 1;
            setChoiceUnlocked(gameGui.takeTurnGui(numOfLand, AI, GameInfo, choiceUnlocked));
            if(getChoiceUnlocked() == 0){
							setNextTurnUnlocked(1);
            }
            playerInfo.setText("Current position: " + (numOfLand.getSpace(gameGui.getPosition())).getName());
						playerInfo.appendText("\nMoney: " + gameGui.getMoney());
	          playerInfo.appendText("\nProperties owned: " + gameGui.getPropertiesOwned());
	          playerInfo.appendText("\nCurrent Turn: " + turnCount);
          }
          else if(getPlayerFlag() == 2){
            setChoiceUnlocked(AI.takeTurnGui(numOfLand, AI, GameInfo, choiceUnlocked));
            if (getChoiceUnlocked() == 0) {
							setNextTurnUnlocked(1);
            }
            playerInfo.setText("Current position: " + (numOfLand.getSpace(AI.getPosition())).getName());
						playerInfo.appendText("\nMoney: " + AI.getMoney());
	          playerInfo.appendText("\nProperties owned: " + AI.getPropertiesOwned());
	          playerInfo.appendText("\nCurrent Turn: " + turnCount);
          }
        }
      }
    }
    );

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
						setRollUnlocked(1);
						setNextTurnUnlocked(0);
						setPlayerFlag(2);
						playerInfo.setText("Current position: " + (numOfLand.getSpace(AI.getPosition())).getName());
						playerInfo.appendText("\nMoney: " + AI.getMoney());
	          playerInfo.appendText("\nProperties owned: " + AI.getPropertiesOwned());
	          playerInfo.appendText("\nCurrent Turn: " + turnCount);
					}
					else if (getPlayerFlag() == 2){
						playerTurn.setText("Player 1's Turn");
						playerTurn.setTextFill(Color.BLUE);
						setRollUnlocked(1);
						setNextTurnUnlocked(0);
						setPlayerFlag(1);
						playerInfo.setText("Current position: " + (numOfLand.getSpace(gameGui.getPosition())).getName());
						playerInfo.appendText("\nMoney: " + gameGui.getMoney());
	          playerInfo.appendText("\nProperties owned: " + gameGui.getPropertiesOwned());
	          playerInfo.appendText("\nCurrent Turn: " + turnCount);
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
            gameGui.purchase(numOfLand, GameInfo);
						playerInfo.setText("Current position: " + (numOfLand.getSpace(gameGui.getPosition())).getName());
            playerInfo.appendText("\nMoney: " + gameGui.getMoney());
						playerInfo.appendText("\nProperties owned: " + gameGui.getPropertiesOwned());
						playerInfo.appendText("\nCurrent Turn: " + turnCount);
          }
          else if (getPlayerFlag() == 2) {
            AI.purchase(numOfLand, GameInfo);
						playerInfo.setText("Current position: " + (numOfLand.getSpace(AI.getPosition())).getName());
            playerInfo.appendText("\nMoney: " + AI.getMoney());
						playerInfo.appendText("\nProperties owned: " + AI.getPropertiesOwned());
						playerInfo.appendText("\nCurrent Turn: " + turnCount);
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
		if (gameGui.getSpaceOwned(7) == true)
			root.add(Pcontrol,7,0);
		if (AI.getSpaceOwned(7) == true)
			root.add(AIcontrol,7,0);
	    bt02.setOnAction(new EventHandler<ActionEvent>()
	    {
      @Override
      public void handle(ActionEvent event) {
			  basicText = "Blue 1, Cost 350, Rent 150";
				if (gameGui.getSpaceOwned(7) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(7) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Blue 2, Cost 400, Rent 175";
				if (gameGui.getSpaceOwned(8) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(8) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Railroad, Cost 200, Rent 100";
				if (gameGui.getSpaceOwned(9) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(9) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Blue 3, Cost 450, Rent 200";
				if (gameGui.getSpaceOwned(10) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(10) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Yellow 3, Cost 250, Rent 100";
				if (gameGui.getSpaceOwned(4) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(4) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Yellow 2, Cost 200, Rent 75";
				if (gameGui.getSpaceOwned(3) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(3) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Railroad, Cost 200, Rent 100";
				if (gameGui.getSpaceOwned(2) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(2) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Yellow 1, Cost 150, Rent 50";
				if (gameGui.getSpaceOwned(1) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(1) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Red 1, Cost 550, Rent 250";
				if (gameGui.getSpaceOwned(13) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(13) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Red 2, Cost 600, Rent 275";
				if (gameGui.getSpaceOwned(15) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(15) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Railroad, Cost 200, Rent 100";
				if (gameGui.getSpaceOwned(14) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(14) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Red 3, Cost 650, Rent 300";
				if (gameGui.getSpaceOwned(16) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(16) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Green 2, Cost 1000, Rent 500";
				if (gameGui.getSpaceOwned(23) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(23) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
				basicText = "Green 1, Cost 900, Rent 425";
				if (gameGui.getSpaceOwned(22) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(22) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
				if (gameGui.getSpaceOwned(20) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(20) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
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
        basicText = "Orange 1, Cost 750, Rent 350";
				if (gameGui.getSpaceOwned(19) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 1");
				else if (AI.getSpaceOwned(19) == true)
					spaceInfo.setText(basicText + "\nOwned by Player 2");
				else
					spaceInfo.setText(basicText + "\nOwned by Nobody");
      }
    }
  );

	/**
	* Exit/quit button to prematurely end game without meeting end condition and pop up window to confirm
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

	Scene gamePlay = new Scene(root, 1000, 600);
	primaryStage.setTitle("Mono-Poly");
	primaryStage.setScene(gamePlay);
	primaryStage.show();

}
}
