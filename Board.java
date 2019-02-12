class Board {
    /**
    * Instance Variables: "spaces" of type ArrayList
    * Setters and Getters for instance variables
    */
  
    //Sets every space as a space object to add to the array of the board
    Space space1 = new Space("Space 1", 150, 50);
    Space space2 = new Space("Space 2", 200, 75);
    Space space3 = new Space("?", 0, 0);
    Space space4 = new Space("Space 4", 250, 100);
    Space space5 = new Space("Space 5", 300, 125);
    Space space6 = new Space("Jail", 350, 150);
    
    Space[] boardArray = {space1, 
                          space2,
                          space3,
                          space4,
                          space5,
                          space6,
                         };

}
