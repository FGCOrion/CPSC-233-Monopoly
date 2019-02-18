import java.util.ArrayList;

class Board {
    /**
    * Instance Variables: "spaces" of type ArrayList
    * Setters and Getters for instance variables
    */
    
    public Board() {
    //Basic Constructor that builds the board
        ArrayList<Space> boardArray = new ArrayList<Space>();
    
        //Sets every space as a space object to add to the array of the board
        Space space1 = new Space("Space 1", 150, 50);
        Space space2 = new Space("Space 2", 200, 75);
        Space space3 = new Space("?", 0, 0);
        Space space4 = new Space("Space 4", 250, 100);
        Space space5 = new Space("Space 5", 300, 125);
        Space space6 = new Space("Jail", 350, 150);
        
        boardArray.add(space1);
        boardArray.add(space2);
        boardArray.add(space3);
        boardArray.add(space4);
        boardArray.add(space5);
        boardArray.add(space6);
    }

}
