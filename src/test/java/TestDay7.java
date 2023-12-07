import d07.CamelCards;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 7 {@link d07.CamelCards}
 */
public class TestDay7 {
	
	@Test
	public void test1() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay7.txt");
		
		long output = CamelCards.calculate(input);
		
		assertEquals(6440, output);
	}
	
	@Test
	public void test2() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay7.txt");
		
		long output = CamelCards.calculateB(input);
		
		assertEquals(5905, output);
	}
}
