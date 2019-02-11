class GameMain {
  public int money;
  public String name;
  
  GameMain(String name, int startingMoney) { //Constructor for initializing game
    money = startingMoney;
    name = name;
  }
  
  public void gainMoney(int num) { //when a player lands on a property
    money += num;
  }
  
  public void loseMoney(int num) { // when a player lands on a property
    money -= num;
  }


}
