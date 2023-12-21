import d09.OasisAndSandInstabilitySensor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 9 {@link d09.OasisAndSandInstabilitySensor}
 */
class TestDay9 {
	
	@Test
	void test1() {
		List<String> input = List.of(
				"0 3 6 9 12 15",
				"1 3 6 10 15 21",
				"10 13 16 21 30 45");
		
		long output = OasisAndSandInstabilitySensor.calculate(input, false);
		
		assertEquals(114, output);
	}
	
	@Test
	void test2() {
		List<String> input = List.of("10 13 16 21 30 45");
		
		long output = OasisAndSandInstabilitySensor.calculate(input, true);
		
		assertEquals(5, output);
	}
	
}
