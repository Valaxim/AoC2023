import d02.Solution;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 2 {@link Solution}
 */
public class TestDay2 {
	
	@Test
	public void test1() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay2.txt");
		
		int output = Solution.getIdsSum(input);
		
		assertEquals(output, 8);
	}
	
	@Test
	public void test2() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay2.txt");

		long output = Solution.getPowerOfMinimalCubesAmount(input);
		
		assertEquals(output, 2286);
	}

}
