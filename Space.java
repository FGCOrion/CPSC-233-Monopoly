class Space {
    //The name of the space
    public String name;
    
    //How much money the space costs to buy
    public int cost;
    
    //How much money it costs to land on the space
    public int value;
    
    //Who controls the space, 0=unowned, 1=player, 2=AI
    public int owner;
    
    
    //Basic constructors for a space
    public Space(String name,int cost, int value) {
        this.name = name;
        this.cost = cost;
        this.value = value;
        this.owner = 0;
        //Defaults to nobody having any control of the space
    }
    
    public Space(String name,int cost, int value, int owner) {
        this.name = name;
        this.cost = cost;
        this.value = value;
        this.owner = owner;
    }
    
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

}