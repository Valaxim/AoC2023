package d16;

import utils.ParseUtil;

import java.io.IOException;

/**
 * Main class for <a href="https://adventofcode.com/2023/day/16">Advent of Code 2023 - Day 16</a>
 */
public class Day16 {
	
	public static void main(String[] args) throws IOException {
		char[][] input = ParseUtil.read2DArray("inputDay16.txt");
		
		long startTime = System.nanoTime();
		long outputA = TheFloorWillBeLava.calculate(input);
		long endTime = System.nanoTime();
		
		System.out.println("Answer Day16 partA: " + outputA + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
		
		startTime = System.nanoTime();
		long outputB = TheFloorWillBeLava.calculateB(input);
		endTime = System.nanoTime();
		
		System.out.println("Answer Day16 partB: " + outputB + ", calculated in " + (endTime - startTime) / 1000000.0 + " ms");
	}
}

