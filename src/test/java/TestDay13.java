
import d13.PointOfIncidence;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 13 {@link PointOfIncidence}
 */
class TestDay13 {
	
	@Test
	void test1() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay13.txt");
		
		long output = PointOfIncidence.calculate(input);
		
		assertEquals(405, output);
	}
	
	@Test
	void test2() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay13.txt");
		
		long output = PointOfIncidence.calculateB(input);
		
		assertEquals(400, output);
	}
	
}
