import java.util.ArrayList;

class Board {

	//Instance Variables
	private ArrayList<Space> boardArray = new ArrayList<Space>();
	private boolean allSpacesOwned = false;


	//Accessor Methods

	/**
	* retuns the size of the board
	* @return boardArray.size()
	*/
	public int getLength() {
		return boardArray.size();
	}

	/**
	* retuns true if all spaces are owned
	* returns false otherwise
	* @return allSpacesOwned
	*/
	public boolean getAllSpacesOwned(){
		return allSpacesOwned;
	}

	/**
	* sets a space at the specified location
	* @param location, space
	*/
	public void setSpace(int location, Space space) {
		boardArray.set(location, space);
	}

	/**
	* sets allSpacesOwned to true or false
	* @param allSpacesOwned
	*/
	public void setAllSpacesOwned(boolean ifAllSpacesOwned){
		allSpacesOwned = ifAllSpacesOwned;
	}

	/**
	* retuns the space at the index provided in parameter
	* @param space
	* @return boardArray.get(space)
	*/
	public Space getSpace(int space) {
		return boardArray.get(space);
	}

  public Board() {

    //Basic Constructor that builds the board
		//Ownership: 0: unowned, Default, 1-2: Player 1 and 2, -1: GO
		//Special Spaces: 11: Chance, 12: Income Tax, 13: Free Parking, 14: Community Fund, 15: Go to Jail, 16: Free Parking
    //Sets every space as a space object to add to the array of the board

		Space space0 = new Space("GO", 0, 0, 0, -1, 0);

    Space space1 = new Space("Yellow 1", 150, 50, 75, 0, 1);
    Space space2 = new Space("Railroad 1", 200, 100, 100, 0, 2);
    Space space3 = new Space("Yellow 2", 200, 75, 100, 0, 3);
    Space space4 = new Space("Yellow 3", 250, 100, 125, 0, 4);
    Space space5 = new Space("Chance", 0, 0, 0, 11, 5);
    Space space6 = new Space("Jail", 0, 0, 0, -1, 6);

		Space space7 = new Space("Blue 1", 350, 150, 175, 0, 7);
		Space space8 = new Space("Blue 2", 400, 175, 200, 0, 8);
		Space space9 = new Space("Railroad 2", 200, 100, 100, 0, 9);
		Space space10 = new Space("Blue 3", 450, 200, 225, 0, 10);
		Space space11 = new Space("Income Tax", 0, 250, 0, 12, 11);
		Space space12 = new Space("Free Parking", 0, 0, 0, 11, 16);

		Space space13 = new Space("Red 1", 550, 250, 275, 0, 13);
		Space space14 = new Space("Red 2", 600, 275, 300, 0, 14);
		Space space15 = new Space("Railroad 3", 200, 100, 100, 0, 15);
		Space space16 = new Space("Red 3", 650, 300, 325, 0, 16);
		Space space17 = new Space("Chance", 0, 0, 0, 11, 17);
		Space space18 = new Space("Go to Jail", 0, 0, 0, 15, 18);

		Space space19 = new Space("Orange 1", 750, 350, 375, 0, 19);
		Space space20 = new Space("Orange 2", 800, 375, 400, 0, 20);
		Space space21 = new Space("Community Fund", 0, 0, 0, 14, 21);
		Space space22 = new Space("Green 1", 900, 425, 450, 0, 22);
		Space space23 = new Space("Green 2", 1000, 500, 500, 0, 23);

		//adds all the spaces created to the board array
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
  }
}
