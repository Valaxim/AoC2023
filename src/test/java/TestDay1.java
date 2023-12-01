import d01.Solution;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for {@link Solution}
 */
public class TestDay1 {
	
	@Test
	public void test1() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay1_partA.txt");
		
		int output = Solution.getSum(input);
		
		assertEquals(output, 142);
	}
	
	@Test
	public void test2() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay1_partB.txt");
		
		List<String> strings = Solution.replaceDigitsWrittenAsString(input);
		int output = Solution.getSum(strings);
		
		assertEquals(output, 281);
	}

}
