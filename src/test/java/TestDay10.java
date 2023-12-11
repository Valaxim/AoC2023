import d10.PipeMaze;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 10 {@link PipeMaze}
 */
public class TestDay10 {
	
	@Test
	public void test1() throws IOException {
		char[][] input = ParseUtil.read2DArray("testInputDay10_partA_case1.txt");
		
		long output = PipeMaze.calculate(input);
		
		assertEquals(4, output);
	}
	
	@Test
	public void test2() throws IOException {
		char[][] input = ParseUtil.read2DArray("testInputDay10_partA_case2.txt");
		
		long output = PipeMaze.calculate(input);
		
		assertEquals(4, output);
	}
	
	@Test
	public void test3() throws IOException {
		char[][] input = ParseUtil.read2DArray("testInputDay10_partA_case3.txt");
		
		long output = PipeMaze.calculate(input);
		
		assertEquals(8, output);
	}
	
	@Test
	public void test4() throws IOException {
		char[][] input = ParseUtil.read2DArray("testInputDay10_partA_case4.txt");
		
		long output = PipeMaze.calculate(input);
		
		assertEquals(8, output);
	}
}
