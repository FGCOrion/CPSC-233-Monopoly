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
		
		Space space1 = new Space("Yellow 1", 150, 50); 		//33.3% income to cost
		Space space2 = new Space("Railroad", 200, 100); 	
		Space space3 = new Space("Yellow 2", 200, 75);		//37.5%
		Space space4 = new Space("Yellow 3", 250, 100);		//40.0%
		Space space5 = new Space("Pick up a Card", 0, 0);  	
		Space space6 = new Space("Jail", 0, 0);

		Space space7 = new Space("Blue 1", 300, 125);		//41.7%
		Space space8 = new Space("Blue 2", 350, 150);		//43.8%
		Space space9 = new Space("Rail Road", 200, 100);
		Space space10 = new Space("Blue 3", 400, 175);		//44.4%
		Space space11 = new Space("Income Tax", 0, 250);	
		Space space12 = new Space("Free Parking", 0, 0);
		
		Space space13 = new Space("Red 1", 450, 200);		//45.0% 
		Space space14 = new Space("Rail Road", 200, 100);	
		Space space15 = new Space("Red 2", 500, 225);		//45.5%
		Space space16 = new Space("Red 3", 550, 250);		//46.2% 
		Space space17 = new Space("Pick up a Card", 0, 0);	
		Space space18 = new Space("Go to Jail", 0, 0);
	    	Space space19 = new Space("Orange 1", 600, 275);	//46.4%
		Space space20 = new Space("Orange 2", 650, 300);	//46.7%
	    	Space space21 = new Space("Community Fund", 0, 0);
	    	Space space22 = new Space("Green 1", 700, 325);		//46.9%
	    	Space space23 = new Space("Green 2", 750, 350);

		
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
	    	boardArray.add(space19);
	    	boardArray.add(space20);
	    	boardArray.add(space21);
	    	boardArray.add(space22);
	    	boardArray.add(space23);	    
		//This is ugly AF
		
		
    }

}
