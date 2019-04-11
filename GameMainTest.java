import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class GameMainTest{

  @Test
  public void test_EndConditionsPlayerOutOfMoney(){

    Player p = new Player(1, true);
    Player c = new Player(2, false);
    int turn = 40;

    p.setMoney(-10);

    assertEquals("Player one is out of money, end conditions met", true, GameMain.endConditions(turn, p, c));
  }

  @Test
  public void test_EndConditionsTurnCountMet(){

    Player p = new Player(1, true);
    Player c = new Player(2, false);
    int turn = 501;

    assertEquals("Turn count reached over 500, game over", true, GameMain.endConditions(turn, p, c));
  }

}
