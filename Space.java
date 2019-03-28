import javafx.scene.control.TextArea;

class Space {

	/**
	* Setup variables for each space
	**/
    private String name;
    private int cost;
    private int value;
    private int owner;
    private int location;
    private int saleValue;

    /**
    * constructor with the owner
    * @param name
    * @param cost
    * @param value
    * @param owner
    **/
    public Space(String name, int cost, int value, int saleValue, int owner, int location) {
        this.name = name;
        this.cost = cost;
        this.value = value;
        this.saleValue = saleValue;
        this.owner = owner;
        this.location = location;
    }
	
	/*	Setters and Getters	 */
    
	/**
	* Sets the owner of the space as an integer value
	* @param owner
	**/
    public void setOwner(int owner) {
        this.owner = owner;
    }
	
	/**
	* Returns a string value of the name of the space
	* @return name
	**/
    public String getName() {
        return name;
    }

	/**
	* Returns the cost to purchase the space
	* @return cost
	**/
    public int getCost() {
        return cost;
    }

	/**
	* Returns the value of the space (how much a player has to pay the owner of the space when they land on it)
	* @return value
	**/
    public int getValue() {
        return value;
    }

	/**
	* Returns the owner of the space as an integer value
	* @return owner
	**/
    public int getOwner() {
        return owner;
    }

	/**
	* Returns the location of the space on the board as an integer (Starting at Go and going clockwise)
	* @return location
	**/
    public int getLocation(){
      return location;
    }

	/**
	* Returns the sell value of the property (half of the original price)
	* @return saleValue
	**/
    public int getSaleValue(){
      return saleValue;
    }


  /**
  * Method to set the text values of a space for the graphical version
  * @param newSpace 		The space that is going to be edited
  * @param spaceInfo		The text that is displayed in Gui.java
  * @param basicText 	Used in Gui.java to display the basic information of this space
  **/
  public void setSpaceInfo(Space newSpace, TextArea spaceInfo, String basicText){
    if (newSpace.getOwner() == 1)
      spaceInfo.setText(basicText + "\nOwned by Player 1");
    else if (newSpace.getOwner() == 2)
      spaceInfo.setText(basicText + "\nOwned by Player 2");
    else if (newSpace.getOwner() == 3)
        spaceInfo.setText(basicText + "\nOwned by Player 3");
    else if (newSpace.getOwner() == 4)
      spaceInfo.setText(basicText + "\nOwned by Player 4");
    else
      spaceInfo.setText(basicText + "\nUnowned");
  }

}
