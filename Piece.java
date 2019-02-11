class Piece {
    /* Instance Variables */
    private int position = 0;
    private char avatar;

    /* Getters */
    public int getXposition(){
        return position;
    }
    public char getAvatar(){
        return avatar;
    }

    /* Setters */
    public void setPosition(int newPosition){
        position = newPosition;
    }
    public void setAvatar(char avatarType){
            avatar = avatarType;
    }
}
