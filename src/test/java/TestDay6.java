import d06.Race;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 6 {@link Race}
 */
public class TestDay6 {
	
	@Test
	public void test1() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay6.txt");
		
		long output = Race.calculateAsArray(input);
		
		assertEquals(288, output);
	}
	
	@Test
	public void test2() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay6.txt");
		
		long output = Race.calculateAsSingleValue(input);
		
		assertEquals(71503, output);
	}
}
