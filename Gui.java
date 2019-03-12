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
	private ChoiceBox choiceBox = new ChoiceBox();
	private Label text = new Label("Click square to get information");
	private Label Explanation=new Label("Explanation: ");
	private Label resultOfDice=new Label();
	private ComputerAI AI=new ComputerAI();
    private Label Pmoney=new Label("Your Money:");
    private Label Pproperty=new Label("Your property:");
    private Label Pturn=new Label("Turn processing at: ");
    private Label AImoney=new Label("AI's Money: ");
    private Label AIproperty=new Label("AI's property: ");
    private Label AIinformation = new Label("Display message: ");
    private Label AIinformation1 = new Label();
    private Label AIinformation2 = new Label();
    private Label AIinformation3 = new Label();
    private Label AIinformation4 = new Label();
    private Label AIinformation0 = new Label();
    private Label Pinformation0 = new Label();
    private Label Pinformation1 = new Label();
    private Label status = new Label();


   

	public static void main(String[] args) {
		Application.launch(args);
		}
	

	@Override
	public void start(Stage primaryStage) {
		GridPane root = new GridPane();
    	final int numCols = 15 ;
        final int numRows = 10 ;
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
    root.add(Explanation,0,3,2,3);
	
    root.add(new Label(""), 4, 20);
    root.add(text,2,3,4,3);
    root.add(resultOfDice,8,3,10,3);
    root.add(Pmoney, 3, 4,4,4);
    root.add(Pproperty, 3, 5,4,5);
    root.add(Pturn, 3, 6,4,6);
    root.add(AImoney, 3,1,4,1);
    root.add(AIproperty, 3,2,4,2);
    root.add(AIinformation,6,6,7,6);
    root.add(AIinformation0,9,1,10,1);
    root.add(AIinformation1,9,6,10,6);
    root.add(AIinformation2,9,7,10,7);
    root.add(AIinformation3,9,8,10,8);
    root.add(AIinformation4,9,9,10,9);
    root.add(Pinformation0,10,5,11,5);
    root.add(Pinformation1,8,4,9,4);
    root.add(status,8,1,10,1);


    

  





    //These are buttons

    //choiceBox
    Button positive=new Button("yes");
   
    root.add(positive,7,3);


    Button negative = new Button("no");
   
    root.add(negative,10,3);
    

    /*choiceBox.getItems().add("Player");
	choiceBox.getItems().add("Computer");
	root.add(choiceBox,10,3,11,3);*/

	//choiceBox.getValue().toString();

	Button roll = new Button("Dice");
	roll.setMaxWidth(150);
	roll.setMaxHeight(400);
    root.add(roll,9,3);

    roll.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        
        
        turns.playerrun();
      	resultOfDice.setText("You rolled :"+turns.player.getNumbersOfDice());
      	Pmoney.setText(("Your Money: "+turns.player.getMoney()));
		Pproperty.setText(("Your property: "+numOfLand.getNumberOfLand()));
		Pturn.setText(("Turn processing at: "+turns.getTurns()));
        Pinformation0.setText(turns.player.getInformation0());
        text.setText("Do you interest in this Land?");
        

    
       positive.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        
      
       // turns.setYes(1);
        text.setText(turns.player.getInformation1());
        turns.AIrun();
        status.setText(turns.getStatus());
        AImoney.setText(("AI's Money: "+turns.computer.getMoney()));
        AIproperty.setText(("AI's property: "+turns.computer.getNewProperty()));
        AIinformation0.setText(turns.computer.getInformation0());
        AIinformation1.setText(turns.computer.getInformation1());
        AIinformation2.setText(turns.computer.getInformation2());
        AIinformation3.setText(turns.computer.getInformation3());
        AIinformation4.setText(turns.computer.getInformation4());
        turns.playerBuy();
        }
    }
  );
        negative.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
       text.setText(turns.player.getInformation1());
       
        //turns.setYes(0);

        turns.AIrun();
        status.setText(turns.getStatus());
        AImoney.setText(("AI's Money: "+turns.computer.getMoney()));
        AIproperty.setText(("AI's property: "+turns.computer.getNewProperty()));
        AIinformation0.setText(turns.computer.getInformation0());
        AIinformation1.setText(turns.computer.getInformation1());
        AIinformation2.setText(turns.computer.getInformation2());
        AIinformation3.setText(turns.computer.getInformation3());
        AIinformation4.setText(turns.computer.getInformation4());
        }
    }
  );   
    
    
       
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
    bt02.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        
        text.setText("Blue 1");
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
        
        text.setText("Blue 2");
        }
    }
  );
    Button bt04 = new Button("Rail");
    bt04.setMaxWidth(150);
	bt04.setMaxHeight(400);
    root.add(bt04,9,0);
    bt04.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        
        text.setText("This is Rail");
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
        
        text.setText("Blue 3");
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
        
        text.setText("Pay the tax");
        }
    }
  );
    Button bt07 = new Button("Parking");
    bt07.setMaxWidth(150);
	bt07.setMaxHeight(400);
    root.add(bt07,12,0);
    bt07.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        
        text.setText("You can park here for free");
        }
    }
  );
    //vertical
    Button bt11 = new Button("Card");
    bt11.setMaxWidth(150);
	bt11.setMaxHeight(400);

    root.add(bt11,6,1);
    bt11.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        
        text.setText("Draw a card");
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
        
        text.setText("Yellow3");
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
        
        text.setText("Yellow2");
        }
    }
  );
    Button bt41 = new Button("Rail");
    bt41.setMaxWidth(150);
	bt41.setMaxHeight(400);

    root.add(bt41,6,4);
    bt41.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        
        text.setText("This is the rail");
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
        
        text.setText("Yellow 1");
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
        
        text.setText("Red 1");
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
        
        text.setText("Red 2");
        }
    }
  );
    Button bt37 = new Button("Rail");
    bt37.setMaxWidth(150);
	bt37.setMaxHeight(400);
    root.add(bt37,12,3);

    bt37.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        
        text.setText("This is the rail");
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
        
        text.setText("Red 3");
        }
    }
  );
    Button bt57 = new Button("Card");
    bt57.setMaxWidth(150);
	bt57.setMaxHeight(400);
    root.add(bt57,12,5);
    bt57.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        
        text.setText("Draw a card");
        }
    }
  );
    
    Button bt67 = new Button("Jail");
    bt67.setMaxWidth(150);
	bt67.setMaxHeight(400);
    root.add(bt67,12,6);
    bt67.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event) {
        
        text.setText("Going to the jail");
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
        
        text.setText("Green 2");
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
        
        text.setText("Green 1");
        }
    }
  );
    Button bt74 = new Button("Fund");
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
        
        text.setText("Orange 2");
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
        
        text.setText("Orange 1");
        }
    }
  );
  
  
  
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
	
	








	//these are reference
	
	
	primaryStage.setTitle("Mono-Poly");
	primaryStage.setScene(new Scene(root, 1000, 600)); 
	primaryStage.show();

}

}
