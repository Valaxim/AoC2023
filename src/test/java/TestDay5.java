import d05.Almanac;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 5 {@link Almanac}
 */
class TestDay5 {
	
	@Test
	void test1() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay5.txt");
		
		long output = Almanac.calculate(input);
		
		assertEquals(35, output);
	}
	
}
