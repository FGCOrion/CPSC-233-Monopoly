class Space {
    //The name of the space
    public String name;
    
    //How much money the space costs to buy
    public int cost;
    
    //How much money it costs to land on the space
    public int value;
    
    //Who controls the space, 0=unowned, 1=player, 2=AI
    public int owner;
    
    
    //Basic constructor for a space
    public Space(String name,int cost, int value, int owner) {
        this.name = name;
        this.cost = cost;
        this.value = value;
        this.owner = owner;
    }

}