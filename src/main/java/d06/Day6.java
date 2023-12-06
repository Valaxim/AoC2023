package d06;

import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/6">Advent of Code 2023 - Day 6</a>
 */
public class Day6 {
	
	public static void main(String[] args) throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("inputDay6.txt");
		
		Long startTime = System.nanoTime();
		long outputA = Race.calculateAsArray(input);
		Long endTime = System.nanoTime();
		
		System.out.println("Answer Day6 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
		
		startTime = System.nanoTime();
		long outputB = Race.calculateAsSingleValue(input);
		endTime = System.nanoTime();
		System.out.println("Answer Day6 partB: " + outputB + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

