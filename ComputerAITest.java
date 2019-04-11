import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class ComputerAITest{

  @Test
  public void test_defaultComputerAIPosition(){
    ComputerAI c = new ComputerAI();
    assertEquals("AI should start on position 0", 0, c.getPosition());
  }

  @Test
  public void test_defaultComputerAIStartMoney(){
    ComputerAI c = new ComputerAI();
    assertEquals("AI should start with $1500", 1500, c.getMoney());
  }

  @Test
  public void test_defaultComputerAIInJail(){
    ComputerAI c = new ComputerAI();
    assertEquals("AI inJail should return false at start of game", false, c.getInJail());
  }

  @Test
  public void test_getAIPositionInvalidPosition(){
    ComputerAI c = new ComputerAI();
    c.setPosition(25);

    assertEquals("25 is an invalid space, AI should reamin on space 0", 0, c.getPosition());
  }

  @Test
  public void test_getAIEliminatedNotOutOfMoney(){
    ComputerAI c = new ComputerAI();
    c.setMoney(100);
    c.setEliminated(true);

    assertEquals("AI is not out of money, should not be eliminated", false, c.getEliminated());
  }

  @Test
  public void test_playerNumberOne(){
    ComputerAI c = new ComputerAI(1, true);
    c.getPlayerNumber();

    assertEquals("AI cannot be player one", 2, c.getPlayerNumber());
  }
}
