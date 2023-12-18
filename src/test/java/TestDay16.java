import d16.TheFloorWillBeLava;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 16 {@link d16.TheFloorWillBeLava}
 */
public class TestDay16 {
	
	@Test
	public void test1() throws IOException {
		char[][] input = ParseUtil.read2DArray("testInputDay16.txt");
		
		long output = TheFloorWillBeLava.calculate(input);
		
		assertEquals(46, output);
	}
	
	@Test
	public void test2() throws IOException {
		char[][] input = ParseUtil.read2DArray("testInputDay16.txt");
		
		long output = TheFloorWillBeLava.calculateB(input);
		
		assertEquals(51, output);
	}
	
	
}
