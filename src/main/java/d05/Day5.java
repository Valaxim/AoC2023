package d05;

import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/5">Advent of Code 2023 - Day 5</a>
 */
public class Day5 {
	
	public static void main(String[] args) throws IOException {
		Long startTime = System.nanoTime();
		
		List<String> input = ParseUtil.readInputLineByLine("inputDay5.txt");
		long outputA = Almanac.calculate(input);
		Long endTime = System.nanoTime();
		
		System.out.println("Answer Day5 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

