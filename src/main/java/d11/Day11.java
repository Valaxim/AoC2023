package d11;

import utils.ParseUtil;

import java.io.IOException;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/11">Advent of Code 2023 - Day 11</a>
 */
public class Day11 {
	
	private static final long DOUBLE_FACTOR = 2;
	private static final long EXTEND_FACTOR = 1_000_000;
	
	public static void main(String[] args) throws IOException {
		
		char[][] chars = ParseUtil.read2DArray("inputDay11.txt");
		
		long startTime = System.nanoTime();
		long outputA = CosmicExpansion.calculate(chars, DOUBLE_FACTOR);
		long endTime = System.nanoTime();
		
		System.out.println("Answer Day11 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
		
		startTime = System.nanoTime();
		long outputB = CosmicExpansion.calculate(chars, EXTEND_FACTOR);
		endTime = System.nanoTime();
		System.out.println("Answer Day11 partB: " + outputB + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

