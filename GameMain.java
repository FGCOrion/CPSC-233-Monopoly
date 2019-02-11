class GameMain {
  public int money;
  public String name;
  
  Game_Main(Str string, int startingMoney) { //Constructor for initializing game
    money = startingMoney;
    name = str;
  }
  
  public gainMoney(int num) { //when a player lands on a property
    money += num;
  }
  
  public loseMoney(int num) { // when a player lands on a property
    money -= num;
  }


}
