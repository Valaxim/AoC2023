import d08.HauntedWasteland;
import org.junit.jupiter.api.Test;
import utils.ParseUtil;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Day 8 {@link d08.HauntedWasteland}
 */
class TestDay8 {
	
	@Test
	void test1() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay8_partA_case1.txt");
		
		HauntedWasteland hauntedWasteland = new HauntedWasteland();
		BigInteger output = hauntedWasteland.calculate(input);
		
		assertEquals(BigInteger.valueOf(2), output);
	}
	
	@Test
	void test2() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay8_partA_case2.txt");
		
		HauntedWasteland hauntedWasteland = new HauntedWasteland();
		BigInteger output = hauntedWasteland.calculate(input);
		
		assertEquals(BigInteger.valueOf(6), output);
	}
	
	@Test
	void test3() throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("testInputDay8_partB.txt");
		
		HauntedWasteland hauntedWasteland = new HauntedWasteland();
		BigInteger output = hauntedWasteland.calculateB(input);
		
		assertEquals(BigInteger.valueOf(6), output);
	}
}
