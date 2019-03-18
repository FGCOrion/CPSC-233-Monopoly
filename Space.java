
class Space {
    //The name of the space
    public String name;

    //How much money the space costs to buy
    public int cost;

    //How much money it costs to land on the space
    public int value;

    //Who controls the space, 0=unowned, 1=player, 2=AI
    public int owner;

    public int location;


    /**
    *constructor with the owner
    *@param name
    *@param cost
    *@param value
    *@param owner
    **/
    public Space(String name,int cost, int value, int owner, int location) {
        this.name = name;
        this.cost = cost;
        this.value = value;
        this.owner = owner;
        this.location = location;
    }
    //setter method
    public void setOwner(int owner) {
        this.owner = owner;
    }
    /**
    *@return name
    *@return cost
    *@return value
    **/
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

	//A test method for Board to check every space in the boardArray
	public String getInfo() {
		return name + ":  " + String.valueOf(owner);
	}

}
