import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.application.Application;

public class Save {

    /**
     * Method that creates or overwrites a file with a given filename, setting the text in the file to be textToWrite
     * @param filename
     * @param textToWrite
     * @throws IOException
     */
    public static void saveToTextFile(String filename, String textToWrite) throws IOException {
        PrintWriter out = new PrintWriter(filename);
        out.println(textToWrite);
        out.close();
    }

    /**
     * Saves the data of a the game, so it may be loaded upon re-opening the program. It collects and writes out the
     * data to save, then uses the saveToTextFile method to save it to the specified file.
     * @param filename
     * @param playerArrayList
     * @param board
     * @param turnCount
     * @param playerFlag
     */
    public static void gameSave(String filename, ArrayList<Player> playerArrayList, Board board, int turnCount,
                                int playerFlag){
        /*
        Setting up variables for use in the rest of the method
         */
        int totalPlayers = playerArrayList.size();
        int totalHumanPlayers = 0; int totalComputerPlayers = 0;
        String playerInfo = "";
        /*
        For loop that saves the data of each player on its own line. Saves the player's money, position, inJail, and
        Eliminated values.
         */
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
        /*
        Creates the first line of the save file, which saves the total number of players, number of humans, number
        of cpus, turn number, and whose turn it is.
         */
        String playerNumberInfo = totalPlayers + " " + totalHumanPlayers + " " + totalComputerPlayers + " " +
                turnCount + " " + playerFlag;
        /*
        For loop which gets who owns each space on the board in order, and saves them to the same line separated by
        a space.
         */
        String spaceInfo = "\n";
        for(int j = 0; j < 24; j++){
            int spaceOwned = board.getSpace(j).getOwner();
            spaceInfo = spaceInfo + spaceOwned + " ";
        }
        /*
        Combines all the lines from above to create the full text that needs to be written to the save file
         */
        String textToWrite = playerNumberInfo + playerInfo + spaceInfo;
        /*
        try-catch block to deal with a potential IOException, which does nothing when caught. The code in the try block
        calls the saveToTextFile method to write the save data
         */
        try {
            saveToTextFile(filename, textToWrite);
        }
        catch (IOException e){

        }
    }
}