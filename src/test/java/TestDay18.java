import d18.Gauss;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 18 {@link Gauss}
 */
public class TestDay18 {
	
	@Test
	public void test1() throws IOException {
		List<String> strings = ParseUtil.readInputLineByLine("testInputDay18.txt");
		
		double output = Gauss.calculateGaussA(strings);
		
		assertEquals(62, output);
	}
	
	@Test
	public void test2() throws IOException {
		List<String> strings = ParseUtil.readInputLineByLine("testInputDay18.txt");
		
		double output = Gauss.calculateGaussB(strings);
		
		assertEquals(952408144115.0, output);
	}
	
}
