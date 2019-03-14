import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
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



public class Gui extends Application {
	private Player gameGui=new Player();
	private Board numOfLand=new Board();
	private Text informationOfBox = new Text();
	private GameMain turns=new GameMain();
	//private ChoiceBox choiceBox = new ChoiceBox();
	private Label text = new Label("Click square to get information");
	private Label Explanation=new Label("Explanation: ");
	private Label resultOfDice=new Label();
	private ComputerAI AI=new ComputerAI(2,false);
    private Label Pmoney=new Label("P1's Money: 1500");
    private Label Pproperty=new Label("P1's Property: 0");
    private Label Pturn=new Label("Turn processing at: ");
	private Label Pavatar=new Label("A");
	private Label Pcontrol=new Label("A");
    private Label AImoney=new Label("P2's Money 1500: ");
    private Label AIproperty=new Label("P2's Property: 0");
    private Label AIinformation = new Label("Display message: ");
    private Label Pposition = new Label("P1's Position: ");
    private Label AIposition = new Label ("P2's Position: ");
	private Label AIavatar=new Label("B");
	private Label AIcontrol=new Label("B");
    private Label AIinformation2 = new Label(" "); // info about if go was passed
    private Label AIinformation3 = new Label(" "); // info about what space was landed on
    private Label AIinformation4 = new Label(" "); // info about price/rent
    private Label AIinformation5 = new Label(" "); // info about if something was bought
    private Label AIinformation1 = new Label(" "); // info about dice rolled
    private Label AIinformation0 = new Label(" "); // info about whose turn it is
    //private Label Pinformation0 = new Label();
    //private Label Pinformation1 = new Label();
    private Label status = new Label();
    private int playerFlag = 1;
    private int rollUnlocked = 1;
    private int choiceUnlocked = 0;
	String basicText = "";


  public void setPlayerFlag(int playerFlag) {
    this.playerFlag = playerFlag;
  }

  public void setRollUnlocked(int rollUnlocked){
    this.rollUnlocked = rollUnlocked;
  }

  public void setChoiceUnlocked(int choiceUnlocked){
    this.choiceUnlocked = choiceUnlocked;
  }

  public int getPlayerFlag(){
    return playerFlag;
  }

  public int getRollUnlocked(){
    return rollUnlocked;
  }

  public int getChoiceUnlocked(){
    return choiceUnlocked;
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
    Explanation.setFont(Font.font ("Verdana", 16));
    AIinformation.setFont(Font.font ("Verdana", 16));
	Pavatar.setFont(Font.font ("Verdana", 25));
	AIavatar.setFont(Font.font ("Verdana", 25));
	Pcontrol.setFont(Font.font ("Verdana", 8));
	AIcontrol.setFont(Font.font ("Verdana", 8));
	
    root.add(Explanation,0,3,2,3);

    root.add(new Label(""), 4, 20);
    root.add(text,2,3,4,3);
    root.add(resultOfDice,8,3,10,3);
    root.add(Pmoney, 3, 4,4,4);
    root.add(Pposition, 3,5,4,4);
    root.add(Pproperty, 3,5,4,5);
    root.add(Pturn, 3,6,4,6);
    root.add(AImoney, 3,1,4,1);
    root.add(AIposition, 3,2,4,1);
    root.add(AIproperty, 3,2,4,2);
    root.add(AIinformation,6,6,7,6);
    root.add(AIinformation0,9,5,10,6);
    root.add(AIinformation1,9,6,10,5);
    root.add(AIinformation2,9,7,10,7);
    root.add(AIinformation3,9,8,10,8);
    root.add(AIinformation4,9,9,10,9);
    root.add(AIinformation5,9,10,10,10);
    //root.add(Pinformation0,10,5,11,5);
    //root.add(Pinformation1,8,4,9,4);
    root.add(status,8,1,10,1);










    //These are buttons

    //choiceBox
    Button positive=new Button("yes");
    positive.setMaxWidth(125);
    root.add(positive,8,3);


    Button negative = new Button("no");
    negative.setMaxWidth(125);
    root.add(negative,10,3);


    /*choiceBox.getItems().add("Player");
	choiceBox.getItems().add("Computer");
	root.add(choiceBox,10,3,11,3);*/

	//choiceBox.getValue().toString();

	Button roll = new Button("Roll\nDie");
	roll.setMaxWidth(125);
	roll.setMaxHeight(400);
    root.add(roll,9,3);

    roll.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent event){
        if(getRollUnlocked() == 1){
          AIinformation0.setText(" ");
          AIinformation1.setText(" ");
          AIinformation2.setText(" ");
          AIinformation3.setText(" ");
          AIinformation4.setText(" ");
          AIinformation5.setText(" ");
          setRollUnlocked(0);
          if(getPlayerFlag() == 1){
            turnCount += 1;
            setChoiceUnlocked(gameGui.takeTurnGui(numOfLand, AI, AIinformation0, AIinformation1, AIinformation2,
                    AIinformation3, AIinformation4, Pmoney, AImoney, choiceUnlocked));
            if(getChoiceUnlocked() == 0){
              setPlayerFlag(2);
              setRollUnlocked(1);
            }
            Pposition.setText("P1's Position: " + (numOfLand.getSpace(gameGui.getPosition() - 1)).getName());
          }
          else if(getPlayerFlag() == 2){
            setChoiceUnlocked(AI.takeTurnGui(numOfLand, gameGui, AIinformation0, AIinformation1, AIinformation2,
                    AIinformation3, AIinformation4, Pmoney, AImoney, choiceUnlocked));
            if(getChoiceUnlocked() == 0) {
              setPlayerFlag(1);
              setRollUnlocked(1);
            }
            AIposition.setText("P2's Position: " + (numOfLand.getSpace(AI.getPosition() - 1)).getName());
          }
          Pmoney.setText("P1's Money: " + gameGui.getMoney());
          AImoney.setText("P2's Money: " + AI.getMoney());
          AIproperty.setText("P2's Property: " + AI.getPropertiesOwned());
          Pproperty.setText("P1's Property: " + gameGui.getPropertiesOwned());
          Pturn.setText("Turn Number: " + turnCount);
        }
      }
    }
    );

    positive.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent event){
        if(getChoiceUnlocked() == 1) {
          if (getPlayerFlag() == 1) {
            gameGui.purchase(numOfLand, AIinformation5);
            Pmoney.setText("P1's Money: " + gameGui.getMoney());
            setPlayerFlag(2);
            Pproperty.setText("P1's Property: " + gameGui.getPropertiesOwned());
          }
          else if (getPlayerFlag() == 2) {
            AI.purchase(numOfLand, AIinformation5);
            AImoney.setText("P2's Money: " + AI.getMoney());
            setPlayerFlag(1);
            AIproperty.setText("P2's Property: " + AI.getPropertiesOwned());
          }
          setChoiceUnlocked(0);
          setRollUnlocked(1);
          }
        }
      }
    );

    negative.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent event){
        if(getChoiceUnlocked() == 1) {
          if (getPlayerFlag() == 1) {
            setPlayerFlag(2);
          }
          else if (getPlayerFlag() == 2) {
            setPlayerFlag(1);
          }
          setChoiceUnlocked(0);
          setRollUnlocked(1);
          }
        }
      }
    );
	
    Button bt01 = new Button("Jail");
    bt01.setMaxWidth(150);
	bt01.setMaxHeight(400);
    root.add(bt01,6,0);
    bt01.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Going to the jail");
        }
    }
  );
    Button bt02 = new Button();
    bt02.setMaxWidth(150);
	bt02.setMaxHeight(400);
	bt02.setStyle("-fx-background-color: #00F5FF");
	root.add(bt02,7,0);
	if (gameGui.getSpaceOwned(7) == true)
		root.add(Pcontrol,7,0);
	if (AI.getSpaceOwned(7) == true)
		root.add(AIcontrol,7,0);
    bt02.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
		  basicText = "Blue 1, Cost 350, Value 150";
		if (gameGui.getSpaceOwned(7) == true)
			text.setText(basicText + "\nOwned by Player 1");
		if (AI.getSpaceOwned(7) == true)
			text.setText(basicText + "\nOwned by Player 2");
		else
			text.setText(basicText + "\nOwned by Nobody");
        }
    }
  );

	
    Button bt03 = new Button();
    bt03.setMaxWidth(150);
	bt03.setMaxHeight(400);
	bt03.setStyle("-fx-background-color: #00E5EE");
    root.add(bt03,8,0);
    bt03.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        basicText = "Blue 2, Cost 400, Value 175";
		if (gameGui.getSpaceOwned(8) == true)
			text.setText(basicText + "\nOwned by Player 1");
		if (AI.getSpaceOwned(8) == true)
			text.setText(basicText + "\nOwned by Player 2");
		else
			text.setText(basicText + "\nOwned by Nobody");
        }
    }
  );
    Button bt04 = new Button("Rail\nRoad 2");
    bt04.setMaxWidth(150);
	bt04.setMaxHeight(400);
    root.add(bt04,9,0);
    bt04.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        basicText = "Railroad, Cost 200, Value 100";
		if (gameGui.getSpaceOwned(9) == true)
			text.setText(basicText + "\nOwned by Player 1");
		if (AI.getSpaceOwned(9) == true)
			text.setText(basicText + "\nOwned by Player 2");
		else
			text.setText(basicText + "\nOwned by Nobody");
        }
    }
  );
    Button bt05 = new Button();
    bt05.setMaxWidth(150);
	bt05.setMaxHeight(400);
	bt05.setStyle("-fx-background-color: #00F5FF");
    root.add(bt05,10,0);
    bt05.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        basicText = "Blue 3, Cost 450, Value 200";
			
		if (gameGui.getSpaceOwned(10) == true)
			text.setText(basicText + "\nOwned by Player 1");
		if (AI.getSpaceOwned(10) == true)
			text.setText(basicText + "\nOwned by Player 2");
		else
			text.setText(basicText + "\nOwned by Nobody");
        }
    }
  );
    Button bt06 = new Button("Tax");
    bt06.setMaxWidth(150);
	bt06.setMaxHeight(400);
    root.add(bt06,11,0);
    bt06.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Pay the bank a $100 tax");
        }
    }
  );
    Button bt07 = new Button("Free\nPark");
    bt07.setMaxWidth(150);
	bt07.setMaxHeight(400);
    root.add(bt07,12,0);
    bt07.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Receive a random amount of money");
        }
    }
  );
    //vertical
    Button bt11 = new Button("Chance");
    bt11.setMaxWidth(150);
	bt11.setMaxHeight(400);

    root.add(bt11,6,1);
    bt11.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Receive a random amount of money \nbetween -300 and 250");
        }
    }
  );
    Button bt21 = new Button();
    bt21.setMaxWidth(150);
	bt21.setMaxHeight(400);
	bt21.setStyle("-fx-background-color: #FFFF00");
    root.add(bt21,6,2);
    bt21.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        basicText = "Yellow 3, Cost 250, Value 100";
			
		if (gameGui.getSpaceOwned(4) == true)
			text.setText(basicText + "\nOwned by Player 1");
		if (AI.getSpaceOwned(4) == true)
			text.setText(basicText + "\nOwned by Player 2");
		else
			text.setText(basicText + "\nOwned by Nobody");
        }
    }
  );
    Button bt31 = new Button();
    bt31.setMaxWidth(150);
	bt31.setMaxHeight(400);
	bt31.setStyle("-fx-background-color: #EEEE00");
    root.add(bt31,6,3);
    bt31.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        basicText = "Yellow 2, Cost 200, Value 75";
		if (gameGui.getSpaceOwned(3) == true)
			text.setText(basicText + "\nOwned by Player 1");
		if (AI.getSpaceOwned(3) == true)
			text.setText(basicText + "\nOwned by Player 2");
		else
			text.setText(basicText + "\nOwned by Nobody");
        }
    }
  );
    Button bt41 = new Button("Rail\nRoad 1");
    bt41.setMaxWidth(150);
	bt41.setMaxHeight(400);

    root.add(bt41,6,4);
    bt41.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        text.setText("Railroad, Cost 200, Value 100");
		if (gameGui.getSpaceOwned(4) == true)
			text.setText(basicText + "\nOwned by Player 1");
		if (AI.getSpaceOwned(4) == true)
			text.setText(basicText + "\nOwned by Player 2");
		else
			text.setText(basicText + "\nOwned by Nobody");
        }
    }
  );
    Button bt51 = new Button();
    bt51.setMaxWidth(150);
	bt51.setMaxHeight(400);
    root.add(bt51,6,5);
    bt51.setStyle("-fx-background-color: #FFFF00");
    bt51.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        basicText = "Yellow 1, Cost 150, Value 50";
		if (gameGui.getSpaceOwned(1) == true)
			text.setText(basicText + "\nOwned by Player 1");
		if (AI.getSpaceOwned(1) == true)
			text.setText(basicText + "\nOwned by Player 2");
		else
			text.setText(basicText + "\nOwned by Nobody");
        }
    }
  );
    Button bt61 = new Button("Go");
    bt61.setMaxWidth(150);
	bt61.setMaxHeight(400);
    root.add(bt61,6,6);
    bt61.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Game starts from here");
        }
    }
  );
    //vertical

    Button bt17 = new Button();
    bt17.setMaxWidth(150);
	bt17.setMaxHeight(200);
	bt17.setStyle("-fx-background-color: #FF0000");
    root.add(bt17,12,1);
    bt17.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Red 1, Cost 550, Value 250");
        }
    }
  );

    Button bt27 = new Button();
    bt27.setMaxWidth(150);
	bt27.setMaxHeight(400);
	bt27.setStyle("-fx-background-color: #EE0000");
    root.add(bt27,12,2);
    bt27.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Red 2, Cost 600, Value 275");
        }
    }
  );
    Button bt37 = new Button("Rail\nRoad 3");
    bt37.setMaxWidth(150);
	bt37.setMaxHeight(400);
    root.add(bt37,12,3);

    bt37.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Railroad, Cost 200, Value 100");
        }
    }
  );
    Button bt47 = new Button();
    bt47.setMaxWidth(150);
	bt47.setMaxHeight(400);
	bt47.setStyle("-fx-background-color: #FF0000");

    root.add(bt47,12,4);
    bt47.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Red 3, Cost 650, Value 300");
        }
    }
  );
    Button bt57 = new Button("Chance");
    bt57.setMaxWidth(150);
	bt57.setMaxHeight(400);
    root.add(bt57,12,5);
    bt57.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Receive a random amount of money \nbetween -300 and 250");
        }
    }
  );

    Button bt67 = new Button("Go To\nJail");
    bt67.setMaxWidth(150);
	bt67.setMaxHeight(400);
    root.add(bt67,12,6);
    bt67.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Sends you to Jail");
        }
    }
  );
    //horizontal

    Button bt72 = new Button();
    bt72.setMaxWidth(150);
	bt72.setMaxHeight(400);
	bt72.setStyle("-fx-background-color: #7CFC00");
    root.add(bt72,7,6);
    bt72.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Green 2, Cost 1000, Value 500");
        }
    }
  );
    Button bt73 = new Button();
    bt73.setMaxWidth(150);
	bt73.setMaxHeight(400);
    root.add(bt73,8,6);
    bt73.setStyle("-fx-background-color: #00FA9A");
    bt73.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Green 1, Cost 900, Value 425");
        }
    }
  );
    Button bt74 = new Button("Comm\nFund");
    bt74.setMaxWidth(150);
	bt74.setMaxHeight(400);
    root.add(bt74,9,6);
    bt74.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Funding here");
        }
    }
  );
    Button bt75 = new Button();
    bt75.setMaxWidth(150);
	bt75.setMaxHeight(400);
	bt75.setStyle("-fx-background-color: #FFD700");
    root.add(bt75,10,6);
    bt75.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Orange 2, Cost 800, Value 375");
        }
    }
  );
    Button bt76 = new Button();
    bt76.setMaxWidth(150);
	bt76.setMaxHeight(400);
	bt76.setStyle("-fx-background-color: #EEC900");
    root.add(bt76,11,6);
    bt76.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {

        text.setText("Orange 1, Cost 750, Value 350");
        }
    }
  );


	//Exit button
	Button btClose = new Button("Exit");
	btClose.setMaxWidth(75);
	btClose.setMaxHeight(75);
	root.add(btClose,14,0);
	btClose.setOnAction(new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent event) {

			primaryStage.close();
		}
	}
	);

	
	//The part that displays the characters avatar
	int playerPos = gameGui.getPosition() - 1;
	int AIPos = AI.getPosition() - 1;
	if (playerPos <= 6) 
		root.add(Pavatar, 6, 6 - playerPos);
	else if (playerPos <= 12) 
		root.add(Pavatar, playerPos, 0);
	else if (playerPos <= 18)
		root.add(Pavatar, 12, playerPos - 12);
	else
		root.add(Pavatar, playerPos - 6, 6);

	//these are reference
	primaryStage.setTitle("Mono-Poly");
	primaryStage.setScene(new Scene(root, 1000, 600));
	primaryStage.show();

}

}
