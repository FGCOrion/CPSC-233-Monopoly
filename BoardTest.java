import static org.junit.Assert.*;
import org.junit.Test;

public class BoardTest {

	@Test
	public void test_getSpace_using_getCost() {
		Board b = new Board();
		Space c = new Space("GO", 0, 0, 0, -1, 0); //creates yellow 3 on Board
		
		assertEquals("Expected cost of 0", c.getCost(), b.getSpace(0).getCost());
	}
	
	@Test
	public void test_getSpace_using_getOwner() {
		Board b = new Board();
		b.getSpace(0).setOwner(90000);
		assertEquals("owner should be 90000", 90000, b.getSpace(0).getOwner());
	}
	
	@Test
	public void test_getSpace_using_getLocation() {
		Board b = new Board();
		assertEquals("Location should be 4", 4, b.getSpace(4).getLocation());
	}	

	@Test
	public void test_getLength() {
		Board b = new Board();
		
		assertEquals("Expected length of 24", 24, b.getLength());
	}
	
	@Test
	public void test_allSpacesOwned_and_setAllSpacesOwned() {
		Board b = new Board();
		b.setAllSpacesOwned(true);
		assertEquals("Expected false", true, b.getAllSpacesOwned());
	} 
	
	@Test
	public void test_setSpace() {
		Board b = new Board();
		Space c = new Space("blank", 0, 0, 0, -1, 0);
		b.setSpace(5, new Space("blank", 0, 0, 0, -1, 0));
		assertEquals("Expected no owner", c.getOwner(), b.getSpace(5).getOwner());
	}

}