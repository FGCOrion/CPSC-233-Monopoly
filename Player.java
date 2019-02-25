import java.util.ArrayList;
class Player {
    /**
     * Instance variables: "piece" of type Piece, more to follow.
     * Setters and Getters for instance variables
     */
    //instance variables
    private int position = 0;
    private char avatar;
    private int money;
    private ArrayList owns= new ArrayList();

    /* Getters */
    public int getPosition(){
        return new int(position);
    }
    public char getAvatar(){
        return new char(avatar);
    }
    public int getMoney(){
        return new int(money);
    }
    /*manage the place*/
    public boolean buyPlace(String p){
        if (owns.contains(p))
            return false;
        owns.add(p);
        return true;
    }
    public boolean sellPlace(String p){
        if (!owns.contains(p))
            return false;
        owns.remove(p);
        return true;
    }
        
    public boolean placeExist(String p){
        if (owns.contains(p))
            return true;
        else
            return false;
    }
    
    /* Setters */
    public void setPosition(int newPosition){
        this.position = new int (newPosition);
    }
    public void setAvatar(char avatarType) {
        this.avatar = new char(avatarType);
    }
    public void setMoney(int newMoney){
        this.money = new int(newMoney);
    }
    
   
    /* constructor */
    public Player() {
        position = 0;
        avatar = 'a';
        money = 0;
    }

    public Player(Player otherPlayer)
    {
        position = otherPlayer.getPosition();
        avatar = otherPlayer.getAvatar();
        money = otherPlayer.getMoney();
    }

}
