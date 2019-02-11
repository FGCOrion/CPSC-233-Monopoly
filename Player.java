class Player {
  /** 
  * Instance variables: "piece" of type Piece, more to follow.
  * Setters and Getters for instance variables
  */
  //instance variables
  private Piece piece;
  
  //getter method
  
  public Piece getPiece()
  {
    return piece
  }
  //setter method
  
  public void setPiece(Piece newPiece)
  {
    this.piece=newPiece;
  }
  
  //constructor
  
  public Player()
  {
    
  }
  //constructors
  public Player(Piece c)
  {
    this.piece=c;
  }
  
  public Player(Piece k)//copy constructor
  {
    this.piece=k.piece;
  }

  
  
  
}
