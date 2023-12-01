package d01;

import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/1">Advent of Code 2023 - Day 1</a>
 */
public class Day1 {
	
	public static void main(String[] args) throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("inputDay1.txt");
		
		int outputA = Solution.getSum(input);
		
		List<String> strings = Solution.replaceDigitsWrittenAsString(input);
		int outputB = Solution.getSum(strings);
		
		System.out.println("Answer Day1 partA: " + outputA);
		System.out.println("Answer Day1 partB: " + outputB);
	}
}

