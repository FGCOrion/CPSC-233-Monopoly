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
		Space space0 = new Space("GO", 0, 0);
		
        Space space1 = new Space("Space 1", 150, 50); 		//33.3% income to cost
        Space space2 = new Space("Space 2", 200, 75); 		//37.5%
        Space space3 = new Space("?", 0, 0);
        Space space4 = new Space("Space 4", 250, 100);		//40.0%
        Space space5 = new Space("Space 5", 300, 125);  	//41.7%
        Space space6 = new Space("Jail", 0, 0);
		
		Space space7 = new Space("Space 7", 400, 175);		//43.8%
		Space space8 = new Space("Space 8", 450, 200);		//44.4%
		Space space9 = new Space("?", 0, 0);
		Space space10 = new Space("Space 10", 500, 225);	//45.0%
		Space space11 = new Space("Space 11", 550, 250);	//45.5%
		Space space12 = new Space("Free Parking", 0, 0);
		
		Space space13 = new Space("Space 13", 650, 300);	//46.2%
		Space space14 = new Space("Space 14", 700, 325);	//46.4%
		Space space15 = new Space("?", 0, 0);
		Space space16 = new Space("Space 16", 750, 350);	//46.7%
		Space space17 = new Space("Space 17", 800, 375);	//46.9%
		Space space18 = new Space("Go to Jail", 0, 0);
		
		boardArray.add(space0);
        boardArray.add(space1);
        boardArray.add(space2);
        boardArray.add(space3);
        boardArray.add(space4);
        boardArray.add(space5);
        boardArray.add(space6);
		boardArray.add(space7);
		boardArray.add(space8);
		boardArray.add(space9);
		boardArray.add(space10);
		boardArray.add(space11);
		boardArray.add(space12);
		boardArray.add(space13);
		boardArray.add(space14);
		boardArray.add(space15);
		boardArray.add(space16);
		boardArray.add(space17);
		boardArray.add(space18);
		//This is ugly AF
		
		
    }

}
