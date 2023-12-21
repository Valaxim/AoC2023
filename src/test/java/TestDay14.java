import d14.ParabolicReflectorDish;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 14 {@link d14.ParabolicReflectorDish}
 */
class TestDay14 {
	
	@Test
	void test1() throws IOException {
		char[][] input = ParseUtil.read2DArray("testInputDay14.txt");
		
		long output = ParabolicReflectorDish.calculate(input);
		
		assertEquals(136, output);
	}
	
	@Test
	void test2() throws IOException {
		char[][] input = ParseUtil.read2DArray("testInputDay14.txt");
		
		long output = ParabolicReflectorDish.calculateB(input);
		
		assertEquals(64, output);
	}
	
}
