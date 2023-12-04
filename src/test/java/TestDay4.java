import d04.Scratchcards;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 4 {@link Scratchcards}
 */
public class TestDay4 {
	
	@Test
	public void test1() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay4.txt");
		
		double output = Scratchcards.calculateTotalPoints(input);
		
		assertEquals(13, output);
	}
	
	@Test
	public void test2() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay4.txt");
		
		BigInteger expectedOutput = BigInteger.valueOf(30);
		BigInteger actualOutput = Scratchcards.calculateTotalCardAmounts(input);
		
		assertEquals(expectedOutput, actualOutput);
	}
	
}
