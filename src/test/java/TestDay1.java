import d01.Trebuchet;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 1  {@link Trebuchet}
 */
public class TestDay1 {
	
	@Test
	public void test1() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay1_partA.txt");
		
		int output = Trebuchet.getSum(input);
		
		assertEquals(output, 142);
	}
	
	@Test
	public void test2() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay1_partB.txt");
		
		List<String> strings = Trebuchet.replaceDigitsWrittenAsString(input);
		int output = Trebuchet.getSum(strings);
		
		assertEquals(output, 281);
	}

}
