import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.application.Application;

public class Save {

    public static void saveToTextFile(String filename, String textToWrite) throws IOException {
        PrintWriter out = new PrintWriter(filename);
        out.println(textToWrite);
        out.close();
    }

    public static void gameSave(String filename, ArrayList<Player> playerArrayList, Board board, int turnCount,
                                int playerFlag){
        int totalPlayers = playerArrayList.size();
        int totalHumanPlayers = 0; int totalComputerPlayers = 0;
        String playerInfo = "";
        for(int i = 0; i < totalPlayers; i++){
            if(playerArrayList.get(i).getIsPlayer() == true){
                totalHumanPlayers += 1;
            }
            else{
                totalComputerPlayers += 1;
            }
            int tempMoney = playerArrayList.get(i).getMoney();
            int tempPos = playerArrayList.get(i).getPosition();
            boolean inJail = playerArrayList.get(i).getInJail();
            boolean eliminated = playerArrayList.get(i).getEliminated();
            playerInfo = playerInfo + "\n" + tempMoney + " " + tempPos + " " + inJail + " " + eliminated;
        }
        String playerNumberInfo = totalPlayers + " " + totalHumanPlayers + " " + totalComputerPlayers + " " +
                turnCount + " " + playerFlag;
        String spaceInfo = "\n";
        for(int j = 0; j < 24; j++){
            int spaceOwned = board.getSpace(j).getOwner();
            spaceInfo = spaceInfo + spaceOwned + " ";
        }
        String textToWrite = playerNumberInfo + playerInfo + spaceInfo;
        try {
            saveToTextFile(filename, textToWrite);
        }
        catch (IOException e){

        }
    }
}