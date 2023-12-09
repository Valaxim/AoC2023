package d09;

import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/9">Advent of Code 2023 - Day 9</a>
 */
public class Day9 {
	
	public static void main(String[] args) throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("inputDay9.txt");
		
		Long startTime = System.nanoTime();
		long outputA = OasisAndSandInstabilitySensor.calculate(input, false);
		Long endTime = System.nanoTime();
		
		System.out.println("Answer Day9 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
		
		startTime = System.nanoTime();
		long outputB = OasisAndSandInstabilitySensor.calculate(input, true);
		endTime = System.nanoTime();
		System.out.println("Answer Day9 partB: " + outputB + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

