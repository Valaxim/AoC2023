package d19;

import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/19">Advent of Code 2023 - Day 19</a>
 */
public class Day19 {
	
	public static void main(String[] args) throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("inputDay19.txt");
		
		long startTime = System.nanoTime();
		long outputA = Aplenty.calculate(input);
		long endTime = System.nanoTime();
		System.out.println("Answer Day19 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

