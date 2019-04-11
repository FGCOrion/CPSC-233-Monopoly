import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class PlayerTest{

  @Test
  public void test_defaultPlayerStartPosition(){
    Player p = new Player();
    assertEquals("Player should start on position 0", 0, p.getPosition());
  }

  @Test
  public void test_defaultPlayerStartMoney(){
    Player p = new Player();
    assertEquals("Player should start with $1500", 1500, p.getMoney());
  }

  @Test
  public void test_defaultPlayerInJail(){
    Player p = new Player();
    assertEquals("Player inJail should return false at start of game", false, p.getInJail());
  }

  @Test
  public void test_getPositionInvalidPosition(){
    Player p = new Player();
    p.setPosition(25);

    assertEquals("25 is an invalid space, player should reamin on space 0", 0, p.getPosition());
  }

  @Test
  public void test_getEliminatedNotOutOfMoney(){
    Player p = new Player();
    p.setMoney(100);
    p.setEliminated(true);

    assertEquals("Player is not out of money, should not be eliminated", false, p.getEliminated());
  }

  @Test
  public void test_playerNumberTooHigh(){
    Player p = new Player(5, true);
    p.getPlayerNumber();

    assertEquals("Player number five is not allowed", 1, p.getPlayerNumber());
  }

  @Test
  public void test_createGame(){
    int human = 3;
    int computer = 1;
    int total = human + computer;
    ArrayList<Player> p = new ArrayList<Player>();

    Player.createGame(total, human, computer, p);

    assertEquals("Total players in game should be 4", 4, p.size());
  }

  @Test
  public void test_createGameTooManyHumans(){
    int human = 5;
    int computer = 1;
    int total = human + computer;
    ArrayList<Player> p = new ArrayList<Player>();

    Player.createGame(total, human, computer, p);

    assertEquals("Total players cannot exceed 4, should be 0", 0, p.size());
  }

  @Test
  public void test_createGameTooFewPlayers(){
    int human = 1;
    int computer = 0;
    int total = human + computer;
    ArrayList<Player> p = new ArrayList<Player>();

    Player.createGame(total, human, computer, p);

    assertEquals("Game must have at least 2 players", 0, p.size());
  }

  @Test
  public void test_networths(){

    Board b = new Board();
    Player p1 = new Player(1, true);
    Player p2 = new Player(2, true);
    
    b.getSpace(23).setOwner(1);
    b.getSpace(22).setOwner(2);

    p1.setMoney(1000);
    p2.setMoney(5);

    assertEquals("Player 1 networth should be 2000", 2000, p1.getNetWorth(b));
    assertEquals("Player 2 networth should be 2000", 905, p2.getNetWorth(b));
  }

}
