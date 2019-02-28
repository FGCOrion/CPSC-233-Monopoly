class Piece {
    /* Instance Variables */
    private int position = 0;
    private char avatar;

    /**
    *Getters
    *@return position
    *@return avatar
    **/
    
    public int getPosition(){
        return position;
    }
    public char getAvatar(){
        return avatar;
    }

    /* 
    *Setters 
    */
    public void setPosition(int newPosition){
        position = newPosition;
    }
    public void setAvatar(char avatarType){
        avatar = avatarType;
    }
}
