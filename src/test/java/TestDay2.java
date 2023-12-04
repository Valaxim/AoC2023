import d02.CubeConundrum;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 2 {@link CubeConundrum}
 */
public class TestDay2 {
	
	@Test
	public void test1() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay2.txt");
		
		int output = CubeConundrum.getIdsSum(input);
		
		assertEquals(8, output);
	}
	
	@Test
	public void test2() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay2.txt");

		long output = CubeConundrum.getPowerOfMinimalCubesAmount(input);
		
		assertEquals(2286, output);
	}

}
