import d01.Solution;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay1 {
	
	@Test
	public void test1() {
		List<Integer> integers = readTestData();
		
		Solution solution = new Solution();
		
		assertEquals(solution.getInteger(integers), 24000);
	}
	
	@Test
	public void test2() {
		List<Integer> integers = readTestData();
		
		Solution solution = new Solution();
		
		assertEquals(solution.getVarTop3(integers), 45000);
	}
	
	private List<Integer> readTestData() {
		List<String> testData = List.of("1000",
				"2000",
				"3000",
				"",
				"4000",
				"",
				"5000",
				"6000",
				"",
				"7000",
				"8000",
				"9000",
				"",
				"10000");
		return new Solution().createDataFromInput(testData);
	}
}
