package d08;

import utils.ParseUtil;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/8">Advent of Code 2023 - Day 8</a>
 */
public class Day8 {
	
	public static void main(String[] args) throws IOException {
		List<String> input = ParseUtil.readInputLineByLine("inputDay8.txt");
		
		long startTime = System.nanoTime();
		HauntedWasteland hauntedWasteland = new HauntedWasteland();
		BigInteger outputA = hauntedWasteland.calculate(input);
		long endTime = System.nanoTime();

		System.out.println("Answer Day8 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
		
		startTime = System.nanoTime();
		BigInteger outputB = hauntedWasteland.calculateB(input);
		endTime = System.nanoTime();
		System.out.println("Answer Day8 partB: " + outputB + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

