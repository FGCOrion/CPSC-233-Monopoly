class Player {
    /**
     * Instance variables: "piece" of type Piece, more to follow.
     * Setters and Getters for instance variables
     */
    //instance variables
    private int position = 0;
    private char avatar;

    /* Getters */
    public int getPosition(){
        return position;
    }
    public char getAvatar(){
        return avatar;
    }

    /* Setters */
    public void setPosition(int newPosition){
        position = newPosition;
    }
    public void setAvatar(char avatarType) {
        avatar = avatarType;
    }

    /* constructor */
    public Player() {
        position = 0;
        avatar = 'a';
    }

    public Player(Player otherPlayer)
    {
        position = otherPlayer.getPosition();
        avatar = otherPlayer.getAvatar();
    }

}