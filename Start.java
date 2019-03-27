
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

// I want to set up a menu that gives you the option to select either PvP
// or player vs AI and possibly multiple (up tp 4) players

// I will need a welcome greting "Welcome to Monopoly"
// and then a prompt
// how many people are playing today?
// 1-4Players, min of at least 1 human player
// so maybe you can choose how many humans and how many computer

public class Start extends Gui {

  private int totalHumanPlayers = 0;
  private int totalComputerPlayers = 0;

  private Label welcome = new Label("Welcome to Monopoly!");
  private Label humanPlayers = new Label("How many human players?");
  private Label AIPlayers = new Label("How many computer players?");

  private Button player1 = new Button("1");
  private Button player2 = new Button("2");
  private Button player3 = new Button("3");
  private Button player4 = new Button("4");

  private Button computer0 = new Button("0");
  private Button computer1 = new Button("1");
  private Button computer2 = new Button("2");
  private Button computer3 = new Button("3");

  private Button begin = new Button("Start Game!");

  public int getTotalHumanPlayers(){
    return totalHumanPlayers;
  }

  public int getTotalComputerPlayers(){
    return totalComputerPlayers;
  }

  public static void main(String[] args) {
    Application.launch(args);
    }

	@Override
	public void start(Stage primaryStage) {
		GridPane start = new GridPane();
    		final int numCols = 11;
        final int numRows = 7;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            start.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            start.getRowConstraints().add(rowConst);
        }

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

        player1.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event){
            totalHumanPlayers = 1;
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
          }
        });

        computer1.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event){
            totalComputerPlayers = 1;
          }
        });

        computer2.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event){
            totalComputerPlayers = 2;
          }
        });

        computer3.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event){
            totalComputerPlayers = 3;
          }
        });

        begin.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event){

          }
        });

        primaryStage.setTitle("Mono-Poly");
        Scene menu = new Scene(start, 1000, 600);
      	primaryStage.setScene(menu);
      	primaryStage.show();
      }
    }
