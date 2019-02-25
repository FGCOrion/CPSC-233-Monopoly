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
        return position;
    }
    public char getAvatar(){
        return avatar;
    }
    public int getMoney(){
        return money;
    }
    public ArrayList getOwns(){
        return new ArrayList (owns);
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
        retrun true;
    }
        
    public boolean placeExist(String p){
        if (owns.contains(p))
            return true;
        else
            return false;
    }
    
    /* Setters */
    public void setPosition(int newPosition){
        position = newPosition;
    }
    public void setAvatar(char avatarType) {
        avatar = avatarType;
    }
    public void setMoney(int newMoney){
        money = newMoney;
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
