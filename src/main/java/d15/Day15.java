package d15;

import utils.ParseUtil;

import java.io.IOException;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/15">Advent of Code 2023 - Day 15</a>
 */
public class Day15 {
	
	public static void main(String[] args) throws IOException {
		
		List<String> input = ParseUtil.readInputLineByLine("inputDay15.txt");
		
		long startTime = System.nanoTime();
		long outputA = LensLibrary.calculate(input);
		long endTime = System.nanoTime();

		System.out.println("Answer Day15 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
		
		startTime = System.nanoTime();
		long outputB = LensLibrary.calculateB(input);
		endTime = System.nanoTime();

		System.out.println("Answer Day15 partB: " + outputB + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

