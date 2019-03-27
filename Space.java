import javafx.scene.control.TextArea;

class Space {

    private String name;
    private int cost;
    private int value;
    private int owner;
    private int location;
    private int saleValue;

    /**
    *constructor with the owner
    *@param name
    *@param cost
    *@param value
    *@param owner
    **/
    public Space(String name, int cost, int value, int saleValue, int owner, int location) {
        this.name = name;
        this.cost = cost;
        this.value = value;
        this.saleValue = saleValue;
        this.owner = owner;
        this.location = location;
    }
    //setter method
    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getName() {
        return this.name;
    }

    public int getCost() {
        return this.cost;
    }

    public int getValue() {
        return this.value;
    }

    public int getOwner() {
        return this.owner;
    }

    public int getLocation(){
      return this.location;
    }

    public int getSaleValue(){
      return this.saleValue;
    }

	//A test method for Board to check every space in the boardArray
	public String getInfo() {
		return this.getName() + ", Cost " + this.getCost() + ", Rent " + this.getValue();
	}

  
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
