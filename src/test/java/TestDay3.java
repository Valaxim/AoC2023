import d03.GearRatios;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 3 {@link GearRatios}
 */
class TestDay3 {
	
	@Test
	void test1() throws IOException {
		char[][] game = ParseUtil.read2DArray("testInputDay3.txt");
		
		int output = GearRatios.getPartNumbersSum(game);
		
		assertEquals(4361, output);
	}
	
	@Test
	void test2() throws IOException {
		char[][] game = ParseUtil.read2DArray("testInputDay3.txt");
		
		long output = GearRatios.getAllGearRatiosSum(game);
		
		assertEquals(467835, output);
	}
	
}
