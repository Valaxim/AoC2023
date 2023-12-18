import d18.LavaductLagoon;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

import static d18.LavaductLagoon.Point;
import static d18.LavaductLagoon.calculate;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 18 {@link LavaductLagoon}
 */
public class TestDay18 {
	
	@Test
	public void test1() throws IOException {
		List<String> strings = ParseUtil.readInputLineByLine("testInputDay18.txt");
		
		long output = calculate(strings, new Point(1, 5));
		
		assertEquals(62, output);
	}
}
