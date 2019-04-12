import static org.junit.Assert.*;
import org.junit.Test;

public class SpaceTest {
	
	@Test
	public void test_Constructor() {
		Space s = new Space("test", 0, 0, 0, 0, 0);
		
		assertEquals("not proper boi", 0, s.getCost());
	}
	
	@Test
	public void test_setOwner_getOwner() {
		Space bruh = new Space("blank",0, 0, 0, 0, 0);
		bruh.setOwner(9);
		assertEquals("Expected 9", 9, bruh.getOwner());
	}
	
	@Test
	public void test_getName() {
		Space yeet = new Space("The BIG NAME", 0, 0, 0, 0, 0);
		assertEquals("Expected the BIG NAME", "The BIG NAME", yeet.getName());
	}

	@Test
	public void test_getCost() {
		Space cactus = new Space("title", 5000, 0, 0, 0, 0);
		assertEquals("Expected 5000", 5000, cactus.getCost());
	}
	
	@Test
	public void test_getLocation() {
		Space kcuf = new Space("kcuf", 0, 0, 0, 0, 9000);
		assertEquals("Expected 9000", 9000, kcuf.getLocation());
	}
	
	@Test
	public void test_getValue() {
		Space BIG = new Space("BIG", 0, 5555, 0, 0, 0);
		assertEquals("Expected 5555", 5555, BIG.getValue());
	}
	
	@Test
	public void test_getSaleValue() {
		Space DOG = new Space("BROIMDEAD", 0, 0, 9, 0, 0);
		assertEquals("Expected 9", 9, DOG.getSaleValue());
	}
	
}