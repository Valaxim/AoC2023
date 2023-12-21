import d19.Aplenty;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 19 {@link Aplenty}
 */
class TestDay19 {
	
	@Test
	void test1() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay19.txt");
		
		long output = Aplenty.calculate(input);
		
		assertEquals(19114L, output);
	}
	
}
