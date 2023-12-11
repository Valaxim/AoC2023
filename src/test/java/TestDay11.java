import d11.CosmicExpansion;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 11 {@link CosmicExpansion}
 */
public class TestDay11 {
	
	@Test
	public void test1() throws IOException {
		char[][] input = ParseUtil.read2DArray("testInputDay11.txt");
		
		long output = CosmicExpansion.calculate(input, 2);
		
		assertEquals(374, output);
	}
	
	@Test
	public void test2() throws IOException {
		char[][] input = ParseUtil.read2DArray("testInputDay11.txt");
		
		long output = CosmicExpansion.calculate(input, 10);
		
		assertEquals(1030, output);
	}
	
	@Test
	public void test3() throws IOException {
		char[][] input = ParseUtil.read2DArray("testInputDay11.txt");
		
		long output = CosmicExpansion.calculate(input, 100);
		
		assertEquals(8410, output);
	}
	
}
