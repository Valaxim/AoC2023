import d03.GearRatios;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 3 {@link GearRatios}
 */
public class TestDay3 {
	
	@Test
	public void test1() throws IOException {
		char[][] game = ParseUtil.read2DArray("testInputDay3.txt");
		
		int output = GearRatios.solution(game);
		
		assertEquals(output, 4361);
	}
	
	@Test
	public void test2() throws IOException {
		char[][] game = ParseUtil.read2DArray("testInputDay3.txt");
		
		long output = GearRatios.solutionB(game);
		
		assertEquals(output, 467835);
	}

}
