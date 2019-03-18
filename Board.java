
import java.util.ArrayList;

class Board {
    /**
    * Instance Variables: "spaces" of type ArrayList
    * Setters and Getters for instance variables
    */
	ArrayList<Space> boardArray;

	public Space getSpace(int space) {
		return boardArray.get(space);
	}

	public int getLength() {
		return boardArray.size();
	}

	public void setSpace(int number, Space space) {
		boardArray.set(number, space);
	}


	//A method to print out every space and the ownership of that space to make sure things are working
	public void returnBoard() {
		for (int i = 0; i < boardArray.size(); i++) {
			System.out.println(boardArray.get(i).getInfo());
		}
	}

  public Board() {
    //Basic Constructor that builds the board
		boardArray = new ArrayList<Space>();
		//Ownership: 0: unowned, Default, 1-2: Player 1 and 2, -1: GO
		//Special Spaces: 11: Chance, 12: Income Tax, 13: Free Parking, 14: Community Fund, 15: Go to Jail
        //Sets every space as a space object to add to the array of the board
		Space space0 = new Space("GO", 0, 0, -1, 0);

    Space space1 = new Space("Yellow 1", 150, 50, 0, 1); 			//33.3% income to cost
    Space space2 = new Space("Railroad 1", 200, 100, 0, 2);
    Space space3 = new Space("Yellow 2", 200, 75, 0, 3);			//37.5%
    Space space4 = new Space("Yellow 3", 250, 100, 0, 4);			//40.0%
    Space space5 = new Space("Chance", 0, 0, 11, 5);
    Space space6 = new Space("Jail", 0, 0, -1, 6);

		Space space7 = new Space("Blue 1", 350, 150, 0, 7);		//42.9%
		Space space8 = new Space("Blue 2", 400, 175, 0, 8);		//43.8%
		Space space9 = new Space("Railroad 2", 200, 100, 0, 9);
		Space space10 = new Space("Blue 3", 450, 200, 0, 10);		//44.4%
		Space space11 = new Space("Income Tax", 0, 250, 12, 11);
		Space space12 = new Space("Free Parking", 0, 0, 11, 12); //FOR NOW FREE PARKING IS JUST ANOTHER CHANCE

		Space space13 = new Space("Red 1", 550, 250, 0, 13);		//45.5%
		Space space14 = new Space("Railroad 3", 200, 100, 0, 14);
		Space space15 = new Space("Red 2", 600, 275, 0, 15);		//45.8%
		Space space16 = new Space("Red 3", 650, 300, 0, 16);		//46.2%
		Space space17 = new Space("Chance", 0, 0, 11, 17);
		Space space18 = new Space("Go to Jail", 0, 0, 15, 18);

		Space space19 = new Space("Orange 1", 750, 350, 0, 19);	//46.7%
		Space space20 = new Space("Orange 2", 800, 375, 0, 20);	//46.9%
		Space space21 = new Space("Community Fund", 0, 0, 14, 21);
		Space space22 = new Space("Green 1", 900, 425, 0, 22);		//47.2%
		Space space23 = new Space("Green 2", 1000, 500, 0, 23);	//50.0%  This one is intentionally skewed

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
